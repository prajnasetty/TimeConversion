import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input time format S for Standard and M for military:");
        String inputTimeFormat = scanner.nextLine();

        System.out.println("Please enter te time(eg: '0000' or '12:00 AM' ):");
        String inTime = scanner.nextLine();

        System.out.println("Please enter the time format for conversion S for Standard and M for military:");
        String toTimeFormat = scanner.nextLine();

        String returnTime =  getTimeConverter(inputTimeFormat, toTimeFormat, inTime).convertTime(inTime);
        System.out.println(returnTime);

    }

    private static TimeConverter getTimeConverter(String fromTimeFormat, String toTimeFormat,  String time){
        switch (SupportedTypes.getSupportedType(toTimeFormat)){
            case Standard:
                return new StandardTimeConverter(getTimeValidator(fromTimeFormat, time));
            case Military:
                return new MilitaryTimeConverter(getTimeValidator(fromTimeFormat, time));
        }
        return null;
    }

    private static TimeValidator getTimeValidator(String fromTimeFormat, String time){
        switch (SupportedTypes.getSupportedType(fromTimeFormat)){
            case Military:
                return new MilitaryTimeValidator(time);
            case Standard:
                return new StandardTimeValidator(time);
        }
        return null;
    }


}
