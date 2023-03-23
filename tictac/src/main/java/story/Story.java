package story;

import group.tic.tac.algos.MinMax.MinMax;
import group.tic.tac.game.TicTacToe;
import group.tic.tac.game.agents.basicTicTacAgent.basicTicTacAgent;
import group.tic.tac.game.agents.minMaxAgent.minMaxAgent;
import java.util.ArrayList;

public class Story {

    private ArrayList<Levels> lstLevels = new ArrayList<>();

    public Story() {
        initializeStory();
    }

    public void initializeStory() {
        TicTacToe game = new TicTacToe();
        game.OpponentAgent = basicTicTacAgent::makeMove;
        Levels lvl = new Levels(game, "Welcome to the Tic Tac Toe tournament, you are now in the knockout round.",
                "Would you like to start the game? Press enter to continue.");
        lstLevels.add(lvl);

        // new level (intermediate)
        TicTacToe game2 = new TicTacToe();
        game2.OpponentAgent = minMaxAgent::makeMove;
        Levels lvl2 = new Levels(game2, "Well done!!!, you are now in the staging round.",
                "Would you like to continue the game? Press enter to continue.");
        lstLevels.add(lvl2);

        // final boss stage
        TicTacToe game3 = new TicTacToe();
        game3.OpponentAgent = minMaxAgent::makeMove;
        game3.currentPlayer = TicTacToe.PlayerTwo;
        Levels lvl3 = new Levels(game3, "Good luck!!!, you are now about to face the undefeated champion.",
                "Would you like to continue the game? Press enter to continue.");
        lstLevels.add(lvl3);
    }

    public void startStory() {
        for (Levels lvl : lstLevels) {
            lvl.loadLevel();
        }
    }

}
