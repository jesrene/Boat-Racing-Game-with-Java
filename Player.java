package assignment;
import java.util.*;
import java.io.*;

public class Player {
    private String name;
    private int total;
    private static ArrayList<Player> scoreList = new ArrayList<Player>();
    
    public Player() {
    }

    public Player(String name) {
		this.name = name;
    }
    
    public String getName() {
        return name;
    }
	
    public void setName(String name) {
	this.name = name;
    }
	
    public int getTotal() {
	return total;
    }
	
    public void setTotal(int total) {
	this.total = total;
    }

    public void addScore(int s){
	total += s;
    }
	
    public static void addtoList(Player s){
	scoreList.add(s);
    }

    /*
    ** ==================== Displays the score board in Java =======================
    */
    public static void showTopScores(){
	System.out.println("------------  SCORE BOARD  ------------ \n");
	System.out.println("  Name of players  |  Score ");
	      if(scoreList.size()<=5){
	          for(int i = 0; i<scoreList.size(); i++){
	              System.out.println(scoreList.get(i).toString());
	          }
	      } else {
	          for(int i = 0; i<5; i++){
	              System.out.println(scoreList.get(i).toString());
	          }
	      }
	  }

	public static void sortScoreList(){
		for(int i = 0; i<scoreList.size()-1; i++){
			for(int j = 0; j<scoreList.size()-i-1; j++){
				if(scoreList.get(j).getTotal() > scoreList.get(j+1).getTotal()){
					Player player = scoreList.get(j);
	                    scoreList.set(j, scoreList.get(j+1));
	                    scoreList.set(j+1, player);
	                }
	            }
	        }
	    }
	
 	 /*
   	 ** ==================== To check whether TopScore text file has been created =======================
   	 */
	public static void readFile(){
		try {
		File fileRead = new File("TopScore.txt");
	            Scanner reader = new Scanner(fileRead);
	            while (reader.hasNextLine()){
	                String data1 = reader.nextLine();
	                Player player = new Player(data1);
	                int data2 = Integer.parseInt(reader.nextLine());
	                player.setTotal(data2);
	                scoreList.add(player);
	            }
	            reader.close();
	            showTopScores();
	        }
	        catch (FileNotFoundException e){
	            try{
	                File fileOpen = new File("TopScore.txt");
	                if (fileOpen.createNewFile()){
	                    System.out.println("Top Score file has been created successfully.");
	                }
	                else {
	                    System.out.println("Top score file already exists.");
	                }
	            }
	            catch (IOException i){
	                System.out.println("Top Score file is unable to print.");
	                i.printStackTrace();
	            }
	        }
	    }

	    /*
   	    ** ==================== Display player's name and score in the text file =======================
   	    */
	    public static void writeFile(){
	        try{
	            FileWriter fileWrite = new FileWriter("TopScore.txt");
	            for(int i = 0; i<scoreList.size(); i++){
	                fileWrite.write(scoreList.get(i).getName());
	                fileWrite.write("\n");
	                String player = String.valueOf(scoreList.get(i).getTotal());
	                fileWrite.write(player);
	                fileWrite.write("\n");
	            }
	            fileWrite.close();
	        }
	        catch (IOException e){
	            System.out.println("Top Score file is unable to print.");
	            e.printStackTrace();
	        }
	    }
	    
	    @Override
	    public String toString(){
			return String.format("\t%s       \t%d", name, total);
		}

}
