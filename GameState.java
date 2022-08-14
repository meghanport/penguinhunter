import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameState {
    public static int round;
    private static int goneFishing;
    private static final Scanner input = new Scanner(System.in);
    
    private static ArrayList<String> loanArray = new ArrayList<String>();
 
    private static  final int MIN_FISH_TARGET = 10;
    private static  final  int MAX_FISH_TARGET = 20;

    private static  final  int MIN_INSURANCE = 1;
    private static  final  int MAX_INSURANCE = 10;

    private static  final  int MIN_DISASTER = 1;
    private static  final  int MAX_DISASTER = 100;

    private static  final int MIN_PENALTY = 50;
    private static  final int MAX_PENALTY = 100;

   
    private static void initialiseArrayList(){
        String loanAmount = String.valueOf(0); 
        String loanDue = "due" + String.valueOf(0); 

        loanArray.add(loanDue);
        loanArray.add(loanAmount);   
    }

    
    private static int fishTarget(){
        
        int fishTarget = (int)Math.floor(Math.random()*(MAX_FISH_TARGET-MIN_FISH_TARGET+1)+MIN_FISH_TARGET);
  
        System.out.println("Today's fish target is: " + fishTarget);  
        return fishTarget;
    }

    private static int insurance(){
  
        int insurance = (int)Math.floor(Math.random()*(MAX_INSURANCE-MIN_INSURANCE+1)+MIN_INSURANCE);
        
        System.out.println("Today's predator insurance premium is: " + insurance);  
        return insurance;
    }


    private static String naturalDisaster(){
        String safe = "You have avoided a natural disaster today!";
  
        int probability = (int)Math.floor(Math.random()*(MAX_DISASTER-MIN_DISASTER+1)+ MIN_DISASTER);
        int thisisadisaster = 1;
                     
        if(probability == thisisadisaster){
            int penalty = (int)Math.floor(Math.random()*(MAX_PENALTY-MIN_PENALTY+1)+MIN_PENALTY);

            Player.fish = Player.fish - penalty;

            checkDead(Player.fish);
                        
            if(Player.fish > 0){
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


    private static int choiceList(){
        while (true) {
            System.out.println(" ");
            System.out.println("Please make a selection:");
            System.out.println("1: Catch fish"); 
            System.out.println("2: Borrow from the loan shark"); 
            System.out.println("3: End turn"); 
    
            String selection = null;
    
            selection = input.nextLine();
            switch(selection){
                case "1":
                case "2":
                case "3":
                break;
                default:
                System.out.println("Please choose 1, 2, or 3");
                continue;
            }
    
            if (selection.equals("1") && goneFishing == 2){
                System.out.println(" ");
                System.out.println("Sorry, you're done fishing for today!"); 
                System.out.println(" ");
                System.out.println("Please make another selection");
                continue;
            }
    
            return Integer.parseInt(selection);
        }
    }

   
    private static void endTurn(int fish1, int target1, int prem1){
        System.out.println(" ");
        System.out.println("You have chosen to end this turn!");
        System.out.println("After feeding your family " +  target1 + " fish");
        System.out.println("and paying your insurance premium of " + prem1 + " fish");
        System.out.println("You have " +  fish1 + " fish");

    }

    public static void handleRound() throws IOException{
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

        while(true){
            Integer selection = choiceList();
            if((selection.equals(1))){
                catchFish();
                goneFishing = goneFishing +1;   
            }
            else if((selection.equals(2))){
                loanShark();
            }
            else if((selection.equals(3))){
                break;
            }

        }
            Player.fish = Player.fish - (todayTarget + prem);
            endTurn(Player.fish, todayTarget, prem);
            
            if(Player.fish>0){
                payDebt(thisRound);
            }

                        
    }
    

    private static String chooseWeapon(){
        String weapon = null;
        boolean userInputCorrect = false;
        
        System.out.println("Please choose a weapon");
        System.out.println("1. Swordfish");
        System.out.println("2. Throwing Starfish");
        System.out.println("3. Stingray");
        System.out.println("4. Orca");
        System.out.println("5. Weapon details");

        do {
            weapon = input.nextLine();
            switch(weapon.toLowerCase()){
                case "1":
                weapon = "swordfish";
                userInputCorrect = true;
                break;
                case "2":
                weapon = "throwing starfish";
                userInputCorrect = true;
                break;
                case "3":
                weapon = "stringray";
                userInputCorrect = true;
                break;
                case "4":
                weapon = "orca";
                userInputCorrect = true;
                break;
                
                case "swordfish":
                case "throwing starfish":
                case "stingray":
                case "orca":
                userInputCorrect = true;
                break;

                case "5":
                System.out.println("Weapon details:");
                System.out.println(" ");
                System.out.println("NAME\t\t\tSTRONG\t\tWEAK\t\tCOST\tMIN CATCH\tMAX CATCH");
                System.out.println("Swordfish\t\tSardine\t\tMackerel\t2\t3\t\t7");
                System.out.println("Throwing Starfish\tMackerel\tSardine\t\t4\t7\t\t11");
                System.out.println("Stringrayt\t\tCod\t\tShrimp\t\t7\t11\t\t15");
                System.out.println("Orca\t\t\tShrimp\t\tCod\t\t10\t15\t\t19");
                System.out.println(" ");
                System.out.println("Please choose a weapon");
                userInputCorrect =false;
                break;

                default:

                System.out.println("Weapon details:");
                System.out.println(" ");
                System.out.println("NAME\t\t\tSTRONG\t\tWEAK\t\tCOST\tMIN CATCH\tMAX CATCH");
                System.out.println("Swordfish\t\tSardine\t\tMackerel\t2\t3\t\t7");
                System.out.println("Throwing Starfish\tMackerel\tSardine\t\t4\t7\t\t11");
                System.out.println("Stringrayt\t\tCod\t\tShrimp\t\t7\t11\t\t15");
                System.out.println("Orca\t\t\tShrimp\t\tCod\t\t10\t15\t\t19");
                System.out.println(" ");
                System.out.println("Please choose a weapon");
            }
            
        } while (!userInputCorrect);

        System.out.println("You selected a " + weapon);

        return weapon;       
    }

    private static void catchFish(){
        System.out.println(" ");
        System.out.println("You have chosen to catch fish!");
        System.out.println(" ");

        String chosenWeapon = chooseWeapon();
        
        int indexWeapon = Hunter.arr.indexOf(chosenWeapon);
        String weaponCostString = Hunter.arr.get(indexWeapon + 4);
        int weaponCost = Integer.parseInt(weaponCostString);
       
        System.out.println("The weapon cost is " + weaponCost);
        System.out.println(" ");

        Player.fish = Player.fish - weaponCost;
        System.out.println("You currently have " + Player.fish + " fish");
       
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

        private static void loanShark(){
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
            
            String loanDue = "due" + String.valueOf(round + 3); 
            String loanAmount = String.valueOf(fishLoan); 

            loanArray.add(loanDue);
            loanArray.add(loanAmount);

            
           
        }   
        
        private static void payDebt(int thisRound){
            String isLoanDue = "due" + String.valueOf(thisRound);
  
            if(loanArray.contains(isLoanDue)){
                int loanIndex = (loanArray.indexOf(isLoanDue) + 1);
                Integer loanAmount = Integer.parseInt(loanArray.get(loanIndex));

                Player.fish = Player.fish - loanAmount; 
                Player.loan = Player.loan - loanAmount;
                System.out.println("");
                System.out.println("You paid your loan of " + loanAmount + " fish");
                System.out.println("You now have " + Player.fish  + " fish");
                System.out.println("Your loan is now " + Player.loan + " fish");
                System.out.println("");

                checkDead(Player.fish);

            }else{
                System.out.println("");
                System.out.println("You have no debts to pay today!");
                System.out.println("");
            }     
        }

        
        public static void checkDead(int fish){

            if(fish<0){

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
        }

       
}