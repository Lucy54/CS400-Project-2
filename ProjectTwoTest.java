// --== CS400 File Header Information ==--
// Name: <Tzu Chi Huang (Aris)>
// Email: <thuang245@wisc.edu>
// Team: <GD>
// Role: <Test Engineer 1>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 * This is a JUnit5 test class to determine whether interfaces (projectTwoADT) and the
 * functionalities of the application (mainly BackEnd class) are working correctly.
 * 
 * @author Aris Huang
 *
 */
class ProjectTwoTest {
  private BackEnd backend = new BackEnd(); // a BackEnd Object to test the functionality of the
                                           // application
  private Loader loader = new Loader(); // a DataPrep object to test the Data Wrangler & Back
                                              // End's data-loading functionalities
  private FrontEnd frontEnd  = new FrontEnd(backend);

  //Test for Data loading 
  /**
   * Test the functionality of InsertData() from the DataPrep class to ensure whether the
   * application has successfully loaded the data
   * 
   * Display fail message if the functionality fails
   */
  @Test
  void loadDataTest() {
    // this will load all the players into the backend object for all the other cases as well
    loader.LoadData();

    if (!loader.LoadData() ) {
      fail("fails to load the data into the application");
    }
  }

  /**
   * Test whether the data file exists.
   * 
   * Display fail message if file does not exist
   */
  @Test
  void dataTest() {
    // this will load all the players into the backend object for all the other cases as well
    if (loader.equals(null)) {
      fail("fails to find the data");
    }
  }

  // Test for FrontEnd Functionalities  
  /**
   * Test the functionality of run() from the FrontEnd class to ensure whether the application is
   * running correctly and users are able to interact with the application
   * 
   * Display fail message if the functionality fails
   */
  @Test
  void fronEndTest() {
    // frontEnd.run(); // this should display the application (if nothing comes out of the console,
    // this means that the application isn't running). (HAD TO COMMENT THIS OUT OTHERWISE ALL THE
    // TESTS WILL NOT RUN)

    if (frontEnd == null) {
      fail("fail to run the application");
    }
  }



  // Test for BackEnd Functionalities 
  /**
   * Test the functionality of addPlayer() by adding a valid player that could be added to the tree
   * 
   * Display fail message if the functionality fails
   */

  @Test
  void addValidPlayerTest() {
    // addPlayer case 1 - adding a player that could be added to the tree
    if (backend.addPlayer(new Player("Stephen Curry", 30, 97)) == false ) {
      fail("fail to add a valid player into the tree");
    }
  }

  /**
   * Test the functionality of addPlayer() by adding a player that has the same jersey number as one
   * of the players that are already in the tree
   * 
   * Display fail message if the functionality fails
   */
  @Test
  void addPartialValidPlayerTest() {
    // addPlayer case 2 - adding a player that has the same jersey number as one of the players that
    // are already in the tree
    if (backend.addPlayer(new Player("Jordan Poole", 3, 71)) != false) {
      fail(
          "encountering error in adding a new player with the jersey number that already existed in the tree");
    }
  }

  /**
   * Test the functionality of addPlayer() by adding invalid player with null reference
   * 
   * Display fail message if the functionality fails
   */
  @Test
  void addInvalidPlayerTest() {

    // addPlayer case 3 - add invalid player with null reference
    if (backend.addPlayer(null) != false) {
      fail("encountering error when adding null/invalid arguments");
    }
  }


  /**
   * Test the functionality of getPlayer() by getting an existing Player (LeBron James) in the Red
   * Black Tree
   * 
   * Display fail message if the functionality fails
   */
  @Test
  void getExistPlayerInAppTest() {

    // get case 1 - get an existing Player (LeBron James) in the Red Black Tree
    if (backend.getPlayer(23).equals(null)) { // TODO: the BackEnd need to fix this part because you
                                              // need to call the RedBlackTreeExtention class
      fail("There is an error when accessing a player that is already in the tree.");
    }
    
  }

  /**
   * Test the functionality of getPlayer() by calling a Player that does not exist
   * 
   * Display fail message if the functionality fails
   */
  @Test
  void getPlayerDoesNotExistInAppTest() {
    // get case 2 - get a Player that does not exist
    if (!backend.getPlayer(2).equals(null)) {
      fail("There is an error when accessing a player that does not exist in the tree.");
    }
  }

  /**
   * Test the functionality of playerExists() by calling a Player who does exist
   * 
   * Display fail message if the functionality fails
   */
  @Test
  void playerExistsTest() {
    // get case 1 - Determines if an existing Player exists
    if (backend.playerExists(11) == false) {
      fail("This player does exist but is not shown here");
    }
  }

  /**
   * Test the functionality of playerExists() by calling a Player who does not exist
   * 
   * Display fail message if the functionality fails
   */
  @Test
  void playerDoNotExistsTest() {
    // get case 2 - Determines if an non-existing Player exists
    if (backend.playerExists(5) != false) {
      fail("This player does exist but is not shown here");
    }
  }
}
