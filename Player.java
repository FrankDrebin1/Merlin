//stores data for each player and assignes a score based on how trustable they seem

public class Player {
  
  public String name;
  public int trustScore;
  public boolean votedYes;
  public boolean onMission;

  public Player(String str, int i) {
    name = str;
    trustScore = i;
  }
 
  public String getName() {
    return name;
  }
  
  public int getTrustScore() {
    return trustScore;
  }
  
  public void changeTrustScore(int i) {
    trustScore = trustScore + i;
  }

  public int getTrustScorePercent() {
    double trustPercent = trustScore / 100;
    int trustPercentTwo = trustScore * 100;
    return trustPercentTwo;
  }
}
 
 

