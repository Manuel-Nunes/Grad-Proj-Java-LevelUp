package group.tic.tac.game;

public interface tictacGame {
  public enum GameState {
    Win,
    Lose,
    Draw
  }

  public GameState enterGameLoop();

  //Crosses are first, then nots
  public GameState enterGameLoop(int size, boolean isPlayerOne);
}
