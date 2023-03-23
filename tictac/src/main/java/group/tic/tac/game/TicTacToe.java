package group.tic.tac.game;

import java.util.ArrayList;
import java.util.List;

import group.tic.tac.game.agents.playerAgent;
import group.tic.tac.gui.Panel;
import group.tic.tac.gui.PanelCollector;
import group.tic.tac.utils.Utils;

public class TicTacToe implements tictacGame {
  public static char Space = '-', PlayerOne = 'X', PlayerTwo = 'O';
  public int N, inARow;
  public char[][] board;
  public char currentPlayer = PlayerOne;
  public playerAgent OpponentAgent;

  public TicTacToe(){
    this(3,3);
  }

  public TicTacToe(int Size, int WinRequired){
    this.N = Size;
    this.board = new char[Size][Size];
    this.inARow = WinRequired;
  }

  public GameState enterGameLoop() {
    initializeBoard();
    GameState state;
    while (true) {
      Utils.clearConsole();
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
    boardPostion.Paddings = new int[] { 1, 2, 1, 2 };
    boardPostion.Margins = new int[] { 1, 1, 1, 1 };
    boardPostion.Generate(true);

    PC.Place(boardPostion);

    Panel board = new Panel("Board\n" + genDisplayBoard());
    board.Paddings = new int[] { 1, 2, 1, 2 };
    board.Margins = new int[] { 1, 2, 1, 2 };

    board.Generate(true);

    PC.Place(board);

    Utils.printArray(PC.renderToString());
  }

  public void displayEndGameMessage() {

    displayBoard();
    String messageOut = "";
    GameState StateOfGame = isGameOver();
    if (StateOfGame == GameState.Win) {
      messageOut = "Game over! Winner: " + currentPlayer;
    } else if (StateOfGame == GameState.Draw) {
      messageOut = "Game over! It's a draw!";
    } else {

    }

    Utils.clearConsole();
    Panel outcomeMesasge = new Panel(messageOut);
    outcomeMesasge.Paddings = new int[] { 1, 2, 1, 2 };
    outcomeMesasge.Generate(true);
    PanelCollector PC = new PanelCollector();
    PC.Place(outcomeMesasge);

    Panel lastBoard = new Panel(genDisplayBoard());
    lastBoard.Paddings = new int[] { 1, 2, 1, 2 };
    lastBoard.Generate(true);
    PC.Place(lastBoard);

    Utils.printArray(PC.renderToString());
    Utils.waitForInput();
  }

  public void switchPlayer() {
    currentPlayer = (currentPlayer == PlayerOne) ? PlayerTwo : PlayerOne;
  }

  public void initializeBoard() {
    for (int row = 0; row < N; row++) {
      for (int column = 0; column < N; column++) {
        board[row][column] = '-';
      }
    }
  }

  public String genDisplayBoard() {
    String Out = "";
    for (int r = 0;r < N;r++ )
    {
      String sLine = "|";
      for (int c = 0;c < N;c++ )
        sLine += String.format(" %c |", board[r][c], N) ;
      Out += sLine + '\n';
    }
    return Out;
  }

  public void displayBoard() {
    System.out.println(genDisplayBoard());
  }

  public static String displayPosBoard(int N) {
    String Out = "";
    int Length = String.valueOf(N*N).length();
    for (int r = 0;r < N;r++ )
    {
      String sLine = "|";
      for (int c = 0;c < N;c++ )
        sLine += String.format(" %s |", Utils.numToFixLenString(getPosFromCords(new int[] {r,c}, N), Length)) ;
      Out += sLine + '\n';
    }
    return Out;
  }

  public static int[] getCoordinates(int position, int Size) {
    position--;
    return new int[] {position/Size, position %Size};
  }
  
  public static int getPosFromCords(int[] Cords, int Size){
    return Cords[0]*Size+Cords[1]+1;
  }

  public static List<Integer> availableSpots(char[][] board) {
    List<Integer> lstAvail = new ArrayList<Integer>();
    int Size = board[0].length;
    for (int i = 0; i < Size; i++) {
      for (int j = 0; j < Size; j++) {
        if (board[i][j] == '-')
          lstAvail.add(i * Size + j + 1);
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

    int[] coordinates = getCoordinates(position,N);
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
    if (hasWin()) {
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

  public boolean hasWin() {
    int limit = N*N;
    for (int i = 1;i <= limit;i++){
      //DoTLBR Check
      int[] Cords = getCoordinates(i,N);
      if (board[Cords[0]][Cords[1]] != Space && validDiagonalCords(Cords, inARow, N, diagonalEval.TLBR) ){
        char Found = board[Cords[0]][Cords[1]];
        boolean doCheck = true;
        for (int j = 1;j < inARow;j++)
        {
          if (Found != board[Cords[0]+j][Cords[1]+j])
          {
            doCheck = false;
            break;
          }
        }
        if (doCheck)
          return true;
      }

      //DoTRBL Check
      if (board[Cords[0]][Cords[1]] != Space && validDiagonalCords(Cords, inARow, N, diagonalEval.TRBL)){
        char Found = board[Cords[0]][Cords[1]];
        boolean doCheck = true;
        for (int j = 1;j < inARow;j++)
        {
          if (Found != board[Cords[0]+j][Cords[1]-j])
          {
            doCheck = false;
            break;
          }
        }
        if (doCheck)
          return true;
      }

      if (board[Cords[0]][Cords[1]] != Space && validCardinalCords(Cords, inARow, N,true)){
        char Found = board[Cords[0]][Cords[1]];
        boolean doCheck = true;
        for (int j = 1;j < inARow;j++)
        {
          if (Found != board[Cords[0]+j][Cords[1]])
          {
            doCheck = false;
            break;
          }
        }
        if (doCheck)
          return true;
      }

      if (board[Cords[0]][Cords[1]] != Space && validCardinalCords(Cords, inARow, N,false)){
        char Found = board[Cords[0]][Cords[1]];
        boolean doCheck = true;
        for (int j = 1;j < inARow;j++)
        {
          if (Found != board[Cords[0]][Cords[1]+j])
          {
            doCheck = false;
            break;
          }
        }
        if (doCheck)
          return true;
      }
    }
    return false;
  }

  private static enum diagonalEval{
    TLBR,// \
    BRTL,// \
    TRBL,// /
    BLTR // /
  }

  private static boolean validDiagonalCords(int[] Cords, int Required,int Size, diagonalEval EvalDirection){
    Required--;
    switch (EvalDirection){
      case TLBR:{
        if (Cords[0]+Required >= Size)//Space between bottom and start
          return false;
        if (Cords[1]+Required >= Size)// Space between Start and left
          return false;
      }
      break;
      case BRTL:{
        if (Cords[0] - Required < 0)//Space between bottom and start
          return false;
        if (Cords[1] - Required < 0)// Space between Start and left
          return false;
      }
      break;
      case TRBL:{
        if (Cords[0]+Required >= Size)//Space between bottom and start
          return false;
        if (Cords[1]-Required < 0)// Space between Start and left
          return false;
      }
      break;
      case BLTR:{
        if (Cords[0] - Required < 0)//Space between bottom and start
          return false;
        if (Cords[1] + Required >= Size)// Space between Start and left
          return false;
      }
      break;
    }

    return true;
  }

  private static boolean validCardinalCords(int[] Cords, int Required,int Size,boolean Vertical){
    int index = (Vertical)?0:1;
    Required--;
    //Removed check because of the nature of the check is incorrect
    // if (Cords[index]-Required < 0){
    //   return false;
    // }

    if (Cords[index] + Required >= Size){
      return false;
    }

    return true;
  }

  @Override
  public GameState enterGameLoop(int size, boolean isPlayerOne) {
    throw new UnsupportedOperationException("Unimplemented method 'enterGameLoop'");
  }

  // function to check range
  private boolean isInRange(int position) {
    if (position >= 0 && position <= N*N) {
      return true;
    }
    return false;
  }

}
