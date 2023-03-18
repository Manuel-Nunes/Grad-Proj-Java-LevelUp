package group.tic.tac.game.MinMax;

public class main {
    public static void main(String[] args){
        // main class for testing purpose
        System.out.println("hello minimax");
        char board[][] =
                {{ 'X', 'O', 'X' },
                { 'O', 'O', 'X' },
                { '_', '_', '_' }};

        MinMax minMaxAgent = new MinMax(board);
        MinMax.Move bestMove = minMaxAgent.findBestMove();

        System.out.printf("The Optimal Move is :\n");
        System.out.println("Row: " + bestMove.row);
        System.out.println("Col: " + bestMove.col);
    }

}
