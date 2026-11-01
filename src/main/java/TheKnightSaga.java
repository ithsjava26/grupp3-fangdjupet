import characters.Knight;
import game.SnapshotRestorer;
import game.menu.MenuChoice;
import game.save.GameSnapshot;
import game.save.SaveGameException;
import game.save.SaveGameService;
import game.save.TextSaveGameService;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * The game console: the main menu that runs everything!
 */
public class TheKnightSaga {

    private static final Path SAVE_FILE = Path.of("saves", "sparat-aventyr.txt");
    private static final Scanner scanner = new Scanner(System.in);

    private Knight hero;

    public static void main(String[] args) {
        new TheKnightSaga().run();
    }

    private void run() {
        SaveGameService saveService = new TextSaveGameService(SAVE_FILE);
        IO.println("Välkommen till Fangdjupet!");
        boolean running = true;
        while (running) {
            printMenu();
            MenuChoice choice = MenuChoice.parse(scanner.nextLine());
            switch (choice) {
                case NEW_ADVENTURE -> hero = startNewAdventure();
                case CONTINUE -> hero = continueAdventure(saveService);
                case SHOW_EQUIPMENT -> showEquipment();
                case NEXT_BATTLE -> IO.println("Striderna kopplas in när spelmotorn är klar!");
                case EXIT -> {
                    saveOnExit(saveService);
                    running = false;
                }
                case UNKNOWN -> IO.println("Ogiltigt val, försök igen!");
            }
        }
    }

    private void printMenu() {
        IO.println("""
                Fangdjupet
                ==========
                1. Starta nytt äventyr
                2. Fortsätt sparat äventyr
                3. Visa hjältens utrustning
                4. Strid mot nästa fiende
                e. Avsluta""");
    }

    private Knight startNewAdventure() {
        IO.print("Vad heter din hjälte? ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Riddaren";
        }
        IO.println(name + " kliver ner i Fangdjupets mörka djup...");
        return new Knight(name);
    }

    private Knight continueAdventure(SaveGameService saveService) {
        try {
            GameSnapshot snapshot = saveService.load();
            Knight restored = SnapshotRestorer.restore(snapshot);
            IO.println("Välkommen tillbaka, " + restored.getName() + "! (" + restored.getHealth() + " hp)");
            return restored;
        } catch (SaveGameException e) {
            IO.println("Kunde inte läsa in sparat äventyr: " + e.getMessage());
            return hero; // keep the current hero if there is one
        }
    }

    private void showEquipment() {
        if (hero == null) {
            IO.println("Du måste starta ett äventyr först!");
            return;
        }
        IO.println(hero.getName() + " bär just nu: (utrustningsvyn kopplas samman med Inventory i fas 2)");
    }

    private void saveOnExit(SaveGameService saveService) {
        if (hero == null) {
            IO.println("Välkommen tillbaka!");
            return;
        }
        try {
            saveService.save(new GameSnapshot(hero.getName(), hero.getHealth(), 1, List.of(), List.of()));
            IO.println("Ditt äventyr sparades. Välkommen tillbaka!");
        } catch (SaveGameException e) {
            IO.println("Kunde inte spara: " + e.getMessage());
        }
    }
}
