package assignment;

import java.util.*;

public class Game {
	private River river;
	private int initialNumberOfPlayers;
	private Queue<Player> players;// Comment: Keeping players in game service as they are specific to this game and not the board. Keeping pieces in the board instead.
	private int noOfDices; //The board will have 100 cells numbered from 1 to 100.
	
	private static final int DEFAULT_BOARD_SIZE = 100;
	private static final int DEFAULT_NO_OF_DICES = 1;
	
	public Game() {
        this(Game.DEFAULT_BOARD_SIZE);
    }
	
	public Game(int boardSize) {
		this.river = new River();  
		this.players = new LinkedList<Player>();
		this.noOfDices = Game.DEFAULT_NO_OF_DICES;
	}
	
	/**
	 * ================== Initialize board ==================
	 */
	public void setPlayers(List<Player> players) {
        this.players = new LinkedList<Player>();
        this.initialNumberOfPlayers = players.size();
        Map<String, Integer> boat = new HashMap<String, Integer>();
        for (Player player : players) {
            this.players.add(player);
            boat.put(player.getName(), 0); //Each player has a piece which is initially kept outside the board (i.e., at position 0).
        }
        river.setBoat(boat); // Add pieces to board
    }
    
    
    /**
     * ========== Core business logic for the game ==========
     */
    public int getNewPosition(Player player, int newPosition) {
    	int previousPosition;
    	
    	/**
    	* ========== Display when the player steps on a current or a trap ==========
    	*/
    	do {
    		previousPosition = newPosition;
    		for (RiverItem r : river.getRiverItems()) {
    			if(r != null && r.getSymbol() == "#") {
    				if(r.getStartPosition() == newPosition) {
    	    			 newPosition = r.getStartPosition() - r.getStrength();
    	    			 if(r.getStrength()<=1) {
    	    			 System.out.println ("\t\t\t\t~~~~~~~~~~~~~"+ player.getName() + " stepped on a trap, BOAT MOVES " + r.getStrength() + " STEP BACKWARDS!!! ~~~~~~~~~~~~~\n");
    	    			 } else {
    	        	     System.out.println ("\t\t\t\t~~~~~~~~~~~~~"+ player.getName() + " stepped on a trap, BOAT MOVES " + r.getStrength() + " STEPS BACKWARDS!!!~~~~~~~~~~~~~\n");
    	    			 }
    				}
    			} else if(r != null && r.getSymbol() == "C") {
    				if (r.getStartPosition() == newPosition) {
                        newPosition = r.getStartPosition() + r.getStrength();
               			if(r.getStrength()<=1) {
                            System.out.println ("\t\t\t\t~~~~~~~~~~~~~"+ player.getName() + " stepped on a current, BOAT MOVES " + r.getStrength() + " STEP FORWARD!!! ~~~~~~~~~~~~~\n");
                        } else {
                            System.out.println ("\t\t\t\t~~~~~~~~~~~~~"+ player.getName() + " stepped on a current, BOAT MOVES " + r.getStrength() + " STEPS FORWARD!!!~~~~~~~~~~~~~\n");
                        }
                    }
    			}
    		 }
    	} while (newPosition != previousPosition);
    		return newPosition;
    }
    /**
     * ======================= Method to get player's position ===========================
     */
    public void movePlayer(Player player, int positions) {
        int oldPosition = river.getBoat().get(player.getName());
        int newPosition = oldPosition + positions; // Based on the dice value, the player moves their piece forward that number of cells.
        int boardSize = Game.DEFAULT_BOARD_SIZE;

        if ((newPosition > boardSize)) {
            newPosition = boardSize; // After the dice roll, if a piece is supposed to move outside position 100, it does not move.
        
        } else if (newPosition < 0){
        	newPosition = oldPosition;        
        } else {
            newPosition = getNewPosition(player, newPosition);
        }

        river.getBoat().put(player.getName(), newPosition);
        
        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition +" to " + newPosition + "\n");
    }
    
    public int getTotalValueAfterDiceRolls() {
        return DiceService.roll();
    }
    
    public boolean hasPlayerWon(Player player) {
        int playerPosition = river.getBoat().get(player.getName());
        int winningPosition = Game.DEFAULT_BOARD_SIZE;
        return playerPosition == winningPosition; // A player wins if it exactly reaches the position 100 and the game ends there.
    }
    
    public boolean isGameCompleted() {
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < initialNumberOfPlayers ;
    }
    
    /**
     * ======================= Method for instruction ===========================
     */
    public void instruction() {
    	System.out.println ("\n------------------------------------------------------------------------------------------------------------------------------\n");
    	System.out.println ("\t\t\t\t\t\tWelcome To Boat Racing Game\n");
    	System.out.println ("------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println ("Instructions:");
		System.out.println(
		"\nThe game is a two players game. At the beginning of the game, each player will be allocated with a boat.\n"+
		"During the game, the players take turn to throw the dice to decide how many steps should the boat move forward.\n"+
		"The river can be visualised as 100-columns track, which is filled with random number of traps(#) and currents(C).\n"
		);
        System.out.println ("-----------------------------------------------------Game Board---------------------------------------------------------------\n");    
        river.printRiver();
	}
	
    /**
     * ======================= Method to decide winner ===========================
     */
    public void startGame() {
        while (!isGameCompleted()) {
            int counter = 1;

            Player player1;
            Player player2 = null ;
            
            while (counter!=0) {
                counter = counter + 1;

                int totalDiceValue = getTotalValueAfterDiceRolls();
                player1 = players.poll();

                movePlayer(player1, totalDiceValue);
                
                if (hasPlayerWon(player1)) {
                    counter = counter - 1;
                    int counter2 = counter-1;

                    System.out.println("---------------  Game Over  --------------- \n");
                    System.out.println(player1.getName() + " wins the game!\nTurns: " + counter); 
                    System.out.println(player2.getName()+ " lose the game!\nTurns: " + counter2);
                    Player.addtoList(player1);
                    player1.addScore(counter);
                    river.getBoat().remove(player1.getName()); 
                    counter = 0; //stop the counter 
                    } else {
                      players.add(player1);
                      int totalDiceValue2 = getTotalValueAfterDiceRolls();
                      player2 = players.poll();

                      movePlayer(player2, totalDiceValue2);

                      if (hasPlayerWon(player2)) {
                    	  counter = counter - 1;
                          int counter2 = counter;

                          System.out.println("---------------  Game Over  --------------- \n");
                    	  System.out.println(player2.getName() + " wins the game!\nTurns: " + counter); 
                          System.out.println(player1.getName() + " lose the game!\nTurns: " + counter2);
                          Player.addtoList(player2);
                          player2.addScore(counter);
                          river.getBoat().remove(player2.getName()); 
                          counter = 0; //stop the counter 
                        } else {
                            players.add(player2);
                            }
                      }
                }
            }
        }

	@Override
	public String toString() {
		return String.format("Game [river=%s, initialNumberOfPlayers=%s, players=%s, noOfDices=%s]", river,
				initialNumberOfPlayers, players, noOfDices);
	}
    
}
