public class MilitaryTimeConverter implements TimeConverter {

    private TimeValidator _validator;
    private Util _util;

    MilitaryTimeConverter(TimeValidator validator){
        _validator = validator;
        _util = new Util();
    }

    public String toMilitaryTime(String fromTime){
        _validator.validateTime(fromTime);
        return convertTime(fromTime);
    }

    @Override
    public String convertTime(String fromTime) {
        _validator.validateTime(fromTime);
        int minutes = _validator.getMinutesSinceMidnight();
        return convertFromMinutesToMilitary(minutes);
    }

    private String convertFromMinutesToMilitary(int minutesSinceMidnight){
        int minutes = minutesSinceMidnight%60;
        int hours = minutesSinceMidnight/60;
        StringBuilder sb = new StringBuilder();
        sb.append(_util.padZero(hours)).append(_util.padZero(minutes)).append(" Hours");
        return sb.toString();
    }

}
