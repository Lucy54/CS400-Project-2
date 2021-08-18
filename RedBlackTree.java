// --== CS400 File Header Information ==--
// Name: <Shin-Tsz Lucy Kuo>
// Email: <skuo8@wisc.edu>
// Team: <your team name: GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import RedBlackTree.Node;

/**
 * Binary Search Tree implementation with a Node inner class for representing
 * the nodes within a binary search tree.  You can use this class' insert
 * method to build a binary search tree, and its toString method to display
 * the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always be maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild; 
        public Node<T> rightChild; 
        public Node(T data) { this.data = data; }
        public boolean isBlack = false;
        
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node.  The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() { // display subtree in order traversal
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }
    }

    protected Node<T> root; // reference to root node of tree, null when empty

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree.  After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the tree already contains data
     */
    public void insert(T data) throws NullPointerException,
              IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) { root = newNode; } // add first node to an empty tree
        else insertHelper(newNode,root); // recursively insert into subtree
        
        //always sets root node of RBT to black
        root.isBlack = true;
    }

    /** 
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references (as defined by Comparable.compareTo())
     */
    private void insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) throw new IllegalArgumentException(
            "This RedBlackTree already contains that value.");

        // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else { 
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.rightChild);
        }
        
        enforceRBTreePropertiesAfterInsert(newNode);
    }

    /**
     * This method performs a level order traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { return root.toString(); }

    /**
     * Performs the rotation operation on the provided nodes within this BST.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation (sometimes called a left-right 
     * rotation).  When the provided child is a rightChild of the provided 
     * parent, this method will perform a left rotation (sometimes called a 
     * right-left rotation).  When the provided nodes are not related in one 
     * of these ways, this method will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        //right child, perform left rotation
        if(parent.rightChild != null && parent.rightChild.data.compareTo(child.data) == 0) {
          parent.rightChild = child.leftChild;
          
          if(child.leftChild != null) {
            child.leftChild.parent = parent;
          }
          child.parent = parent.parent;
          
          //checking if parent is left or right child
          if(parent.parent == null) {
            root = child;
          }
          else if(parent.parent.leftChild.data.compareTo(parent.data) == 0) {
            parent.parent.leftChild = child;
          }
          else {
            parent.parent.rightChild = child;
          }
          
          //uddate references pointing to parent and child
          child.leftChild = parent;
          parent.parent = child;
        }
        
        //left child, perform right rotation
        else if(child.isLeftChild()) {
          parent.leftChild = child.rightChild;
          
          if(child.rightChild != null) {
            child.rightChild.parent = parent;
          }
          child.parent = parent.parent;
          
          //checking if parent is left or right child
          if(parent.parent == null) {
            root = child;
          }
          else if(parent.parent.leftChild.data.compareTo(parent.data) == 0) {
            parent.parent.leftChild = child;
          }
          else {
            parent.parent.rightChild = child;
          }
          
          //update references pointing to parent and child
          child.rightChild = parent;
          parent.parent = child;
        }
        
        else {
          throw new IllegalArgumentException("child neither left nor right child of parent");
        }
    }
    
    /**
     * Private helper method which resolves red child under red parent red black tree property 
     * violations that are introduced by inserting new nodes into a red black tree. While doing so,
     * all other red black tree properties must also be preserved.
     * @param newNode reference to newly added red node
     * @return newNode when enforce complete or null otherwise
     */    
    private Node<T> enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
      Node<T> parent = newNode.parent;
      
      //Base Case of Recursion: no violation and check if parent is root
      if(parent == null || parent.isBlack) {
        return newNode;
      }
      
      boolean parentLeft = parent.isLeftChild();
      Node<T> grandparent = parent.parent;
      Node<T> parentSibling;
      
      //determine if parentSibling is left or right child
      if(parentLeft) {
        parentSibling = grandparent.rightChild;
      }
      else {
        parentSibling = grandparent.leftChild;
      }
      
      //determine side of parent sibling & new node
      boolean sameSide = false;
      if(newNode.isLeftChild()) {
        if((parentSibling != null && parentSibling.isLeftChild()) || 
            (parentSibling == null && !parent.isLeftChild())) {
          sameSide = true;
        }
      }
      else {
        if((parentSibling != null && !parentSibling.isLeftChild()) || 
            (parentSibling == null && parent.isLeftChild())) {
          sameSide = true;
        }
      }
      
      //Case 1: parent red, parent sibling red
        //set parents to black and grandparent to red, then recursively percolate up
      if(parentSibling != null && !parentSibling.isBlack) {
        
        //re color
        parent.isBlack = true;
        parentSibling.isBlack = true;
        grandparent.isBlack = false;
        
        //check if root
        if(root.data.compareTo(grandparent.data) == 0) {
          grandparent.isBlack = true;
          return(newNode);
        }
        
      }
      
      //Cases 2 & 3: parent red, parent sibling black
      if(parentSibling == null || parentSibling.isBlack) {
        
        //Case 3: parent sibling on same side as new node
        if(sameSide) {
          rotate(newNode, parent);
          rotate(newNode, grandparent);
        }
        //Case 2: parent sibling opposite side as new node
        else {
          rotate(parent, grandparent);
        }
        
        //re color
        grandparent.isBlack = false;
        parent.isBlack = true;
      }
      
      return null;
    }

}
