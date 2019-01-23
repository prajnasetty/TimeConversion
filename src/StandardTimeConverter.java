public class StandardTimeConverter implements TimeConverter{

    private TimeValidator _validator;
    private Util _util;

    StandardTimeConverter(TimeValidator validator){
        _validator = validator;
        _util = new Util();
    }

    @Override
    public String convertTime(String fromTime) {
        _validator.validateTime(fromTime);
        int minutes = _validator.getMinutesSinceMidnight();
        return convertFromMinutesToStandard(minutes);
    }

    private String convertFromMinutesToStandard(int minutesSinceMidnight){
        int minutes = minutesSinceMidnight%60;
        int hours = minutesSinceMidnight/60;
        String meridiam = "AM";
        if(hours >=12){
            hours = hours-12;
            meridiam = "PM";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(_util.padZero(hours)).append(":").append(_util.padZero(minutes)).append(" ").append(meridiam);
        return sb.toString();
    }
}
