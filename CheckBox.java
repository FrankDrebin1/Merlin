import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class CheckBox extends JPanel implements ItemListener {
  
  public ArrayList<Player> players;
  public ArrayList<JCheckBox> boxes;
  public JCheckBox fail, update;
  public boolean failedMission = false;
  
  public CheckBox(ArrayList<Player> playerArray) {
    super(new BorderLayout());
    
    players = playerArray;
    
    //create an arraylist of check boxes, each one being an option for a player
    boxes = new ArrayList<JCheckBox>();
    
    //a button for whether the mission succeeded
    fail = new JCheckBox("The mission failed.");
    fail.setSelected(false);
    
    for (int i = 0; i < players.size(); i++) {
      
      //on mission button
      boxes.add(new JCheckBox("On the mission."));
      boxes.get(i).setSelected(false);
      
      //voted no button
      boxes.add(new JCheckBox("Voted no when approving the mission."));
      boxes.get(i).setSelected(false);
    }
    
    //a button to update once all other buttons have been done
    update = new JCheckBox("Update");
    update.setSelected(false);
    
    //implement the listener
    fail.addItemListener(this);
    for (int i = 0; i < boxes.size(); i++) {
      boxes.get(i).addItemListener(this);
    }
    update.addItemListener(this);
    
    //Put the check boxes in a column in a panel
    JPanel checkPanel = new JPanel(new GridLayout(0, 1));
    
    //put fail check box on top
    JLabel failLabel = new JLabel();
    failLabel.setText("Did the mission fail?");
    checkPanel.add(failLabel);
    checkPanel.add(fail);
    
    for (int i = 0; i < boxes.size(); i++) {
      
      if (i % 2 == 0) {
        JLabel nameLabel = new JLabel();
        nameLabel.setText(players.get(i / 2).getName());
        checkPanel.add(nameLabel);
      }

      checkPanel.add(boxes.get(i));
    }
    
    checkPanel.add(update);
    
    add(checkPanel, BorderLayout.LINE_START);
    setBorder(BorderFactory.createEmptyBorder(20,20,20,420));
  }
  
  // Listens to the check boxes and changes player stats accordingly
  public void itemStateChanged(ItemEvent e) {
    
    Object source = e.getItemSelectable();
    
    //check if the mission failed
    if (source == fail) {
      failedMission = !failedMission;
      System.out.println("Mission failure:" + failedMission);
    }
    
    //account for who was on the mission and who voted no
    for (int i = 0; i < boxes.size(); i++) {
      
      //who was on the mission
      if (i % 2 == 0 && source == boxes.get(i)) {
        if (failedMission) {
          players.get(i / 2).changeTrustScore(-10); }//if mission fails, take away 10 from their trust score
        else { players.get(i / 2).changeTrustScore(10); }//if mission passed, add 10 to their trust score
      }
      
      //who voted down the mission
      if (i % 2 == 1 && source == boxes.get(i)) {
        if (failedMission) {
          players.get(i / 2).changeTrustScore(10); }//if the mission failed and they voted no, add to their trust score
        else {players.get(i /2).changeTrustScore(-10); }//if the mission passed and they voted no, decrease their trust score
      }
    } 
    
    //update the output
    if (source == update)
      updateAndReset();
  }
  
  //update scanner output and reset GUI
  public void updateAndReset() {
    Runner.showResults();
    
    //whenever we change fail's selection, we must change the variable it is tied to
    fail.setSelected(false);
    failedMission = !failedMission;
    
    update.setSelected(false);
    
    for (int i = 0; i < boxes.size(); i++) {
      boxes.get(i).setSelected(false);
    }
  }
  
  
  /**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event-dispatching thread.
   */
  public static void createAndShowGUI(ArrayList<Player> players) {
    //Create and set up the window.
    JFrame frame = new JFrame("Merlin v1.0");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Create and set up the content pane.
    JComponent newContentPane = new CheckBox(players);
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);
    
    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }
}