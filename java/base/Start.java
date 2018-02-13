package base;

import edu.kit.informatik.Main;

import java.util.ArrayList;

/**
 * Starts the game
 * This class needs to be modified if your Main class name or package is different
 */
public class Start {


    /**
     * Starts the game
     * @param gameMode the game mode
     * @param boardSize the board size
     * @param playerCount the player count
     */
    public static void game(String gameMode, String boardSize, String playerCount){
        ArrayList<String> args = new ArrayList<>();
        if(gameMode != null) args.add(gameMode);
        if(boardSize != null) args.add(boardSize);
        if(playerCount != null) args.add(playerCount);

        game(args.toArray(new String[]{}));
    }

    public static void game(String[] args){
        Main.main(args);
    }
}
