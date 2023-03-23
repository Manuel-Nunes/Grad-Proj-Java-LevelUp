package group.tic.tac.game.agents.basicTicTacAgent;

import java.util.List;

import group.tic.tac.game.TicTacToe;

public class basicTicTacAgent {
  public static int makeMove(char[][] board){
    List<Integer> lstAva = TicTacToe.availableSpots(board);
    return lstAva.get((int) Math.floor(Math.random() * lstAva.size()));
  }
}
