import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;

public class Runner {
  public boolean votedYesFail;
  public boolean votedNoFail;
  public boolean votedYesPass;
  public boolean votedNoPass;
  public boolean wasOnFailedQuest;
  public boolean wasOnPassedQuest;
  public boolean wasNotOnFailedQuest;
  public boolean wasNotOnPassedQuest;
  public static ArrayList<Player> players;
  public static int playerNum;
  
  //hopefully from these options some probability can be generated
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("Merlin v1.0");
    //setup the arraylist of players based on how many there are
    System.out.println("Please enter the number of players for each game");
    
    Scanner keyboard = new Scanner(System.in);
    if(keyboard.hasNextInt())
    {
    playerNum = keyboard.nextInt();
    }
    else
    {System.out.println("Not a number! Please restart the game!");}
    
    //instantiate arraylist of players
    players = new ArrayList<Player>();
    
    //account for nextLine exception error
    keyboard.nextLine();
    
    //add each player to the list with default trust score
    for(int i = 0; i < playerNum; i++) {
      System.out.println("Enter a name for player " + (i + 1) + ". ");
      //here we need to figure out what the default trust score should be
      //I started with a score of 50 but that can be changed
      String tempName = keyboard.nextLine();
      Player ebic = new Player(tempName, 50);
      players.add(ebic);
    }
    //Here we should start the GUI for each round which has the form in it
    //Ideally the beginning part would be a GUI too but it isn't as needed
    CheckBox.createAndShowGUI(players);
    
    showResults();
  }
  
  
  
 static //the logic for each player currently exists within the checkbox.java class
  //finally at the end of each quest, this method should print out
  //the probability for each person that they're evil.
  //A trust score of 50 is normal, 100 ideally is "guaranteed evil" and 0 ideally is "guaranteed good"
  int round = 0;
  public static void showResults() {
	  
    System.out.println("For round " + round + ", the chance that each person is evil is: ");
    for(int i = 0; i < playerNum; i++) {
      //I'm not sure why it says in eclipse that arrayList can't be resolved...
      System.out.println(players.get(i).getName() + " : " + (100 - players.get(i).getTrustScore()) +"%");
      
      //it's doing this twice for some reason
    }
    round++; //so each time it outputs the round increases
  }
  
  
  
}


