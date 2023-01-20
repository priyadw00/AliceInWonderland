/**
 *. Class to represent a character and its inventory 
 *
 *  @author Priya Dalal-Whelan/ Lillian Fok
 *. @version Spring 2022
 */
  
import java.util.ArrayList;

public class Character{

   /**  location */
  private Location location; 

   /** inventory backpack */ 
  private ArrayList<Item> backpack = null; 

  /** constructor */
  public Character(){
    location = null;
    backpack = new ArrayList<Item>();
  }

  /** set location */
  public void setLocation(Location here){
    location = here ; 
  }

   /** get location */
  public Location getLocation() {
    return location;
  }

  /** add item to backpack */ 
  public void addItem(Item item){
    backpack.add(item);
  }

  /** remove item from backpack */ 
  public void removeItem(Item item){
    backpack.remove(item);
  }

  /**returns backpack arraylist */
  public ArrayList<Item> getBackpack(){
     return backpack; 
    }

  /** check if player is holding a certain item */
  public boolean hasItem(Item item){
    if (backpack.contains(item)){
      return true;
    }
    else{
      return false;
    }
  }
  
  }
  
  
  


  

  
