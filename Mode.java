import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Mode {
    public String mode;

    private static final Scanner input = new Scanner(System.in);

    public Mode(String newMode){
        this.mode = newMode;
    }

    public String getMode() {
        return mode;
    }

    public static String pickMode(){
        
        String pickMode = null;
        boolean userInputCorrect = false;
        System.out.println("");
        System.out.println("Please pick your mode:");
        System.out.println("Arcade or Story?");  

        do {
            pickMode = input.nextLine();
            switch(pickMode.toLowerCase()){
                case "arcade":
                case "story":
                userInputCorrect = true;
                break;
                default:
                System.out.println("Please pick either Arcade or Story Mode");
            }
            
        } while (!userInputCorrect);

        pickMode = pickMode.substring(0,1).toUpperCase() + pickMode.substring(1);
        System.out.println("You selected " + pickMode + " Mode");

        
        return pickMode;        
        
    }

    public static int readHighScore() throws java.io.IOException{
        Scanner s = new Scanner(new File("highscore.txt"));
        ArrayList<Integer> highscore = new ArrayList<Integer>();

        while (s.hasNext()){
            if(s.hasNextInt()){
                highscore.add(s.nextInt());
            } else {
                s.next();
            }
        }

            int highestNumber = Collections.max(highscore);   
            return highestNumber;
    }


    public static void writeHighScore(int contents)
    {
        String fileName = "highscore.txt";
        try
        {
            PrintWriter outputFile = new PrintWriter(fileName);
            outputFile.println(contents);
            outputFile.close();
        }
        catch(Exception e)
        {
            System.out.println("Caught in exception while writing the file");
        }
    }
    
    public static void arcadeMode() throws IOException{
        System.out.println("");
        String selection = null;
        System.out.println("This is Arcade Mode");
        System.out.println("The game goes on until you are not able to get");
        System.out.println("enough fish required for the end of the day.");
        System.out.println("Highest number of days survived will be recorded when you ... die");
        System.out.println(" ");

        int currentHighScore = Mode.readHighScore();

        System.out.println("The current high score is: " + currentHighScore + " days");
        System.out.println(" ");
        System.out.println("Press any key to start the game . . .");
        selection = input.nextLine();

        while(Player.fish > 0){
            GameState.nextRound();
        }

        int roundsReached = GameState.round;
        if(roundsReached<2){
            System.out.println("You played " + roundsReached + " round!");
        }else{
            System.out.println("You played " + roundsReached + " rounds!");
        }
       
        writeHighScore(roundsReached);


        System.out.println("You have run out of fish ...");

        ArrayList<String> dead = new ArrayList<String>();

            try (BufferedReader br = new BufferedReader(new FileReader("dead.txt"))){
                String deadLine;
                while ((deadLine = br.readLine()) != null) {
                    dead.add(deadLine);
                }
            } catch (IOException e){
                e.printStackTrace();
            } 

            for (int i = 0; i < dead.size();i++){ 		      
	          System.out.println(dead.get(i)); 		
	      }   

    }


    public static void storyMode() throws IOException{

        int selectRounds = 0;
        System.out.println("");
        System.out.println("This is Story Mode");
        System.out.println("How many rounds would you like to play?");

        selectRounds = Integer.parseInt(input.nextLine());
        
        while(selectRounds<5){
            System.out.println("You must play 5 or more rounds!");
            selectRounds = Integer.parseInt(input.nextLine());
        }

        System.out.println("How many fish do you hope to have after your " + selectRounds + " turns?");

        int fishGoal = Integer.parseInt(input.nextLine());

        while(fishGoal<=24){
            System.out.println("Aim bigger! Aim for at least 25 fish");
            fishGoal = Integer.parseInt(input.nextLine());
        }

        while(GameState.round <= selectRounds){
            GameState.nextRound();
        }

        System.out.println("You finished your " + selectRounds + " turns are over!");
        System.out.println("You have " + Player.fish + " fish! And your goals was " + fishGoal);

        if(Player.fish > fishGoal){
            System.out.println("You reached your goal!!");
        } else{
            System.out.println("You didn't catch quite enough fish :(");
        }
    }

    public void setMode(String newMode) {
        this.mode = newMode;
    }

    
}

