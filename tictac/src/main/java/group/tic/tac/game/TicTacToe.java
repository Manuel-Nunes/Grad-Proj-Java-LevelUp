package group.tic.tac.game;

import java.util.ArrayList;
import java.util.List;

import group.tic.tac.game.agents.playerAgent;
import group.tic.tac.gui.Panel;
import group.tic.tac.gui.PanelCollector;
import group.tic.tac.utils.Utils;

public class TicTacToe implements tictacGame {
  public static char Space = '-', PlayerOne = 'X', PlayerTwo = 'O';
  public final int N = 3;
  public final char[][] board = new char[N][N];
  public char currentPlayer = PlayerOne;
  public playerAgent OpponentAgent;

  public GameState enterGameLoop() {
    initializeBoard();
    GameState state;
    while (true) {
      showGameState();
      this.makeMove();
      state = isGameOver();
      if (state != null) {
        displayEndGameMessage();
        break;
      }
      switchPlayer();
    }
    return state;
  }

  public void showGameState() {
    PanelCollector PC = new PanelCollector();

    Panel boardPostion = new Panel("Position\n" + displayPosBoard(this.N));
    boardPostion.Paddings = new int[] { 1, 1, 1, 1 };
    boardPostion.Margins = new int[] { 1, 1, 1, 1 };
    boardPostion.Generate(true);

    PC.Place(boardPostion);

    Panel board = new Panel("Board\n" + genDisplayBoard());
    board.Paddings = new int[] { 1, 1, 1, 1 };
    board.Margins = new int[] { 1, 1, 1, 1 };

    board.Generate(true);

    PC.Place(board);

    Utils.printArray(PC.renderToString());
  }

  public void displayEndGameMessage() {

    displayBoard();
    String messageOut = "";
    if (isGameOver() == GameState.Win) {
      messageOut = "Game over! Winner: " + currentPlayer;
    } else {
      messageOut = "Game over! It's a draw!";
    }

    Utils.clearConsole();
    Panel outcomeMesasge = new Panel(messageOut);
    outcomeMesasge.Paddings = new int[] { 1, 1, 1, 1 };
    outcomeMesasge.Generate(true);
    PanelCollector PC = new PanelCollector();
    PC.Place(outcomeMesasge);

    Panel lastBoard = new Panel(genDisplayBoard());
    lastBoard.Paddings = new int[] { 1, 0, 1, 1 };
    lastBoard.Generate(true);
    PC.Place(lastBoard);

    Utils.printArray(PC.renderToString());
    Utils.waitForInput();
  }

  public void switchPlayer() {
    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
  }

  public void initializeBoard() {
    for (int row = 0; row < N; row++) {
      for (int column = 0; column < N; column++) {
        board[row][column] = '-';
      }
    }
  }

  public String genDisplayBoard() {

    String sOut = "";
    for (int row = 0; row < N; row++) {
      for (int column = 0; column < N; column++) {
        sOut += String.format(board[row][column] + " ", row * 3 + column + 1);
      }
      sOut += '\n';
    }
    return sOut;
  }

  public void displayBoard() {
    System.out.println(genDisplayBoard());
  }

  public String displayPosBoard(int N) {
    String Out = "";
    for (int row = 0; row < N; row++) {
      for (int column = 0; column < N; column++) {
        Out += String.format("%" + N + "s", row * 3 + column + 1);
      }
      Out += '\n';
    }
    return Out;
  }

  public int[] getCoordinates(int position) {
    int[][] coordinates = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 },
        { 2, 2 } };
    return coordinates[position - 1];
  }

  public static List<Integer> availableSpots(char[][] board) {
    List<Integer> lstAvail = new ArrayList<Integer>();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == '-')
          lstAvail.add(i * 3 + j + 1);
      }
    }
    return lstAvail;
  }

  private void makeMove() {
    int position = -1;
    if (currentPlayer == 'O') {
      position = this.OpponentAgent.makeMove(this.board);
    } else {
      position = Utils.getUserInt("\nPlayer " + currentPlayer + ", make a move:");
      while (!isInRange(position)) {
        System.out.println("Please choose an available spot");
        position = Utils.getUserInt("\nPlayer " + currentPlayer + ", make a move:");
      }
    }

    int[] coordinates = getCoordinates(position);
    int row = coordinates[0];
    int column = coordinates[1];
    if (board[row][column] == '-') {
      board[row][column] = currentPlayer;
    } else {
      System.out.println("Invalid move, try again!");
      makeMove();
    }
  }

  public GameState isGameOver() {
    if (hasHorizontalWin() || hasVerticalWin() || hasDiagonalWin()) {
      return GameState.Win;
    } else if (isBoardFull(this.board)) {
      return GameState.Draw;
    } else {
      return null;
    }
  }

  public static boolean isBoardFull(char[][] board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == Space) {
          return false;
        }
      }
    }
    return true;
  }

  public static boolean isBoardEmpty(char[][] board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] != Space) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean hasHorizontalWin() {
    for (int row = 0; row < N; row++) {
      if (board[row][0] != Space && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
        return true;
      }
    }
    return false;
  }

  public boolean hasVerticalWin() {
    for (int column = 0; column < N; column++) {
      if (board[0][column] != Space && board[0][column] == board[1][column]
          && board[1][column] == board[2][column]) {
        return true;
      }
    }
    return false;
  }

  public boolean hasDiagonalWin() {
    if (board[0][0] != Space && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      return true;
    }
    if (board[0][2] != Space && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      return true;
    }
    return false;
  }

  @Override
  public GameState enterGameLoop(int size, boolean isPlayerOne) {
    throw new UnsupportedOperationException("Unimplemented method 'enterGameLoop'");
  }

  // function to check range
  private boolean isInRange(int position) {
    if (position >= 0 && position <= 9) {
      return true;
    }
    return false;
  }

}
