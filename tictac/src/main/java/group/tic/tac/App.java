package group.tic.tac;

import group.tic.tac.game.TicTacToe;
import group.tic.tac.game.agents.basicTicTacAgent.basicTicTacAgent;

public class App {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.OpponentAgent = basicTicTacAgent::makeMove;
        game.enterGameLoop();
    }
}
