package group.tic.tac;

import group.tic.tac.game.MinMax.advancedMinMax;
import group.tic.tac.game.*;
public class App {
    public static void main(String[] args) {
        // tictacGame game = new basicTicTacToe();
        // game.enterGameLoop();
        tictacGame game = new advancedMinMax();
        game.enterGameLoop();
    }
}
