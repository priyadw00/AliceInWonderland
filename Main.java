import java.util.*; 

/**
 *. CSC 212 Final Project: CS in Wonderland!! 
 *
 *  @author Priya Dalal-Whelan/ Lillian Fok
 *. @version Spring 2022
 */

class Main {
  public static void main(String[] args) {
    Adventure adventure = new Adventure();
    System.out.println("Welcome to Wonderland! Move using 'forward', 'back', 'left', 'right'.");
    System.out.println("");
    GameMap map = adventure.getMap();
    Character player = adventure.getPlayer();
    while (map.playing()){
      System.out.println();
      adventure.play();
      if (map.checkWin(player)){
        map.setPlaying(false);
        adventure.printWin();
      }
    }
  }    
}

