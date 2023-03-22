package group.tic.tac;

import java.util.List;

import group.tic.tac.charGui.Panel;
import group.tic.tac.charGui.PanelCollector;
import group.tic.tac.game.*;
import group.tic.tac.utils.Utils;
public class App {
    public static void main(String[] args) {
        // TicTacToe game = new TicTacToe();
        // game.OpponentAgent = (char[][] board)->{
        //     List<Integer> lstAva = TicTacToe.availableSpots(board);
        //     return lstAva.get((int) Math.floor(Math.random() * lstAva.size()));
        // };
        // game.enterGameLoop();
        // tictacGame game = new advancedMinMax();
        // game.enterGameLoop();

        Panel pan = new Panel("This is my panel\nThis is a secondLine");
        pan.Paddings = new int[]{1,1,1,1};
        pan.Margins = new int[]{3,3,3,3};

        pan.Generate(true);

        PanelCollector PC = new PanelCollector();
        PC.Place(pan);

        Panel panB = new Panel("This is PanB\nLol\nLamou");
        panB.Paddings = new int[] { 1, 1, 1, 1 };
        panB.Margins = new int[] { 1,1,1,1 };

        panB.Generate(true);
        PC.Place(panB);

        Utils.clearConsole();
        Utils.printArray(PC.renderToString());
    }
}
