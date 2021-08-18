// --== CS400 File Header Information ==--
// Name: <Shin-Tsz Lucy Kuo>
// Email: <skuo8@wisc.edu>
// Team: <your team name: GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
public class Player implements Comparable<Player>{
    private String playerName;
    private int jerseyNumber;
    private int rating;

    public Player(String playerName, int jerseyNumber, int rating) {
        this.playerName = playerName;
        this.jerseyNumber = jerseyNumber;
        this.rating = rating;
    }

    public Object getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public int getJerseyNumber() {
      return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
      this.jerseyNumber = jerseyNumber;
    }

    @Override
    public int compareTo(Player player) {
      if (this.jerseyNumber > player.jerseyNumber) {
        return 1;
      }
      else if(this.jerseyNumber == player.jerseyNumber) {
        return 0;
      }
      return -1;
    }
    
    public boolean equals(Player player) {
      if(compareTo(player) == 0) {
        return true;
      }
      return false;
    }

}
