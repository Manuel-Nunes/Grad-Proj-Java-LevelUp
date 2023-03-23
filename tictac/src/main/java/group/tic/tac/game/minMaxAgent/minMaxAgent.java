package group.tic.tac.game.minMaxAgent;

import group.tic.tac.game.TicTacToe;
import group.tic.tac.game.MinMax.MinMax;

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
