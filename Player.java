import java.util.Scanner;

public class Player {
    public static String name;
    public static int fish;
    public static int loan;

    private static final Scanner input = new Scanner(System.in);

   
    public String getName() {
        return name;
    }

    public int getFish() {
        return fish;
    }
   
    public static String playerName(){
        System.out.println("Please enter your name:");   
        name= input.nextLine();
        while(Validation.isBlank(name) == true || Validation.lengthWithinRange(name, 3, 12) == false){
            System.out.println("Please enter a valid name:");
            name = input.nextLine();
        }
        return name;
    }
         
    public static String newPlayer(){
        fish =10;
        
        Player.name = playerName();
        String newName = null;
        boolean userInputCorrect = false;

        System.out.println("Your name is " + Player.name);
        System.out.println("Do you want to change your name? Y or N?");
        
        do {
            
            newName = input.nextLine();
            switch(newName.toLowerCase()){
                case "y":
                case "n":
                userInputCorrect = true;
                break;
                default:
                System.out.println("Please pick either Y or N");
            }
            
        } while (!userInputCorrect);
    

        while(newName.equals("y")){
            Player.name = playerName();
            System.out.println("Your name is currently " + Player.name );
            System.out.println("Do you want to change your name? Y or N?");
        
        do {
            newName = input.nextLine();
            switch(newName.toLowerCase()){
                case "y":
                case "n":
                userInputCorrect = true;
                break;
                default:
                System.out.println("Please pick either Y or N");
            }
            
        } while (!userInputCorrect); 
    }

        System.out.println("Your name is " + Player.name );
        return Player.name ;  
    }    
}