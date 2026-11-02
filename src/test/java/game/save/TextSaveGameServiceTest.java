package game.save;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextSaveGameServiceTest {

    @TempDir
    Path tempDir;

    private TextSaveGameService service;
    private GameSnapshot snapshot;

    @BeforeEach
    void setUp() {
        service = new TextSaveGameService(tempDir.resolve("sparat-aventyr.txt"));
        snapshot = new GameSnapshot("Lancelott", 80, 3,
                List.of("Insurgent", "Insurgent"),
                List.of(new InventoryEntry("Sten", 5), new InventoryEntry("Träpinne", 1)));
    }

    @Test
    void saveThenLoadShouldRoundTripTheWholeSnapshot() throws SaveGameException {
        service.save(snapshot);
        assertEquals(snapshot, service.load());
    }

    @Test
    void saveShouldCreateTheSaveFileOnDisk() throws SaveGameException {
        service.save(snapshot);
        assertTrue(Files.exists(tempDir.resolve("sparat-aventyr.txt")));
    }

    @Test
    void loadShouldThrowWhenThereIsNoSaveFile() {
        assertThrows(SaveGameException.class, () -> service.load());
    }

    @Test
    void loadShouldThrowWhenHeroNameIsMissing() throws Exception {
        Files.writeString(tempDir.resolve("sparat-aventyr.txt"), "heroHealth=80\nroom=1\n");
        assertThrows(SaveGameException.class, () -> service.load());
    }

    @Test
    void loadShouldThrowWhenHealthIsMissing() throws Exception {
        Files.writeString(tempDir.resolve("sparat-aventyr.txt"), "heroName=Lancelott\nroom=1\n");
        assertThrows(SaveGameException.class, () -> service.load());
    }

    @Test
    void loadShouldThrowWhenFileHasGarbageLine() throws Exception {
        Files.writeString(tempDir.resolve("sparat-aventyr.txt"), "det här är ingen rad\n");
        assertThrows(SaveGameException.class, () -> service.load());
    }

    @Test
    void loadShouldThrowWhenFileHasUnknownKey() throws Exception {
        Files.writeString(tempDir.resolve("sparat-aventyr.txt"),
                "heroName=Lancelott\nheroHealth=80\nroom=1\nfoo=bar\n");
        assertThrows(SaveGameException.class, () -> service.load());
    }

    @Test
    void loadShouldThrowWhenHealthIsNotANumber() throws Exception {
        Files.writeString(tempDir.resolve("sparat-aventyr.txt"), "heroHealth=många\n");
        assertThrows(SaveGameException.class, () -> service.load());
    }

    @Test
    void loadShouldThrowWhenItemQuantityIsBroken() throws Exception {
        Files.writeString(tempDir.resolve("sparat-aventyr.txt"),
                "heroName=Lancelott\nheroHealth=80\nroom=1\nitem=Sten|noll\n");
        assertThrows(SaveGameException.class, () -> service.load());
    }

    @Test
    void commentsAndBlankLinesShouldBeIgnoredWhenLoading() throws Exception {
        Files.writeString(tempDir.resolve("sparat-aventyr.txt"),
                "# Fangdjupet-sparfil v1\n\nheroName=Lancelott\nheroHealth=80\nroom=1\n");
        GameSnapshot loaded = service.load();
        assertEquals("Lancelott", loaded.heroName());
        assertEquals(80, loaded.heroHealth());
    }

    @Test
    void swedishCharactersShouldSurviveTheRoundTrip() throws SaveGameException {
        GameSnapshot swedish = new GameSnapshot("Lancelott", 100, 1, List.of(),
                List.of(new InventoryEntry("Långt Järnsvärd", 1)));
        service.save(swedish);
        assertEquals(swedish, service.load());
    }
}
