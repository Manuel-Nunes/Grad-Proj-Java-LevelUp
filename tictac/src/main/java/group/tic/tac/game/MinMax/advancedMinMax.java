package group.tic.tac.game.MinMax;

import java.util.Scanner;

import group.tic.tac.charGui.Panel;
import group.tic.tac.charGui.PanelCollector;
import group.tic.tac.utils.Utils;

import group.tic.tac.game.*;

public class advancedMinMax implements tictacGame {

    private static final int N = 3;
    private static final char[][] board = new char[N][N];
    private static char currentPlayer = 'O';
    private static Scanner scanner;

    public GameState enterGameLoop() {
        initializeBoard();
        GameState state;
        while (true) {
            showGameState();
            makeMove();
            state = isGameOver();
            if (state != null) {
                displayEndGameMessage();
                break;
            }
            switchPlayer();
        }
        scanner.close();
        return state;
    }

    private static void showGameState() {
        System.out.println("\n===================");
        System.out.println("Board positions:\n");
        displayPosBoard(N);
        System.out.println("\n===================");
        System.out.println("\nBoard:\n");
        displayBoard();
        System.out.println("\n===================");
    }

    private static void displayEndGameMessage() {

        // System.out.println("______________________________\n");
        displayBoard();
        String messageOut = "";
        if (isGameOver() == GameState.Win) {
            // System.out.println("\nGame over! Winner: " + currentPlayer);
            messageOut = "Game over! Winner: " + currentPlayer;
        } else {
            // System.out.println("\nGame over! It's a draw!");
            messageOut = "Game over! It's a draw!";
        }
        // System.out.println("______________________________");
        Utils.clearConsole();
        Panel outcomeMesasge = new Panel(messageOut);
        outcomeMesasge.Paddings = new int[] {1,1,1,1};
        outcomeMesasge.Generate(true);
        PanelCollector PC = new PanelCollector();
        PC.Place(outcomeMesasge);

        Panel lastBoard = new Panel(genDisplayBoard());
        lastBoard.Paddings = new int[] { 1, 0, 1, 1 };
        lastBoard.Generate(true);
        PC.Place(lastBoard);

        Utils.printArray(PC.renderToString());
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static void initializeBoard() {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                board[row][column] = '-';
            }
        }
    }

    private static String genDisplayBoard(){

        String sOut = "";
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                sOut += String.format(board[row][column] + " ", row * 3 + column + 1);
            }
            sOut += '\n';
        }
        return sOut;
    }

    private static void displayBoard() {
        // for (int row = 0; row < N; row++) {
        //     for (int column = 0; column < N; column++) {
        //         System.out.print(String.format(board[row][column] + " ", row * 3 + column + 1));
        //     }
        //     System.out.println();
        // }
        System.out.println(genDisplayBoard());
    }

    private static void displayPosBoard(int N) {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                System.out.print(String.format("%" + N + "s", row * 3 + column + 1));
            }
            System.out.println();
        }
    }

    private static int[] getCoordinates(int position) {
        int[][] coordinates = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 },
                { 2, 2 } };
        return coordinates[position - 1];
    }

    private static void makeMove() {
        int position;
        int[] coordinates;
        if (currentPlayer == 'O')
        {
            if (isEmpty())
            {
                coordinates = new int[] { 1,1};
            }
            else{
                MinMax MM = new MinMax(board);
                MinMax.Move toMake = MM.findBestMove();
                coordinates = new int[] {toMake.row, toMake.col};
            }
        }
        else{
            scanner = new Scanner(System.in);
            System.out.println("\nPlayer " + currentPlayer + ", make a move:");
            position = scanner.nextInt();
            coordinates = getCoordinates(position);
        }

        int row = coordinates[0];
        int column = coordinates[1];
        if (board[row][column] == '-') {
            board[row][column] = currentPlayer;
        } else {
            System.out.println("Invalid move, try again!");
            makeMove();
        }
    }

    private static GameState isGameOver() {
        if (hasHorizontalWin() || hasVerticalWin() || hasDiagonalWin()) {
            return GameState.Win;
        } else if (isBoardFull()) {
            return GameState.Draw;
        } else {
            return null;
        }
    }

    private static boolean isBoardFull() {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    private static boolean hasHorizontalWin() {
        for (int row = 0; row < N; row++) {
            if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasVerticalWin() {
        for (int column = 0; column < N; column++) {
            if (board[0][column] != '-' && board[0][column] == board[1][column]
                    && board[1][column] == board[2][column]) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasDiagonalWin() {
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    private static boolean isEmpty(){
        boolean found = false;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                found = board[i][j] != '-';
                if (found)
                    break;
            }
            if (found)
                break;
        }
        return !found;
    }

    @Override
    public GameState enterGameLoop(int size, boolean isPlayerOne) {
        throw new UnsupportedOperationException("Unimplemented method 'enterGameLoop'");
    }
}
