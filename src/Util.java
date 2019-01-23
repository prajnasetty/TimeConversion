public class Util {

    //private int value;

    public void validateMinutes(String minutes) throws IllegalArgumentException{
        int value =  parsePositiveInteger(minutes);
        if(value < 0 || value >= 60)
            throw new IllegalArgumentException(String.format("%s is not between 0 and 60 ", minutes));

    }

    public int parsePositiveInteger(String inString) throws IllegalArgumentException {
        int value;
        try{
           value = Integer.parseInt(inString);
        }
       catch (NumberFormatException ex){
           throw new IllegalArgumentException(String.format("Invalid value passed, %s is not an integer", inString));
       }

        if(value < 0){
            throw  new IllegalArgumentException(String.format("%s is not a valid positive number", inString));
        }
        return value;
    }

    public String padZero(int n){
        String num = Integer.toString(n);
        if(num.length() <=1)
            num = String.format("0%s",num);
        return num;
    }

}
