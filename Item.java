/**
 *. Class to represent an item. 
 *
 *  @author Priya Dalal-Whelan/ Lillian Fok
 *. @version Spring 2022
 */


public class Item {

  
 /** name of item */
  private String name;

  private String description;

 /** constructor */

  public Item(String name){
    this.name = name; 
  }

   /** constructor */

  public Item(String name, String description){
    this.name = name; 
  }

  /** to string */
  public String toString() {
    return name;
  }
  
  /** get description */
  public String getDescription() {
    return description;
  }

  /** set description */
  public void setDescription(String words) {
    description = words;
  }
  
}