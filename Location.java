/**
 *. Class to represent a location. 
 *
 *  @author Priya Dalal-Whelan/ Lillian Fok
 *. @version Spring 2022
 */

import org.checkerframework.checker.units.qual.Time;
import org.checkerframework.common.returnsreceiver.qual.This;
import java.util.ArrayList;

/** Location Class holds up to four neighbors, items, and description */

public class Location {

  
  /** name of location */
  private String name; 

   /** text to print*/
  private String description; 

  /** back neighbor - where we came from
    except for start, always exists */
  private Location backNeighbor =  null;

  /** forward neighbor = might exist */
  private Location frontNeighbor = null;

  /** left neighbor - might exist */
  private Location leftNeighbor = null;

  /** right neighbor - might exist */
  private Location rightNeighbor = null;

  /** array list to save items */
  private ArrayList<Item> items = new ArrayList<Item>();

  /** if the location has been traversed */
  private boolean isVisited = false; 

  /** if the location still has a riddle to be solved */
  private boolean hasRiddle = false;

  /** if has riddle, the prize for the riddle */
  private Item prize = null; 


  /** constructor without items */ 
  public Location(String name, String description){
    this.name = name;
    this.description = description; 
  }
  
  /** print description */ 
  public String print_description(){
    String s = description;
    if (items.size() > -1) {
      for (Item it : items) {
        s += "\n" + it.getDescription();
      }
    }  
    return s;
  }

  /** print name */
  public String print_name(){
    return name;
  }

   /** toString prints the name of the location (for now) */
  public String toString(){
    return name; 
  }

  /** add front neighbor */
  public void addFront(Location neighbor) {
    this.frontNeighbor = neighbor;
  }
  
  /** add back neighbor*/
  public void addBack(Location neighbor){
    this.backNeighbor = neighbor;
  }

  /** add left neighbor*/
  public void addLeft(Location neighbor){
    this.leftNeighbor = neighbor;
  }

  /** add right neighbor*/
  public void addRight(Location neighbor){
    this.rightNeighbor = neighbor;
  }

  /** return arraylist all neighbors of this location */
  public ArrayList<Location> getNeighbors() {
    ArrayList<Location> neighbors = new ArrayList<Location>();
    neighbors.add(this.backNeighbor);
    neighbors.add(this.leftNeighbor);
    neighbors.add(this.rightNeighbor);
    return neighbors;
  }

  /** return back neighbor */
  public Location getBack() {
    return backNeighbor;
  }
  
  /** return left neighbor */
  public Location getLeft() {
    return leftNeighbor;
  }

  /** return right neighbor */
  public Location getRight() {
    return rightNeighbor;
  }

  /** return front neighbor */
  public Location getFront() {
    return frontNeighbor;
  }

  //accept item to add to array list
  public void addItem(Item i) {
    items.add(i);
  }

  //return items
  public ArrayList<Item> getItems() {
    return items;
  }

  //delete the item from location
  public void removeItem(Item item){
    items.remove(item);
  }

  //set description
  public void setDescription(String description ){
    this.description = description ;
  }

  //set riddle boolean
  public void setRiddle(Boolean bool) {
    hasRiddle = bool;
  }

  //get riddle boolean 
  public boolean hasRiddle() {
    return hasRiddle;
  }

   /** set locked item */
  public void setPrize(Item prize){
    this.prize = prize;
  }

  /** get locked item*/
  public Item getPrize(){
    return prize; 
  }
  
}
