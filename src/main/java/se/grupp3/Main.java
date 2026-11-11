package se.grupp3;

import characters.Hero;
import characters.Knight;
import game.GameEngine;
import game.SnapshotRestorer;
import game.events.ConsoleGameLogger;
import game.menu.MenuChoice;
import game.save.GameSnapshot;
import game.save.SaveGameException;
import game.save.SaveGameService;
import game.save.TextSaveGameService;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * The game console: the main menu that runs everything!
 */
public class Main {

    private static final Path SAVE_FILE = Path.of("saves", "sparat-aventyr.txt");
    private static final Scanner scanner = new Scanner(System.in);

    private GameEngine engine;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        SaveGameService saveService = new TextSaveGameService(SAVE_FILE);
        IO.println("Välkommen till Riddarspelet!🤺🏰🫅🏹");
        boolean running = true;
        while (running) {
            printMenu();
            MenuChoice choice = MenuChoice.parse(scanner.nextLine());
            switch (choice) {
                case NEW_ADVENTURE -> engine = startNewAdventure();
                case CONTINUE -> engine = continueAdventure(saveService);
                case SHOW_EQUIPMENT -> showEquipment();
                case NEXT_BATTLE -> runNextBattle(saveService);
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

    private GameEngine startNewAdventure() {
        IO.print("Vad heter din hjälte? ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Riddaren";
        }
        GameEngine newEngine = GameEngine.newGame(name);
        newEngine.addListener(new ConsoleGameLogger());
        IO.println(name + " kliver ner i Fangdjupets mörka djup...");
        return newEngine;
    }

    private GameEngine continueAdventure(SaveGameService saveService) {
        try {
            GameSnapshot snapshot = saveService.load();
            Knight hero = SnapshotRestorer.restore(snapshot);
            GameEngine restoredEngine = new GameEngine(hero, snapshot.roomNumber(), snapshot.defeatedEnemies());
            restoredEngine.addListener(new ConsoleGameLogger());
            IO.println("Välkommen tillbaka, " + hero.getName() + "! (" + hero.getHealth() + " hp)");
            return restoredEngine;
        } catch (SaveGameException e) {
            IO.println("Kunde inte läsa in sparat äventyr: " + e.getMessage());
            return engine; // keep the current game if there is one
        }
    }

    private void showEquipment() {
        if (engine == null) {
            IO.println("Du måste starta ett äventyr först!");
            return;
        }
        Hero hero = engine.getHero();
        IO.println(hero.getName() + " har " + hero.getHealth() + " hp. Rum " + engine.getRoomNumber()
                + " av " + GameEngine.TOTAL_ROOMS + ".");
        hero.getInventory().printInventory();
    }

    private void runNextBattle(SaveGameService saveService) {
        if (engine == null) {
            IO.println("Du måste starta ett äventyr först!");
            return;
        }
        int roomBeforeBattle = engine.getRoomNumber();
        engine.nextBattle();
        if (engine.isGameOver()) {
            IO.println("Spelet är över!");
            saveService.delete();
            engine = null;
            return;
        }
        // Autosave only when a room was actually cleared
        if (engine.getRoomNumber() > roomBeforeBattle) {
            saveGame(saveService, "(Framstegen sparades)");
        }
    }

    private void saveOnExit(SaveGameService saveService) {
        if (engine == null) {
            IO.println("Välkommen tillbaka!");
            return;
        }
        saveGame(saveService, "Ditt äventyr sparades. Välkommen tillbaka!");
    }

    private void saveGame(SaveGameService saveService, String successMessage) {
        try {
            saveService.save(engine.snapshot());
            IO.println(successMessage);
        } catch (SaveGameException e) {
            IO.println("Kunde inte spara: " + e.getMessage());
        }
    }
}
