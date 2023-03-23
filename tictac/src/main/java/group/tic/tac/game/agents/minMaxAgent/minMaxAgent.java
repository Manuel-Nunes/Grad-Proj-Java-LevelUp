package group.tic.tac.game.agents.minMaxAgent;

import group.tic.tac.algos.MinMax.MinMax;
import group.tic.tac.game.TicTacToe;

public class minMaxAgent {
  public static int makeMove(char[][] board) {
    if (TicTacToe.isBoardEmpty(board)){
      return 5;
    }
    MinMax maxAgent = new MinMax(board);
    return moveToInt(maxAgent.findBestMove()) ;
  }
  public static int moveToInt(MinMax.Move moveIn){
    return moveIn.row*3+moveIn.col+1;
  }
}
