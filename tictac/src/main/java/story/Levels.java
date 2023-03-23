package story;

import group.tic.tac.game.TicTacToe;
import group.tic.tac.game.tictacGame;
import group.tic.tac.gui.Panel;
import group.tic.tac.gui.PanelCollector;
import group.tic.tac.utils.Utils;

public class Levels {
    private TicTacToe ticTac;
    private String levelDescription;
    private String enterMessage;

    public Levels(TicTacToe game, String levelDescription, String enterMessage) {
        this.ticTac = game;
        this.levelDescription = levelDescription;
        this.enterMessage = enterMessage;
    }

    // method for startup
    public void loadLevel() {
        tictacGame.GameState gameState;
        Panel pc = new Panel(levelDescription);
        pc.Paddings = new int[] { 1, 1, 1, 1 };
        pc.Margins = new int[] { 1, 1, 1, 1 };

        pc.Generate(true);
        PanelCollector pcl = new PanelCollector();
        pcl.Place(pc);
        Utils.clearConsole();
        Utils.printArray(pcl.renderToString());
        Utils.waitForInput(enterMessage);
        Utils.clearConsole();

        // enter the game loop
        gameState = ticTac.enterGameLoop();
        int count = 0;
        while (ticTac.currentPlayer != TicTacToe.PlayerOne) {
            count++;
            Utils.clearConsole();
            Utils.printArray(pcl.renderToString());
            inspirationFunction(count);
            Utils.waitForInput("Would you like to continue? Press enter to continue.");
            Utils.clearConsole();
            gameState = ticTac.enterGameLoop();
        }
    }

    private void inspirationFunction(int count) {
        switch (count) {
            case 3:
                System.out.println("You got it, Champ. Keep your head up!");
                break;
            case 5:
                System.out.println("Time to get serious Champ. You need to win the next round.");
                break;
            case 7:
                System.out.println("You really suck at this...");
                break;
            case 9:
                System.out.println("This you? IP:" + Utils.getIP() + " Wanna go for drinks?? *wink*");
                break;
            default:
                System.out.print("");
                break;
        }
    }

}
