public class Validation {

    public static boolean isBlank(String input){
        if(input.isEmpty())
            return true;
        else
            return false;
    }

    public static boolean lengthWithinRange(String input, int min, int max ){
        if(input.length() <= min && input.length() >= max)
            return true;
        else
            return false;
    }

   
    
}


