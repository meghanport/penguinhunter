import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
    public static int round;
    public static int goneFishing;
    private static final Scanner input = new Scanner(System.in);
    public static ArrayList<String> loanArray = new ArrayList<String>();

   
    public static void initialiseArrayList(){

        String loanAmount = String.valueOf(0); 
        String loanDue = "due" + String.valueOf(0); 

        loanArray.add(loanDue);
        loanArray.add(loanAmount);
          
    }

    
    public static int fishTarget(){

        int min = 10;
        int max = 20;
  
        int fishTarget = (int)Math.floor(Math.random()*(max-min+1)+min);
  
        System.out.println("Today's fish target is: " + fishTarget);  
        return fishTarget;
  
    }

    public static int insurance(){

        int min = 1;
        int max = 10;
  
        int insurance = (int)Math.floor(Math.random()*(max-min+1)+min);
        
        System.out.println("Today's predator insurance premium is: " + insurance);  
        return insurance;
  
    }


    public static String naturalDisaster(){
        String safe = "You have avoided a natural disaster today!";
        int min = 1;
        int max = 100;
  
        int probability = (int)Math.floor(Math.random()*(max-min+1)+min);
        int thisisadisaster = 1;
                     
        if(probability == thisisadisaster){
            int minPenalty = 50;
            int maxPenalty = 100;
            int penalty = (int)Math.floor(Math.random()*(maxPenalty-minPenalty+1)+minPenalty);

            Player.fish = Player.fish - penalty;
                        
            if(Player.fish < 0){
                System.out.println("You have no fish"); 
                
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
            else {
                System.out.println("Number of fish: " + Player.fish); 
            }
        }
        else{
            
            System.out.println(safe); 
            
        }
            
        return safe;
    }


    public static String gameSetUp(){

        String gameSetUp = null;

        Player.newPlayer();

        round = 0;
        
        initialiseArrayList();

        return gameSetUp;
    }


    public static int choiceList(int goneFishing){
        System.out.println(" ");
        System.out.println("Please make a selection:"); 
        System.out.println("1: Catch fish"); 
        System.out.println("2: Borrow from the loan shark"); 
        System.out.println("3: End turn"); 

        Integer selection = Integer.parseInt(input.nextLine());

        while(selection == null || selection < 1 ||selection > 3){
            System.out.println("Please choose 1, 2, or 3");
            selection = Integer.parseInt(input.nextLine());
        }

        if(goneFishing>=2){
            System.out.println("Sorry, you're done fishing for today!"); 
            System.out.println(" ");
            System.out.println("Please make another selection:"); 
            System.out.println("1: Borrow from the loan shark"); 
            System.out.println("2: End this turn"); 

            Integer selection1 = null;
            boolean userInputCorrect = false;

            do {
                selection1 = Integer.parseInt(input.nextLine());
                switch(selection1){
                    case 1:
                    selection1 = 2;
                    userInputCorrect = true;
                    break;
                    case 2:
                    selection1 = 3;
                    userInputCorrect = true;
                    break;

                    default:
                    System.out.println("Please choose 1 or 2!");
                    
                   }
                
            } while (!userInputCorrect);

            selection = selection1;

        }

        return selection;
    }

   
    public static void endTurn(int fish1, int target1, int prem1){
        System.out.println(" ");
        System.out.println("You have chosen to skip this turn!");
        System.out.println("After feeding your family " +  target1 + " fish");
        System.out.println("and paying your insurance premium of " + prem1 + " fish");
        System.out.println("You have " +  fish1 + " fish");
    }

    public static void nextRound() throws IOException{

        ArrayList<String> newDay = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader("newday.txt"))){
            String newLine;

            while ((newLine = br.readLine()) != null) {
                newDay.add(newLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 

        for (int i = 0; i < newDay.size();i++){ 		      
          System.out.println(newDay.get(i)); 		
      }   

        goneFishing = 0;
        round = round + 1;
        int thisRound = round;
        System.out.println(" ");
        System.out.println("This is round: " + round); 

        int todayTarget = fishTarget();
        int prem = insurance();
        naturalDisaster();

        boolean endTurn = false;

        do{ 
            Integer selection = choiceList(goneFishing);

            if((selection.equals(1))){
    
                catchFish();
                goneFishing = goneFishing +1;
                endTurn = false;
            }
            else if((selection.equals(2))){
                loanShark();
                endTurn = false;
    
            } else{
            
            Player.fish = Player.fish - (todayTarget + prem);
            
            endTurn(Player.fish, todayTarget, prem);
            
            payDebt(thisRound);

            endTurn = true;
    
            }
      

        } while(!endTurn);     
                   
    }
    

    public static String chooseWeapon(){

        String weapon = null;
        boolean userInputCorrect = false;
        
        System.out.println("Please choose a weapon");
        System.out.println("1. Trident");
        System.out.println("2. Shark Tooth");
        System.out.println("3. Seaweed Whip");
        System.out.println("4. Throwing Starfish");
        System.out.println("Weapon details");

        do {
            weapon = input.nextLine();
            switch(weapon.toLowerCase()){
                case "1":
                weapon = "trident";
                userInputCorrect = true;
                break;
                case "2":
                weapon = "shark tooth";
                userInputCorrect = true;
                break;
                case "3":
                weapon = "seaweed whip";
                userInputCorrect = true;
                break;
                case "4":
                weapon = "throwing starfish";
                userInputCorrect = true;
                break;
                
                case "trident":
                case "shark tooth":
                case "seaweed whip":
                case "throwing starfish":
               
                userInputCorrect = true;
                break;
                default:

                System.out.println("Weapon details:");
                System.out.println(" ");
                System.out.println("NAME\tSTRONG\tWEAK\tCOST\tMIN CATCH\tMAX CATCH");
                System.out.println("Trident\tMackerel\tShrimp\t2\t2\t10");
                System.out.println("Shark Tooth\tSardine\tCod\t2\t2\t10");
                System.out.println("Seaweed Whip\tCod\tSardine\t4\t4\t20");
                System.out.println("Throwing Starfish\tShrimp\tCod\t3\t3\t16");
                System.out.println(" ");
                System.out.println("Please choose a weapon");
            }
            
        } while (!userInputCorrect);

        System.out.println("You selected a " + weapon);

        return weapon;       

    }

    public static void catchFish(){
        System.out.println(" ");
        System.out.println("You have chosen to catch fish!");
        System.out.println(" ");
        
        // choose weapon

        String chosenWeapon = chooseWeapon();
        
        int indexWeapon = Hunter.arr.indexOf(chosenWeapon);
        String weaponCostString = Hunter.arr.get(indexWeapon + 4);
        int weaponCost = Integer.parseInt(weaponCostString);
       
        System.out.println("The weapon cost is " + weaponCost);
        System.out.println(" ");

        Player.fish = Player.fish - weaponCost;
        System.out.println("You currently have " + Player.fish + " fish");
       
        // random fish caught
       int minFish = Integer.parseInt(Hunter.arr.get(indexWeapon + 5));
       int maxFish = Integer.parseInt(Hunter.arr.get(indexWeapon + 6));
       int fishCaught = (int)Math.floor(Math.random()*(maxFish-minFish+1)+minFish);
    
        boolean userInputCorrect = false;


        int min = 1;
            int max = 4;
            int fishType = (int)Math.floor(Math.random()*(max-min+1)+min);

        do{ 
            System.out.println(" ");
            
        switch(fishType){
            case 1:
            System.out.println("You will be trying to catch sardines!");
            userInputCorrect = true;
            break;
            case 2:
            System.out.println("You will be trying to catch mackerel!");
            userInputCorrect = true;
            break;
            case 3:
            System.out.println("You will be trying to catch shrimp!");
            userInputCorrect = true;
            break;
            case 4:
            System.out.println("You will be trying to catch cod!");
            userInputCorrect = true;
            break;
                } 
            } while (!userInputCorrect);

            String fishToday = null;

            if(fishType == 1){
                fishToday = "sardines";
            }
            if(fishType == 2){
                fishToday = "mackerel";
            }
            if(fishType == 3){
                fishToday = "shrimp";
            }
            if(fishType == 4){
                fishToday = "cod";
            }

            String capWeapon = chosenWeapon.substring(0,1).toUpperCase() + chosenWeapon.substring(1);

            if((Hunter.arr.get(indexWeapon + 2)).equals(fishToday)){
                fishCaught = fishCaught*2;
                System.out.println(" ");
                System.out.println(capWeapon + " is strong against " + fishToday);
                System.out.println("You caught " + fishCaught + " " + fishToday);
                System.out.println("You now have " +  (Player.fish + fishCaught) + " fish");
                Player.fish = Player.fish + fishCaught;
            }
            else if((Hunter.arr.get(indexWeapon + 3)).equals(fishToday)){
                fishCaught = fishCaught/2;
                System.out.println(" ");  
                System.out.println(capWeapon + " is weak against " + fishToday);
                System.out.println("You caught " + fishCaught + " " + fishToday);
                System.out.println("You now have " +  (Player.fish + fishCaught) + " fish");
                Player.fish = Player.fish + fishCaught;
            } else{
                System.out.println(" ");
                System.out.println("You caught " + fishCaught + " " + fishToday);
                System.out.println("You now have " +  (Player.fish + fishCaught) + " fish");
                Player.fish = Player.fish + fishCaught;
            }
           
        }

        public static void loanShark(){

            System.out.println(" ");
            System.out.println("You have chosen to go to the loan shark!");
            System.out.println(" ");
            System.out.println("You have " + Player.fish + " fish");
            System.out.println("Maurice: How many fish would you like to borrow?");
            Integer fishLoan = Integer.parseInt(input.nextLine());
        
        
            while(fishLoan>30){
                System.out.println("Maurice: You can only borrow 30 fish at a time");
                fishLoan = Integer.parseInt(input.nextLine());
            }
        
            Player.loan = Player.loan + (int)Math.round(fishLoan*1.5);
            
            while(Player.loan>100){
                System.out.println("Maurice: You can only have a 100 fish loan");
                System.out.println("Maurice: You can borrow "+ (100 - Player.loan) + " more fish");
                fishLoan = Integer.parseInt(input.nextLine());
                Player.loan = Player.loan + (int)Math.round(fishLoan*1.5);
            }
        
            Player.fish = Player.fish + fishLoan;
            System.out.println("You now have " + Player.fish + " fish!");
            System.out.println(" ");
            System.out.println("Maurice: You have three days to pay back your debt...");
                    
            
            fishLoan = (int)Math.round(fishLoan*1.5);
            String loanAmount = String.valueOf(fishLoan); 
            String loanDue = "due" + String.valueOf(round + 3); 

            loanArray.add(loanDue);
            loanArray.add(loanAmount);
           
        }   
        
        public static void payDebt(int thisRound){

            String isLoanDue = "due" + String.valueOf(thisRound);
  
            if(loanArray.contains(isLoanDue)){
                int loanIndex = (loanArray.indexOf(isLoanDue) + 1);
                Integer loanAmount = Integer.parseInt(loanArray.get(loanIndex));
                Integer toPay = Integer.parseInt(loanArray.get(loanAmount));
                Player.fish = Player.fish - toPay; 
                Player.loan = Player.loan - toPay;
                System.out.println("");
                System.out.println("You paid your loan of " + loanAmount + " fish");
                System.out.println("You now have " + Player.fish  + " fish");
                System.out.println("Your loan is now " + Player.loan + " fish");
                System.out.println("");
            }else{
                System.out.println("");
                System.out.println("You have no debts to pay today!");
                System.out.println("");
            }     
                  

        }

       
}
      

    

