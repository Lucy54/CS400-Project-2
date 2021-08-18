// --== CS400 File Header Information ==--
// Name: <Shin-Tsz Lucy Kuo>
// Email: <skuo8@wisc.edu>
// Team: <your team name: GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestBlackTree {

  /**
   * Tests that Red Black Tree is correct after 
   * Base Case: inserting into tree when parent is black node.
   */
  @Test
  public void testCaseOne() {
    RedBlackTree<Integer> testTree = new RedBlackTree<>();
    testTree.insert(10);
    testTree.insert(8);
    
    String expected = "[10, 8]";
    if(!testTree.toString().equals(expected)) {
      fail("incorrect properties enforcement");
    }
  }
  
  /**
   * Tests that Red Black Tree is correct after 
   * Case 1: inserting into tree when parent red, parent sibling is red node.
   */
  @Test
  public void testCaseTwo() {
    RedBlackTree<Integer> testTree = new RedBlackTree<>();
    testTree.insert(10);
    testTree.insert(8);
    testTree.insert(12);
    testTree.insert(5);
    
    String expected = "[10, 8, 12, 5]";
    if(!testTree.toString().equals(expected)) {
      System.out.println(testTree.toString());
      fail("incorrect properties enforcement");
    }
  }
  
  /**
   * Tests that Red Black Tree is correct after 
   * Case 2: inserting into tree when parent red, parent sibling is black node and 
   * on same side as new node.
   */
  @Test
  public void testCaseThree() {
    RedBlackTree<Integer> testTree = new RedBlackTree<>();
    testTree.insert(20);
    testTree.insert(10);
    testTree.insert(30);
    testTree.insert(5);
    testTree.insert(7);
    
    String expected = "[20, 7, 30, 5, 10]";
    if(!testTree.toString().equals(expected)) {
      fail("incorrect properties enforcement");
    }
  }
  
  /**
   * Tests that Red Black Tree is correct after 
   * Case 3: inserting into tree when parent red, parent sibling is black node and 
   * on opposite side as new node.
   */
  @Test
  public void testCaseFour() {
    RedBlackTree<Integer> testTree = new RedBlackTree<>();
    testTree.insert(20);
    testTree.insert(10);
    testTree.insert(30);
    testTree.insert(5);
    testTree.insert(3);
    
    String expected = "[20, 5, 30, 3, 10]";
    if(!testTree.toString().equals(expected)) {
      System.out.println(testTree.toString());
      fail("incorrect properties enforcement");
    }
  }  
  
  //Gradescope example
  @Test
  public void testCaseFive() {
    RedBlackTree<Integer> testTree = new RedBlackTree<>();
    //32, 41, 57, 62, 79, 81, 93, 97
    
    //Expected: 62, 41, 81, 32, 57, 79, 93, 97
    testTree.insert(32);
    testTree.insert(41);
    testTree.insert(57);
    testTree.insert(62);
    testTree.insert(79);
    testTree.insert(81);
    testTree.insert(93);
    testTree.insert(97);
    
System.out.println(testTree.toString());
  }

}
