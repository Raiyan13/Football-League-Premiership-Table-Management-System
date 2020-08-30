package FootballLeaguePremiershipTableManagementSystem;

public class Entry {
  
  private int position = 0 ;  
  private String teams;
  private int played = 0;
  private int points = 0;
  private double percentage = 0.0;
  private int won = 0;
  private int lost = 0;
  private int drawn = 0;
  private int scoreFor = 0;
  private int scoreAgainst = 0;

    public void setPosition(int position) {
        this.position = position;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public void setPlayed(int won,int lost,int drawn) {
        this.played = won+lost+drawn;
    }

    public void setPoints(int won,int drawn) {
        this.points = won*2+drawn;
    }

    public void setPercentage(double Percentage) {
        this.percentage = Percentage;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public void setScoreFor(int scoreFor) {
        this.scoreFor = scoreFor;
    }

    public void setScoreAgainst(int scoreAgainst) {
        this.scoreAgainst = scoreAgainst;
    }

    public int getPosition() {
        return position;
    }

    public String getTeams() {
        return teams;
    }

    public int getPlayed() {
        return played;
    }

    public int getPoints() {
        return points;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getWon() {
        return won;
    }

    public int getLost() {
        return lost;
    }

    public int getDrawn() {
        return drawn;
    }

    public int getScoreFor() {
        return scoreFor;
    }

    public int getScoreAgainst() {
        return scoreAgainst;
    }
    
    void processResult(int forr,int against){
      double percentage = ((double)forr / (forr + against))*100;
      this.setPercentage(percentage);
    }
}
