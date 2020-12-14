package database;

public class Highscore {
  private int score;
  private String scorer;

  public Highscore(int score, String scorer){
    this.score = score;
    this.scorer = scorer;
  }

  public int getScore() { return score; }
  public String getScorer() { return scorer; }

}
