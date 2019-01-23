public class StandardTimeValidator implements TimeValidator {

    private String [] splitTime;
    private String hours;
    private String minutes;
    private String meridiem;
    private Util _util;

    public StandardTimeValidator(String inTime){
        _util = new Util();
        splitTimeString(inTime);
    }
    @Override
    public void validateTime(String time) throws IllegalArgumentException {
        validateLength(time);
        validatteRange();
        validateMeridiem();
    }

    @Override
    public int getMinutesSinceMidnight() {
        int hoursSinceMidnight = _util.parsePositiveInteger(hours);
        if(meridiem.equals("PM")){
            hoursSinceMidnight +=12;
        }
        return hoursSinceMidnight*60 + _util.parsePositiveInteger(minutes);
    }

    private void splitTimeString(String sTime) throws IllegalArgumentException{
        splitTime = sTime.split(" ");
        if(splitTime.length != 2) {
            throw new IllegalArgumentException("Standard time format should be pased as HH:MM AM or HH:MM PM");
        }
        String [] timeArray = splitTime[0].split(":");
        if(timeArray.length != 2){
            throw new IllegalArgumentException("Missing (:) separated hours and minutes in time");
        }

        hours = timeArray[0];
        minutes = timeArray[1];
        meridiem = splitTime[1].toUpperCase();

    }

    private void validateLength(String time) throws IllegalArgumentException{
        char [] lengthHour = hours.toCharArray();
        char [] lengthMinutes = minutes.toCharArray();
        char [] lengthMeridiem = meridiem.toCharArray();
        validateLength(lengthMeridiem);
        validateLength(lengthMinutes);
        validateLength(lengthHour, 1);
    }

    private void validateLength(char [] x){
        validateLength(x,2);
    }

    private void validateLength(char [] x, int expectedLength) throws IllegalArgumentException{
        if(x.length != expectedLength)
            throw  new IllegalArgumentException();
    }

    private void validatteRange() throws IllegalArgumentException{
        _util.validateMinutes(minutes);
        validateHour();
    }

    private void validateMeridiem() throws IllegalArgumentException{
        if(!(meridiem.equals("AM") || meridiem.equals("PM")))
            throw new IllegalArgumentException("");
    }

    private void validateHour(){
        int hourValue =  _util.parsePositiveInteger(hours);
        if(hourValue < 1 || hourValue > 12)
            throw new IllegalArgumentException();
    }

}
