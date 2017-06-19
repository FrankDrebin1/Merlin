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
 //the trust score indicates how trustworthy they are. The higher the number, the more likely they are to be evil. 
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
 
 

