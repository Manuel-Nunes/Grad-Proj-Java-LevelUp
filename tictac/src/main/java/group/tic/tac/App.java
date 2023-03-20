package group.tic.tac;

import group.tic.tac.game.basicTicTacToe;
import group.tic.tac.game.tictacGame;

public class App {
    public static void main(String[] args) {
        tictacGame game = new basicTicTacToe();
        game.enterGameLoop();
    }
}
