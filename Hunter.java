import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.Scanner;

public class Hunter {
    private static final Scanner input = new Scanner(System.in);
    public static ArrayList<String> arr = new ArrayList<String>();

       
        public static void main(String[] args) throws IOException{

            // read in the weapons text file

            try (BufferedReader br = new BufferedReader(new FileReader("weapons.txt")))
            {
                String sCurrentLine;
    
                while ((sCurrentLine = br.readLine()) != null) {
                    String[] lineParts =  sCurrentLine.split(",");
                    for(String text:lineParts){
                        arr.add(text);
                    }
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            } 

            // game intro

            ArrayList<String> penguin = new ArrayList<String>();

            try (BufferedReader br = new BufferedReader(new FileReader("penguin.txt")))
            {
                String penLine;
    
                while ((penLine = br.readLine()) != null) {
                    
                    penguin.add(penLine);
                    
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            } 

            for (int i = 0; i < penguin.size();i++) 
	      { 		      
	          System.out.println(penguin.get(i)); 		
	      }   


          ArrayList<String> maurice = new ArrayList<String>();

            try (BufferedReader br = new BufferedReader(new FileReader("maurice.txt")))
            {
                String shark;
    
                while ((shark = br.readLine()) != null) {
                    
                    maurice.add(shark);
                    
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            } 

          System.out.println("Press any key to play . . .");
          String selection = input.nextLine();

          System.out.println("Welcome to the cool blue waters of the Bass Strait");
          System.out.println("Capitalism doesn't stop in the ocean!");
          System.out.println("Every day you must catch fish to feed your family,");
          System.out.println("But fish are not just food, they're also currency!");
          System.out.println("");
          System.out.println("Press any key to continue . . .");
          selection = input.nextLine();
          System.out.println("Ensure every day you catch enough fish to feed");
          System.out.println("your growing family, but that you don't eat too ");
          System.out.println("many otherwise you won't survive tomorrow!");
          System.out.println("");
          System.out.println("Press any key to continue . . .");
          selection = input.nextLine();
          System.out.println("You are an exceptional hunter but the fish of the ");
          System.out.println("strait are smart. It takes more and more each day ");
          System.out.println("for you to be able to successfully hunt and bring "); 
          System.out.println("home the food. But there are pitfalls that you must ");
          System.out.println("avoid in order to keep catching the fish.");
          System.out.println("But what is life without a little whimsy and chance?");
          System.out.println("");
          System.out.println("Press any key to continue . . .");
          selection = input.nextLine();
          System.out.println("If worse comes to worst you can always take out a loan . . .");
          for (int i = 0; i < maurice.size();i++) 
	      { 		      
	          System.out.println(maurice.get(i)); 		
	      }   
          System.out.println("Maurice can help meet the daily quota if you ");
          System.out.println("fall short but the terms are steep and the price ");
          System.out.println("for not meeting the payment deadline is well...");
          System.out.println("shark... penguin... I think you can guess!");

          System.out.println("Press any key to start the game . . .");
          selection = input.nextLine();
            
          // game set up
            GameState.gameSetUp();

            //pick mode

            String chosenMode = Mode.pickMode();
            System.out.println(chosenMode);


            if((chosenMode.equals("Arcade"))){
                Mode.arcadeMode();
            }
            else{
                Mode.storyMode();
            }


        }

    }


