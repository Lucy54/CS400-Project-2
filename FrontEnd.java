// --== CS400 File Header Information ==--
// Name: Juan Gutierrez
// Email: jpgutierrez2@wisc.edu
// Team: GD
// TA: Dan Kiel
// Lecturer: Florian Heimerl
// Notes to Grader: 
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents the user interface of the Project 2 Application. It
 * allows users to interact with the application and handles all user input and
 * exceptions caused by the user. It also contains the driver for this
 * application.
 * 
 * @author Juan Gutierrez
 * @version 1.0
 */
public class FrontEnd {
	
	/**
	 * BackEnd object used in this instance of the application.
	 */
	private BackEnd backEnd;
	
	
	public FrontEnd(BackEnd backEnd) {
		
		this.backEnd = backEnd; // Instance of BackEnd
		
	}
	
	/**
	 * Gets user input for adding a player
	 */
	private boolean add(Scanner sc) {
		System.out.println("Enter players name: ");
		String name = sc.nextLine();
		
		System.out.println("Enter jersey number: ");
		int num = sc.nextInt();
		
		System.out.println("Enter player rating (1-100): ");
		int rating = sc.nextInt();
		
		return backEnd.addPlayer(new Player(name, num, rating));

	}
	
	private void printPlayerInfo( Player player ) {
		System.out.println( "Player name: " + player.getPlayerName() );
		System.out.println( "Jersey number: " + player.getJerseyNumber() );
		System.out.println( "Rating: " + player.getRating() );
	}
	
	/**
	 * 
	 */
	private void get(Scanner sc) {
		System.out.println("What jersey number would you like to lookup?: ");
		int num = sc.nextInt();
		
		Player player = backEnd.getPlayer( num );
		
		if ( player == null) {
			System.out.println("Player not found");
			return;
		}
		
		printPlayerInfo(player);
		
	}
	
	private boolean exists(Scanner sc) {
		System.out.println("Enter jersey number: " );
		int num = sc.nextInt();
		
		return backEnd.playerExists(num);
	}
	
	private char format( String in ) {
		return in.trim().toLowerCase().charAt(0);
	}
	
	private void help() {
		System.out.println("To add a player, enter \'a\'");
		System.out.println("To get a player, enter \'g\'");
		System.out.println("To check if a player is on the roster, enter \'e\'");
		System.out.println("To quit, enter \'q\'");
	}
	
	/**
	 * Runs this instance of the front end.
	 */
	public void run() {
		// Variable declarations
		Scanner scnr = new Scanner( System.in );
		String input = "";
		boolean valid = false;
		boolean loop = true;
		
		// Welcome Message
		System.out.println("Database for Los Angeles Lakers' Players");
		System.out.println("========================================\n");
		
		while (loop) {
			valid =false;
			while (!valid) {
				//help();
				
				try {
					input = scnr.nextLine();
					
					if (!input.isEmpty()) {
						valid = true;
					} else {
						System.out.println("Invalid Input\n");
					}
					
				} catch (NoSuchElementException e) {
					System.out.println("Invalid Input\n");
				}
			}
			
			switch ( format(input) ) {
				case 'a':
					if ( add(scnr) ) {
						System.out.println("Player added Successfully");
					} else {
						System.out.println("Player not added");
					}
					break;
				case 'g':
					get(scnr);
					break;
				case 'e':
					if ( exists(scnr) ) {
						System.out.println("Player is on the Lakers");
					} else {
						System.out.println("Player is not on the Lakers ");
					}
					break;
				case 'q': 
					loop = false;
					break;
				case 'h':
				  help();
				default:
					System.out.println("Invalid Input\n");
					break;
				
			}
		}
		
		
	}	
	
}
