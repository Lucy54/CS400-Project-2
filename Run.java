// --== CS400 File Header Information ==--
// Name: <Shin-Tsz Lucy Kuo>
// Email: <skuo8@wisc.edu>
// Team: <your team name: GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
/**
 * This class prepares data to be run with rest of program.
 */
public class Run {

  /**
   * Main method to combine data and backend parts to prepare for front-end component.
   */
  public static void main(String[] args) {
    //DataPrep dataPrep = new DataPrep();
    Loader load = new Loader();
    load.LoadData();
    //load.printDB();
    FrontEnd frontEnd = new FrontEnd(load.backend);
    frontEnd.run();
    
  }

}
