// --== CS400 File Header Information ==--
// Name: <Shin-Tsz Lucy Kuo>
// Email: <skuo8@wisc.edu>
// Team: <your team name: GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;

/**
 * Extension of RBT class to include searching for player
 */
public class RedBlackTreeExtension extends RedBlackTree<Player> {
  
  /**
   * Get player
   * 
   * @param jerseyNumber number to look for
   * @throws NoSuchElementException if tree empty or if jersey number not in tree
   * @return Player that it's looking for or throws exception
   */
//  public Player getPlayer(int jerseyNumber) throws NoSuchElementException {
//    Node<Player> getPlayerResult = this.getPlayerHelper(jerseyNumber, this.root);
////    if (getPlayerResult == null) {
////      return null;
////    } 
//    return getPlayerResult.data;
//    
//    //check if this.playerhelper is null, store in var
//    //return this.getPlayerHelper(jerseyNumber, this.root).data;
//  }
  public Player getPlayer(int jerseyNumber) {
    try{
      //if getPlayerResult doesn't throw exception, player exists
      Node<Player> getPlayerResult = this.getPlayerHelper(jerseyNumber, this.root); //goes to catch block if exception
System.out.println(".data" + getPlayerResult.data);
      return getPlayerResult.data; //evaluated if no exception thrown
    }
    catch(NoSuchElementException n) {
System.out.println("nosuchelement");
      return null;
    }
    catch(Exception e) {
System.out.println("exception");
      return null;
    }
    
    //check if this.playerhelper is null, store in var
    //return this.getPlayerHelper(jerseyNumber, this.root).data;
  }
  
  /**
   * Lookup for player node
   * 
   * @param jerseyNumber number to look for
   * @param current Current node looking at
   * @throws NoSuchElementException if tree empty or if jersey number not in tree
   * @return Player node that it's looking for
   */
  public Node<Player> getPlayerHelper(int jerseyNumber, Node<Player> current) throws NoSuchElementException {
    if(current == null) {
      throw new NoSuchElementException("empty tree");
    }
System.out.println("jerseynumber: " + jerseyNumber + ", Current: " + current.data.getJerseyNumber());
    if(jerseyNumber == current.data.getJerseyNumber()) {
System.out.println("Current: " + current.data.getJerseyNumber());
      return current;
    }
    else if (jerseyNumber < current.data.getJerseyNumber()) {
System.out.println("jerseynumber: " + jerseyNumber + ", Current: " + current.data.getJerseyNumber());
      if (current.leftChild == null) {
        throw new NoSuchElementException("no player matching jersey number");
      }
      else {
        return getPlayerHelper(jerseyNumber, current.leftChild);
      }
    }
    else {
      if (current.rightChild == null) {
        throw new NoSuchElementException("no player matching jersey number");
      }
      else {
        return getPlayerHelper(jerseyNumber, current.rightChild);
      }
    }
  }
}
