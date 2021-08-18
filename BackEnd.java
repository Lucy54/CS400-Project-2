// --== CS400 File Header Information ==--
// Name: <Shin-Tsz Lucy Kuo>
// Email: <skuo8@wisc.edu>
// Team: <your team name: GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
//import RedBlackTree.Node;

/**
 * This class makes up the back end part.
 */
public class BackEnd implements projectTwoADT {
  private RedBlackTree<Player> redBlackTree;

  public BackEnd() {
    this.redBlackTree = new RedBlackTree<Player>();
  }

  @Override
  // public boolean addPlayer(Player player) {
  // try {
  // getPlayer(player.getJerseyNumber()); //throws nosuchelementexception if insertion possible
  // //if no exceptions thrown, player found
  // return false;
  // //throw new IllegalArgumentException("jersey number already exists");
  // } catch (NullPointerException n) {
  //// System.out.println("ERROR: null reference, unable to add player");
  // return false;
  // }
  // //can insert b/c getJerseyNumber threw NSE
  // catch (NoSuchElementException e) {
  // redBlackTree.insert(player);
  // return true;
  // }
  // catch(IllegalArgumentException i) {
  // return false;
  // }
  //
  // //return false;
  // }

  // public boolean addPlayer(Player player) {
  // if(player == null) {
  // return false;
  // }
  //
  // try{
  // getPlayer(player.getJerseyNumber());
  // if(getPlayer(player.getJerseyNumber()) != null) {
  // return false;
  // }
  // redBlackTree.insert(player);
  // return true;
  // } catch(NullPointerException np) {
  // return false;
  // }
  // catch(IllegalArgumentException i) {
  // return false;
  // }
  // catch(NoSuchElementException n) {
  // redBlackTree.insert(player);
  // return true;
  // }
  // catch(Exception e) {
  // return false;
  // }
  // }

  public boolean addPlayer(Player player) {
    if (player == null) {
      return false;
    }

    if (playerExists(player.getJerseyNumber())) {
//System.out.println("playerexists");
      return false;
    }

    try {
      getPlayer(player.getJerseyNumber());
      if (getPlayer(player.getJerseyNumber()) != null) {
        return false;
      }
      redBlackTree.insert(player);
      return true;
    } catch (NullPointerException np) {
//System.out.println("nullpointer");
      return false;
    } catch (IllegalArgumentException i) {
//System.out.println("illegalargument");
      return false;
    } catch (NoSuchElementException n) {
//System.out.println("nosuchelement");
      redBlackTree.insert(player);
      return true;
    } catch (Exception e) {
//System.out.println("exception");
      return false;
    }

  }

//  @Override
//  /**
//   * Uses RBTExtension getPlayer. If RBTExtension getPlayer throws NoSuchElementException, that
//   * means player does not exist, and this method will then handle it and return null.
//   * 
//   * @param jerseyNumber number to look for
//   * @return Player or null if player doesn't exist
//   */
//  public Player getPlayer(int jerseyNumber) {
//    // calls RBTExtension getPlayer
//    Player player;
//    try {
//      player = getPlayer(jerseyNumber);
//      return player;
//
//    } catch (Exception n) {
//      return null;
//    }
//    // if (player == null) {
////    // System.out.println("player does not exist");
//    // }
//    // return player;
//  }
  

  @Override
  public boolean playerExists(int jerseyNumber) {
//    try {
//      getPlayer(jerseyNumber);
//    } catch (NoSuchElementException n) {
//      return false;
//    }
//    return true;
     if (getPlayer(jerseyNumber) != null) {
     return true;
     }
    
     return false;
  }
  
  
  
  
  
  
  
  
  
  public Player getPlayer(int jerseyNumber) {
    try{
      //if getPlayerResult doesn't throw exception, player exists
      RedBlackTree.Node<Player> getPlayerResult = this.getPlayerHelper(jerseyNumber, redBlackTree.root); //goes to catch block if exception
//System.out.println(".data" + getPlayerResult.data);
      return getPlayerResult.data; //evaluated if no exception thrown
    }
    catch(NoSuchElementException n) {
//System.out.println("nosuchelement");
      return null;
    }
    catch(Exception e) {
//System.out.println("exception");
      return null;
    }
    
    //check if this.playerhelper is null, store in var
    //return this.getPlayerHelper(jerseyNumber, this.root).data;
  }
  
  public RedBlackTree.Node<Player> getPlayerHelper(int jerseyNumber, RedBlackTree.Node<Player> current) throws NoSuchElementException {
    if(current == null) {
      throw new NoSuchElementException("empty tree");
    }
//System.out.println("jerseynumber: " + jerseyNumber + ", Current: " + current.data.getJerseyNumber());
    if(jerseyNumber == current.data.getJerseyNumber()) {
//System.out.println("Current: " + current.data.getJerseyNumber());
      return current;
    }
    else if (jerseyNumber < current.data.getJerseyNumber()) {
//System.out.println("jerseynumber: " + jerseyNumber + ", Current: " + current.data.getJerseyNumber());
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
