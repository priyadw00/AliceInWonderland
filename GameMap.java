/**
 *. Class to build a map of the game 
 *
 *  @author Priya Dalal-Whelan/ Lillian Fok
 *. @version Spring 2022
 */

import com.google.common.graph.*;

import java.awt.desktop.PrintFilesHandler;
import java.util.Scanner;

/** Class to hold Locations */

public class GameMap {

  private boolean playing = true;
  
/* Ingredients that are needed to win the game */
  Item sugar = new Item("sugar");
  Item flour = new Item("flour");
  Item butter = new Item("butter");
  Item egg = new Item("egg");
  Item mushroom = new Item("mushroom");
  Item roses = new Item("roses");

  /* Other items used in the game */
  Item recipe_book = new Item("recipe book");
  Item eat_me = new Item("pie");
  Item drink_me = new Item("bottle");
  Item gloves = new Item("gloves");
  Item monocle = new Item("monocle");
  Item CS_homework = new Item("CS homework");
  

  /* All the locations */
  private Location house = new Location ("house", "You are sitting in your kitchen, cozy and warm. Outside, birds are chirping on a beautiful spring day. It's the perfect weather for cake for you and your pet snail. But what will you bake it with? \n As if summoned by your thoughts, a large white rabbit rushes from behind you, out the front door, dashing across the lawn, then disappears. Will you follow? Use 'forward' or 'go'.");

  private Location rabbit_hole = new Location ("rabbit hole", "You dash after the rabbit. You follow it to a hole in the ground, and hesitate only briefly before jumping. Now you're falling down the hole! You pass book shelves, cupboards, an empty jar of orange marmalade. \n You're still falling. \n You're still falling \n You're still falling. \n ...at last you reach the bottom. \n You land in a hallway.");

  private Location hallway = new Location ("hallway",  "You find yourself in a long, low hall, which is lit up by a row of lamps hanging from the roof. You walk down the middle, wondering how you will ever get out again. \n Suddenly you come upon a little three-legged table, made of solid glass. To your left is a large door and to your right is a tiny door. Which door will you try to go through?");
  
  private Location garden_door = new Location("garden door", "This door leads into a small passage, not much larger than a rat-hole: you look along the passage into the loveliest garden you ever saw.");

  private Location tear_pool_door = new Location("tear pool door", "You make your way over and put an ear up to the closed door. Behind it you hear the faint sounds of splashing.");
  
  private Location tear_pool = new Location ("tear pool", "After passing through the door, you find yourself standing on the bank of a river of tears. Somebody inconsolable must've been here long ago. The waters teem with detritus of varying shapes and colors. ");

  private Location garden = new Location ("garden", "You wander about among beds of bright flowers and cool fountains. A large cat sits in a tree, grinning from ear to ear. You decide to ask it for help. \n \"In that direction,” the Cat says, waving its paw round to your left, “lives a Hatter: and in that direction,” waving the other paw, “lives a caterpillar. Visit either you like: they’re both mad.\" \n It vanishes quite slowly, beginning with the end of the tail, and ending with the grin, which remains some time after the rest of it had gone. \n " );

  private  Location caterpillar = new Location ("caterpillar", "There is a large mushroom growing ahead, taller than yourself; you look under it, and on both sides of it, and behind it, it occurs to you that you might as well look and see what is on the top of it. You stretch yourself up on tiptoe, and peep over the edge of the mushroom, and your eyes immediately meet those of a large caterpillar, that is sitting on the top with its arms folded, quietly smoking a long hookah, and taking not the smallest notice of you or of anything else. \n You and the caterpillar look at each other for some time in silence: at last the Caterpillar takes the hookah out of its mouth. In a languid, sleepy voice, he says he'll give you a piece of the mushroom if only you answer a riddle. \n Type 'riddle' to answer. ");

  private String caterpillar_solved = ("The caterpillar is smoking his hookah. He's given you some of his mushroom. He glances at you skeptically and waves you off.");

  private  Location tea_party = new Location ("tea party", "You walk towards the Hatter's house.  There is a table set out under a tree in front of the house, and the March Hare and the Hatter are having tea at it: a Doormouse is sitting between them, fast asleep, and the other two are using it as a cushion. You ask them if you can borrow sugar. They say they'll give you a suger cube if you answer their riddle. To answer, type 'riddle'.");

  private String tea_party_solved = "The mad hatter, the March Hare and the doormouse are sitting for tea. Pleased that you've solved their riddle, they are having a merry time. You hear them mention the Queen's famed rose gardens, further down the path.";

  
    private  Location rose_gardens = new Location ("rose gardens", "You enter the Queen's rose gardens. You are standing among rows and rows of beautifully manicured rose bushes of all the colors of the rainbow. Soldiers stand stationed at regular intervals, wilting slightly under the bright sun. You can hear the sounds of friendly sports rivalry nearby.");


   private Location croquet_game = new Location ("croquet game", " You have never seen such a curious croquet-ground in your life; the balls are live hedgehogs, the mallets live flamingoes, and the card soldiers have to double themselves up and to stand on their hands and feet, to make the arches.  \n Before you start playing, the queen declares that each player will have to solve a riddle to win a special prize. To answer, type 'riddle'. ");


  private String croquet_game_solved = "The court continues to play the most curious croquet game you have ever seen.";

  private Location closed_rabbit_hole = new Location ("closed rabbit hole" , "You can't climb back up the rabbit hole. Guess you have to keep exploring. Use 'go' to continue.");

  private Location garden_door_closed = new Location("closed garden door", "You cannot even get your head through the doorway; and even if your head would go through, it would be of very little use without your shoulders. You should find a way to shrink!");


   /** Constructor */
  
  public GameMap() {
    
    //add the neighbors to each Location
    house.addFront(rabbit_hole);
    rabbit_hole.addFront(hallway);
    rabbit_hole.addBack(house);
    hallway.addLeft(tear_pool_door);
    tear_pool_door.addFront(tear_pool);
    tear_pool_door.addBack(hallway);
    tear_pool.addBack(tear_pool_door);
    hallway.addRight(garden_door);
    garden_door.addFront(garden_door_closed);
    garden_door_closed.addBack(garden_door);
    garden_door.addBack(hallway);
    hallway.addBack(closed_rabbit_hole);
    closed_rabbit_hole.addFront(hallway);
    garden.addLeft(tea_party);
    garden.addRight(caterpillar);
    garden.addBack(garden_door);
    caterpillar.addBack(garden);
    tea_party.addFront(rose_gardens);
    tea_party.addBack(garden);
    rose_gardens.addFront(croquet_game);
    rose_gardens.addBack(tea_party);
    croquet_game.addBack(rose_gardens);
    
    //add items to the locations
    house.addItem(recipe_book);
    house.addItem(flour);
    hallway.addItem(eat_me);
    tear_pool.addItem(gloves);
    tear_pool.addItem(monocle);
    tear_pool.addItem(butter);
    tear_pool.addItem(CS_homework);
    rose_gardens.addItem(roses);

    //add riddles
    tea_party.setRiddle(true);
    caterpillar.setRiddle(true);
    croquet_game.setRiddle(true);

    // add prizes 
    caterpillar.setPrize(mushroom);
    tea_party.setPrize(sugar);
    croquet_game.setPrize(egg);
    
    
    //add item descriptions

    recipe_book.setDescription("On the counter there is a recipe book. Use 'take' to pick it up.");
      eat_me.setDescription("On the table is a very small pie, on which the words “EAT ME” are beautifully marked.");
    gloves.setDescription("A pair of white gloves floats by.");
    monocle.setDescription("An rusty monocle floats by.");
    CS_homework.setDescription("Your old CS homework floats by.");
    butter.setDescription("A butter stick of dubious quality floats by.");
    flour.setDescription("A dusty bag of flour sits in your pantry.");
    roses.setDescription("A smattering of fallen rose petals lie near the path. A rambunctious creature may have caused them to fall after colliding with the rose bush.");

  
  }

  /** whether game is still going aka player alive */
  public boolean playing() {
    return playing;
  }

  /** sets the playing value */
  public void setPlaying(Boolean bool){
    this.playing = bool; 
  }

  
  /** returns the starting location of the game */
  public Location getStart() {
    return house;
  }

  /** returns the left neighbor of current location*/
  public Location getLeftNeighbor(Location loc){
    return hallway;
  }

   /** returns the right neighbor of current location*/
  public Location getRightNeighbor(Location loc){
    return hallway;
  }
  
 /** takes in curent locatation and asks the user to solve appropriate riddle for that location. returns true when the user has solved the riddle*/
  public Boolean solveRiddle(Location here) {
    boolean going = true;
    String right_answer = ""; 
    String solved_description = null;
    Scanner sc = new Scanner(System.in);
    Item prize = here.getPrize();
    if (here == tea_party) {
      System.out.println("“There is a house. One enters it blind and comes out seeing. What is it?”");
      right_answer = "school";
      solved_description = tea_party_solved;
    }
    else if (here == caterpillar) {
      System.out.println("“This thing all things devours, \n Birds, beasts, trees, flowers, \n Gnaws iron, bites steel, \n Grinds hard stones to meal, \n Slays king, ruins town, \n And beats mountain down.”");
      right_answer = "time";
      solved_description = caterpillar_solved;
    }
     else if (here == croquet_game){
      System.out.println("“There is a man found dead in a circular house. The detective interviews the cook, maid, and babysitter. The cook said he couldn't have done it because he was preparing the meal. The maid said she couldn't have done it because she was dusting the corners. The babysitter said she couldn't because she was playing with the children. Who was lying?”");
    right_answer = "maid";
    solved_description = croquet_game_solved;
    }
    while (going) {
      String answer = sc.nextLine();
      if (answer.equals(right_answer)) {
        System.out.println("Good job! Riddle solved. Your prize has been placed in your backpack.");
        here.setDescription(solved_description);
        going = false; 
        return true;
      }
      else if (answer.equals("leave")) {
        System.out.println("You decline to answer the riddle at this time. You decide to come back later.");
        going = false;
        return false;
      }
      else {
        System.out.println("Incorrect answer. Guess again or type 'leave' to exit.");
      }
    }
    return false;
  }

  /** checks if player backpack has ingredients in order to print in progress recipe book */
  public String recipeBook(Character player) {
    String book = "INGREDIENTS NEEDED: \n";
    if (player.getBackpack().contains(flour)) {
      book += "\nX  6 cups flour";
    }    else {
      book += "\n   6 cups flour";
    }
    if (player.getBackpack().contains(sugar)) {
      book += "\nX  3 cups sugar";
    }    else {
      book += "\n   3 cups sugar";
    }
    if (player.getBackpack().contains(butter)) {
      book += "\nX  1 stick butter";
    }    else {
      book += "\n   1 stick butter";
    }
    if (player.getBackpack().contains(egg)) {
      book += "\nX  3 eggs";
    }    else {
      book += "\n   3 eggs";
    }
    if (player.getBackpack().contains(mushroom)) {
      book += "\nX  ? mushrooms";
    }    else {
      book += "\n   ? mushrooms";
    }
    if (player.getBackpack().contains(roses)) {
      book += "\nX  ? roses";
    }    else {
      book += "\n   ? roses";
    }
    return book;
  }

  /** prints each item in the players backpack */
  public void printBackpack(Character player) {
    System.out.println("Your backpack contains: ");
    System.out.println();
    //loop thru each item in array list 
    for (Item it: player.getBackpack()) {
      System.out.println(it);
    }
  }
/** checks if backpack contains all the things needed ot win */
   public boolean checkWin(Character character){
    if (character.getBackpack().contains(sugar) & character.getBackpack().contains(flour)){
      return true;
    }
    else {
      return false;
    }
  }

  /** return hallway location in order to validate */
  public Location getHallway() {
    return hallway;
  }

  /** whether or not player has recipe book */
  public boolean hasRecipe(Character player) {
    return (player.hasItem(recipe_book));
  }

  /** add the closed rabbit hole that blocks the player from climbing back out */
  public void closeRabbitHole(){
    hallway.addBack(closed_rabbit_hole);
    closed_rabbit_hole.addFront(hallway);
  }

  /** returns true if the player has eat me pie and eats it */
  public boolean eatMe(Character player) {
    //if has pie
    if (player.hasItem(eat_me)) {
      //remove from backpack
      player.removeItem(eat_me);
      //update locations
      garden_door.addFront(garden);
      garden.addBack(garden_door);
      return true;
    }
    else {
      return false;
    }
  }




  



}
