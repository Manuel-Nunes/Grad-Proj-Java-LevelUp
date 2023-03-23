package story;

import group.tic.tac.game.TicTacToe;
import group.tic.tac.game.tictacGame;
import group.tic.tac.gui.Panel;
import group.tic.tac.gui.PanelCollector;
import group.tic.tac.utils.Utils;

public class Levels {
    private TicTacToe ticTac;
    private String levelDescription;

    public Levels(TicTacToe game, String levelDescription) {
        this.ticTac = game;
        this.levelDescription = levelDescription;
    }
    // method for startup
    public void loadLevel(){
        tictacGame.GameState gameState;
        Panel pc = new Panel(levelDescription);
        pc.Paddings = new int[]{1,1,1,1};
        pc.Margins = new int[]{1,1,1,1};

        pc.Generate(true);
        PanelCollector pcl = new PanelCollector();
        pcl.Place(pc);
        Utils.clearConsole();
        Utils.printArray(pcl.renderToString());
        Utils.waitForInput("Would you like to start the game? Press enter to continue");
        Utils.clearConsole();

        //enter the game loop
        gameState = ticTac.enterGameLoop();

        while(gameState != tictacGame.GameState.Win){
            Utils.clearConsole();
            Utils.printArray(pcl.renderToString());
            Utils.waitForInput("Would you like to start the game? Press enter to continue");
            Utils.clearConsole();
            gameState = ticTac.enterGameLoop();
        }
    }

}
