package game.save;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves and loads a game round as a simple readable UTF-8 text file.
 *
 * File format:
 *   # comment
 *   heroName=Lancelott
 *   heroHealth=80
 *   room=3
 *   defeated=Insurgent
 *   item=Sten|5
 */
public class TextSaveGameService implements SaveGameService {

    private final Path saveFile;

    public TextSaveGameService(Path saveFile) {
        this.saveFile = saveFile;
    }

    @Override
    public void save(GameSnapshot snapshot) throws SaveGameException {
        try {
            Files.createDirectories(saveFile.toAbsolutePath().getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(saveFile, StandardCharsets.UTF_8)) {
                writer.write("# Fangdjupet save file v1");
                writer.newLine();
                writeLine(writer, "heroName=" + snapshot.heroName());
                writeLine(writer, "heroHealth=" + snapshot.heroHealth());
                writeLine(writer, "room=" + snapshot.roomNumber());
                for (String enemy : snapshot.defeatedEnemies()) {
                    writeLine(writer, "defeated=" + enemy);
                }
                for (InventoryEntry item : snapshot.inventory()) {
                    writeLine(writer, "item=" + item.itemName() + "|" + item.quantity());
                }
            }
        } catch (IOException e) {
            throw new SaveGameException("Kunde inte spara spelet: " + e.getMessage(), e);
        }
    }

    @Override
    public GameSnapshot load() throws SaveGameException {
        try {
            List<String> lines = Files.readAllLines(saveFile, StandardCharsets.UTF_8);
            return parse(lines);
        } catch (IOException e) {
            throw new SaveGameException("Kunde inte läsa in spelet: " + e.getMessage(), e);
        }
    }

    private void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    private GameSnapshot parse(List<String> lines) throws SaveGameException {
        String heroName = null;
        int heroHealth = -1;
        int room = -1;
        List<String> defeated = new ArrayList<>();
        List<InventoryEntry> items = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank() || line.startsWith("#")) {
                continue;
            }
            int equalsIndex = line.indexOf('=');
            if (equalsIndex <= 0) {
                throw new SaveGameException("Ogiltig rad i sparfilen: " + line);
            }
            String key = line.substring(0, equalsIndex);
            String value = line.substring(equalsIndex + 1);
            switch (key) {
                case "heroName" -> heroName = value;
                case "heroHealth" -> heroHealth = parseInt(value, "heroHealth");
                case "room" -> room = parseInt(value, "room");
                case "defeated" -> defeated.add(value);
                case "item" -> items.add(parseItem(value));
                default -> throw new SaveGameException("Okänd nyckel i sparfilen: " + key);
            }
        }

        if (heroName == null || heroName.isBlank()) {
            throw new SaveGameException("Sparfilen saknar hjältenamn");
        }
        if (heroHealth < 0) {
            throw new SaveGameException("Sparfilen saknar hälsopoäng");
        }
        if (room < 1) {
            throw new SaveGameException("Sparfilen saknar rumnummer");
        }
        return new GameSnapshot(heroName, heroHealth, room, defeated, items);
    }

    private InventoryEntry parseItem(String value) throws SaveGameException {
        String[] parts = value.split("\\|");
        if (parts.length != 2) {
            throw new SaveGameException("Ogiltigt föremål i sparfilen: " + value);
        }
        try {
            return new InventoryEntry(parts[0], parseInt(parts[1], "kvantitet"));
        } catch (IllegalArgumentException e) {
            throw new SaveGameException(e.getMessage());
        }
    }

    private int parseInt(String value, String field) throws SaveGameException {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new SaveGameException("Ogiltigt tal för " + field + ": " + value);
        }
    }
}
