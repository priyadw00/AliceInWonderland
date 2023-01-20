/**
 *. Class runs GameMap and Character,  
 *
 *  @author Priya Dalal-Whelan/ Lillian Fok
 *. @version Spring 2022
 */


import java.util.Scanner; 
import com.google.common.graph.*;


public class Adventure {

  private GameMap map = null;

  private Character player = null;

  public Scanner scanner = new Scanner(System.in);
  
  
  /** Constructor */
  Adventure () {

    //builds the map and fills it in w coded locations 
    map = new GameMap();

    //create and place player
    player = new Character();
    player.setLocation(map.getStart());
    
  }
  
//* getter for map */ 
  public GameMap getMap(){
    return map;
  }
//* getter for player */ 
  public Character getPlayer(){
    return player;
  }

  
//* takes input from the user and returns a String command, or takes input again until it receives a valid command */ 

  public String get_input(){
    String input;
    String command = "";
    input = scanner.nextLine();
    //parse as all lower case
    input.toLowerCase();
    //directions commands
    if (input.equals("go forward") | input.equals("move forward") | input.equals("forward") | input.equals("go")){
      command = "forward";
    }
    else if (input.equals("go backward") | input.equals("move backward") | input.equals("backward") | input.equals("back") | input.equals("go back")){
        command = "back";
      }
    else if(input.equals("go right" ) | input.equals("move right") | input.equals("right")){
      command = "right";
    }
    else if(input.equals("go left") | input.equals("move left") | input.equals("left")){
      command = "left";
    }
    //pick up item command
    else if (input.equals("pick up") | input.equals("take") | input.equals("get")) {
      command = "take";
    }
    //riddle command
    else if (input.equals("answer riddle") | input.equals("riddle")) {
      command = "riddle";
    }
    //utility commands
    else if (input.equals("backpack") | input.equals ("check backpack") | input.equals("inventory")) {
      command = "backpack";
    }
    else if (input.equals("recipe") | input.equals("recipe book")) {
      command = "recipe";
    }
    else if (input.equals("eat pie") | input.equals("eat me") | input.equals("eat")) {
      command = "eat";
    }
    else if (input.equals("")) {
      command = "";
    }
    else {
      System.out.println("Invalid command. Try one of the following directions: 'forward', 'left', 'right', 'back'. Use 'backpack' or 'recipe book' to check your progress. Hit Enter to continue.");
      input = get_input(); 
    }
    return command ;
  }


  
  /** one round of the game */
  public void play() {
          
System.out.println(player.getLocation().print_description() + "\n \n");
    System.out.print("> ");
    String command = get_input();
    //check which command to do 
    if (command.equals("left") | command.equals("right") | command.equals("back") | command.equals("forward"))
    { 
        move(command);
    }
    else if (command.equals("take")) {
      pickUp();
    }
    else if (command.equals("riddle")) {
      if (player.getLocation().hasRiddle()){//if riddle is true
        boolean solved = map.solveRiddle(player.getLocation()); 
        if (solved) {
          player.addItem(player.getLocation().getPrize());
          player.getLocation().setRiddle(false);
        }
      }
      else {
        System.out.println("No riddle here, try something else.");
      }
    }
    else if (command.equals("eat")) {
      //if the eat me function works, print complete message
      if (map.eatMe(player)) {
        System.out.println("You devour the 'EAT ME' pie in two bites. The hallway grows cavernously large as you begin to shrink. Finally, when your world stops moving, you think you might be able to fit through that tiny door.");
      }
      else {
        System.out.println("There's nothing you can eat, try something else.");
      }
    }
    else if (command.equals("backpack")) {
     map.printBackpack(player);
    }
    else if (command.equals("recipe")) {
      if (map.hasRecipe(player)) {
        System.out.println(map.recipeBook(player));
      }
      else {
        System.out.println("No recipe book found.");
      }
    }
  }


  
  /** updates player to new location, depending on direction input */
  public void goTo(String direction) {
    if (direction == "back"){
      //if there is a back, update location
      if (player.getLocation().getBack() != null){
        Location back = player.getLocation().getBack();
      player.setLocation(back); 
      }   
      else {
        System.out.println("You can't go back from here.");
      }
    }    
    else if (direction == "forward") {
      if (player.getLocation().getFront() != null) {
        Location front = player.getLocation().getFront();
        player.setLocation(front);
        //if player is entering the hallway, delete previous connection to rabbit hole 
        //this only happens once
        // if (front == map.getHallway()) {
        //   map.closeRabbitHole();
        // }
      }
      else {
        System.out.println("You can't go forward from here.");
      }
    }
    else if (direction == "left") {
      if (player.getLocation().getLeft() != null) {
        Location left = player.getLocation().getLeft();
        player.setLocation(left);
      }
      else {
        System.out.println("You can't go left from here.");
      }
    }
    else if (direction == "right") {
      if (player.getLocation().getRight() != null) {
        Location right = player.getLocation().getRight();
        player.setLocation(right);
      }
      else{
        System.out.println("You can't go right from here.");
      }
    }
    else {
      throw new RuntimeException("Invalid direction input.");
    }
  }

  

  /* accepts direction command and moves player one room in that direction */ 
  
  public void move(String command) {
    if (command.equals("left")){
      this.goTo("left"); //updates player location
    }
    else if (command.equals("back")){  
      this.goTo("back");
    }
    else if (command.equals("forward")) {
      this.goTo("forward");
    }
    else if (command.equals("right")) {
      this.goTo("right");
    }
    else {
      throw new RuntimeException("Invalid move direction input.");
    }
  }

  /** Places an item at the current location into backpack */
  public void pickUp() {
    //get the thing
    Item item = player.getLocation().getItems().get(0);
    //delete the thing from location
    player.getLocation().removeItem(item);
    //put the thing in backpack
    player.addItem(item);
    System.out.println("You are now holding " + item + ".");
    
  }

  /** Prints the win message and ascii art at the conclusion of the game */
  public void printWin() {
    System.out.println("");
    System.out.println("                                           `'.       ");
    System.out.println("                                      .`' ` * .    ");
    System.out.println("                                     :  *  *|  :    ");
    System.out.println("                                      ' |  || '    ");
    System.out.println("                                       `|~'||'    ");
    System.out.println("                                       v~v~v~v    ");
    System.out.println("     _____                             !@!@!@!    ");
    System.out.println("   .'     `.                          _!_!_!_!_     ") ;       
    System.out.println("  /  .-=-.  \\   \\ __                 |  ||    ||     ");
    System.out.println("  | (  C\\ \\  \\_.'')                  |  ||   |||     ");
    System.out.println(" _\\  `--' |,'   _/                   }{{{{}}}{{{   ");
    System.out.println("/__`.____.'__.-');                     __||__    ");
    System.out.println("");
    System.out.println("Congratulations! You finally have all you need to bake your cake. With a piercing whistle, you communicate your whereabouts to your pet snail. After a little while (or more like a long while), he arrives ready to bring you home. \n A long nap later, you awake back in your kitchen. It seems that you're back to your normal size. \n As you busily mix in the flour, butter, and eggs, you reflect upon all the sights you saw today. As you add your secret touches of rose and mushroom, you think with fondness of the creatures you met. Finally, pleased with your efforts, you enjoy your beautiful cake in the magical and weird world you live in.");
  }
 
   

 

   
    

}