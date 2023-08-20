package assignment;

import java.util.*;

public class Driver {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		boolean playAgain = true;
		String response = "";
	
		/**
 		* ================== The start of the boat racing game ==================
 		*/
		while(playAgain) {
			Player.readFile();
			Game game = new Game();
			game.instruction();
			
			Player player1 = new Player();
			Player player2 = new Player();
			
		/**
	 	* ========== Prompt player 1 and player 2 to enter their names =============
	 	*/
	        List<Player> players = new ArrayList<Player>();
	        System.out.print("\nEnter name of player 1: ");
	        player1.setName(scanner.next());
	        players.add(player1);
	        System.out.print("Enter name of player 2: ");
	        player2.setName(scanner.next());
	        players.add(player2);
	        System.out.println("\n------------  Game Start  ------------ \n");
	 
	        /**
	 	* =============== Sets the player position and starts game ==================
	 	*/
	        game.setPlayers(players);
	        game.startGame();
	        
	        /**
	 	* ============== Writes the score in the TopScore text file =================
	 	*/
	        Player.sortScoreList();
	        Player.writeFile();
	        
	        /**
	 	* ============== Asks player if they would like to play again ================
	 	*/
	        System.out.print("\nWould you like to play again? Enter Y or N: ");
			response = scanner.next();
			
			if (response.equalsIgnoreCase("Y")){
				playAgain = true;
			}
			else {
				playAgain = false;
				System.out.println("Thanks for playing!");
				scanner.close();
			}
		}
    }
}
