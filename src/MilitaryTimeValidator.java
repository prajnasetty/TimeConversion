public class MilitaryTimeValidator implements TimeValidator {

    private String [] splitTime;
    private String hours;
    private String minutes;
    private Util _util;

    MilitaryTimeValidator(String mTime){
        _util = new Util();
        splitTimeString(mTime);
    }
    @Override
    public void validateTime(String time) throws IllegalArgumentException {
        validateLength(time);
        _util.parsePositiveInteger(time);
        validateRange();
    }

    @Override
    public int getMinutesSinceMidnight() throws IllegalArgumentException {
        int minutesSinceMidnight =  _util.parsePositiveInteger(minutes) + 60*_util.parsePositiveInteger(hours);
        if(minutesSinceMidnight > 1439 || minutesSinceMidnight < 0) {
            throw new IllegalArgumentException("Invalid time passed, minutes passed since midnight should be less than minutes in a day");
        }
        return minutesSinceMidnight;
    }

    private void splitTimeString(String mTime) {
        splitTime = mTime.split("");
        validateLength(mTime);
        hours = splitTime[0] + splitTime[1];
        minutes = splitTime[2] + splitTime[3];
    }


    private void validateLength(String mTime) throws IllegalArgumentException{
        if(mTime.length() != 4) {
            throw new IllegalArgumentException("Military time should have four numbers HHMM");
        }
    }

    private void validateRange() throws IllegalArgumentException{
        _util.validateMinutes(minutes);
        validateHours();
    }

    private void validateHours() throws IllegalArgumentException{
       int hourValue =  _util.parsePositiveInteger(hours);
        if(hourValue < 0 || hourValue >= 24)
            throw new IllegalArgumentException(String.format("Invalid hour entered,%s is not between 0 and 23 ", hours));
    }
}
