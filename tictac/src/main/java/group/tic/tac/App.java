package group.tic.tac;

import group.tic.tac.game.TicTacToe;
import group.tic.tac.game.basicTicTacAgent.basicTicTacAgent;
import group.tic.tac.game.minMaxAgent.minMaxAgent;
public class App {
    public static void main(String[] args) {
        // TicTacToe game = new TicTacToe();
        // game.OpponentAgent = basicTicTacAgent::makeMove;
        // game.enterGameLoop();

        // TicTacToe game = new TicTacToe();
        // game.OpponentAgent = minMaxAgent::makeMove;
        // game.enterGameLoop();

        TicTacToe game = new TicTacToe();
        game.OpponentAgent = minMaxAgent::makeMove;
        game.currentPlayer = TicTacToe.PlayerTwo;
        game.enterGameLoop();

        // tictacGame game = new advancedMinMax();
        // game.enterGameLoop();

        // Panel pan = new Panel("This is my panel\nThis is a secondLine");
        // pan.Paddings = new int[]{1,1,1,1};
        // pan.Margins = new int[]{3,3,3,3};

        // pan.Generate(true);

        // PanelCollector PC = new PanelCollector();
        // PC.Place(pan);

        // Panel panB = new Panel("This is PanB\nLol\nLamou");
        // panB.Paddings = new int[] { 1, 1, 1, 1 };
        // panB.Margins = new int[] { 1,1,1,1 };

        // panB.Generate(true);
        // PC.Place(panB);

        // Utils.clearConsole();
        // Utils.printArray(PC.renderToString());
    }
}
