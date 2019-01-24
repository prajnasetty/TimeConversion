public class StandardTimeValidator implements TimeValidator {

    private String [] splitTime;
    private String hours;
    private String minutes;
    private String meridiem;
    private Util _util;

     StandardTimeValidator(String inTime){
        _util = new Util();
        splitTimeString(inTime);
    }
    @Override
    public void validateTime(String time) throws IllegalArgumentException {
        validateLength();
        validateRange();
        validateMeridiem();
    }

    @Override
    public int getMinutesSinceMidnight() {
        int hoursSinceMidnight = _util.parsePositiveInteger(hours);
        if(meridiem.equals("PM")){
            if(hoursSinceMidnight == 12) {
                hoursSinceMidnight -= 12;
            }
            hoursSinceMidnight +=12;
        }
        if(meridiem.equals("AM")){
            if(hoursSinceMidnight == 12){
                hoursSinceMidnight -= 12;
            }
        }
        return hoursSinceMidnight*60 + _util.parsePositiveInteger(minutes);
    }

    private void splitTimeString(String sTime) throws IllegalArgumentException{
        splitTime = sTime.split(" ");
        if(splitTime.length != 2) {
            throw new IllegalArgumentException("Standard time format should be passed as HH:MM AM or HH:MM PM");
        }
        String [] timeArray = splitTime[0].split(":");
        if(timeArray.length != 2){
            throw new IllegalArgumentException("Missing (:) separated hours and minutes in time");
        }

        hours = timeArray[0];
        minutes = timeArray[1];
        meridiem = splitTime[1].toUpperCase();

    }

    private void validateLength() throws IllegalArgumentException{
        char [] lengthHour = hours.toCharArray();
        char [] lengthMinutes = minutes.toCharArray();
        char [] lengthMeridiem = meridiem.toCharArray();
        validateLength(lengthMeridiem);
        validateLength(lengthMinutes);
        validateLengthHour(lengthHour);
    }

   /* private void validateLength(char [] x){
        validateLength(x,2);
    } */
   private void validateLengthHour(char [] x){
        if(x.length != 2 && x.length!= 1)
            throw new IllegalArgumentException("Invalid time format,Standard time format should be passed as HH:MM AM or HH:MM PM");

   }

    private void validateLength(char [] x) throws IllegalArgumentException{
        if(x.length != 2) {
                throw new IllegalArgumentException("Invalid time format,Standard time format should be passed as HH:MM AM or HH:MM PM");
        }
    }

    private void validateRange() throws IllegalArgumentException{
        _util.validateMinutes(minutes);
        validateHour();
    }

    private void validateMeridiem() throws IllegalArgumentException{
        if(!(meridiem.equals("AM") || meridiem.equals("PM")))
            throw new IllegalArgumentException("Invalid time format,Standard time format should be passed as HH:MM AM or HH:MM PM");
    }

    private void validateHour(){
        int hourValue =  _util.parsePositiveInteger(hours);
        if(hourValue < 1 || hourValue > 12)
            throw new IllegalArgumentException(String.format("Invalid hour entered,%s is not between 1 and 12 ", hours));
    }

}
