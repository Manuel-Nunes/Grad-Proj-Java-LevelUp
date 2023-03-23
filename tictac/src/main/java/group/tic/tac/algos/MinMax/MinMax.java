package group.tic.tac.algos.MinMax;

public class MinMax {
    //movement of the agent
    private char[][] board;
    public static class Move{
       public int row, col;
    }

    //constructor for minmax agent
    public MinMax(char[][] gameBoard){
        this.board = gameBoard;
    }

    //symbols for the players
    public static char player = 'X' , opponent = 'O', space = '-';

    // function to check if moves are still available
    private boolean isMoveLeft(char[][] board){
        for(int x = 0; x < 3; x++){
            for(int y= 0; y < 3; y++){
                if(board[x][y] == space){
                    return true;
                }
            }
        }
        return false;
    }

    // evaluation function to check who won
    // returns a negative value if the opponent won and a positive if the player won
    private int evaluate(char[][] board){
        // row check for victory
        for(int row = 0 ; row < 3; row ++){
            if(board[row][0] == board[row][1] && board[row][1] == board[row][2]){
                if(board[row][0] == player){
                    return +10;
                }else if(board[row][0] == opponent){
                    return -10;
                }
            }
        }
        // checking diagonals for victory
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            if(board[0][0] == player){
                return +10;
            }else if(board[0][0] == opponent){
                return -10;
            }
        }
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            if(board[0][2] == player){
                return +10;
            }else if(board[0][2] == opponent){
                return -10;
            }
        }
        // column check for victory
        for(int col = 0 ; col < 3 ; col++){
            if(board[0][col] == board[1][col] && board[1][col] == board[2][col]){
                if(board[0][col] == player){
                    return +10;
                }else if(board[0][col] == opponent){
                    return -10;
                }
            }

        }
        // game still in progress
        return 0;
    }

    // minimax function
    private int minimax(char[][] board, int depth, boolean isMax){
        int score = evaluate(board);
        //check if the maximiser has won the game
        // if true then return its score
        if(score == 10){
            return score;
        }
        if(score == -10){
            return score;
        }
        // if it is a draw
        if(isMoveLeft(board) == false){
            return 0;
        }

        // minimax logic
        if(isMax){
            int best = -1000;
            for(int x = 0; x < 3 ; x++){
                for(int y = 0 ; y < 3; y++){
                    // check for empty cells
                    if(board[x][y] == space){
                        board[x][y] = player;
                        // recursively calling the minimax to choose the max value
                        best = Math.max(best, minimax(board, depth +1, false));
                        // undo the move
                        board[x][y] = space;
                    }
                }
            }
            return best;

        }
        // if its the minimizers move
        else{
            int best = 1000;
            for(int x = 0; x < 3 ; x++){
                for(int y = 0 ; y < 3; y++){
                    // check for empty cells
                    if(board[x][y] == space){
                        board[x][y] = opponent;
                        // recursively calling the minimax to choose the max value
                        best = Math.min(best, minimax(board, depth +1, true));
                        // undo the move
                        board[x][y] = space;
                    }
                }
            }
            return best;
        }
    }

    // function to find the best move
    public Move findBestMove(){
        int bestValue = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // traverse the cells and evaluate minmax;
        // for empty cells return the optimal choice
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                if(board[x][y] == space){
                    board[x][y] = player;
                    int moveValue = minimax(this.board, 0,false);
                    //undo the move
                    board[x][y] = space;

                    if(moveValue > bestValue){
                        bestMove.row = x;
                        bestMove.col = y;
                        bestValue = moveValue;
                    }
                }
            }
        }
        System.out.println("Value of optimal move is " + bestValue);
        return bestMove;
    }

}
