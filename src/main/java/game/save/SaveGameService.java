package game.save;

/**
 * Contract for saving and loading an ongoing game round.
 * TextSaveGameService implements it as a readable text file.
 */
public interface SaveGameService {

    void save(GameSnapshot snapshot) throws SaveGameException;

    GameSnapshot load() throws SaveGameException;

    /**
     * Removes the save file, for example when a run ends in defeat.
     */
    void delete();
}
