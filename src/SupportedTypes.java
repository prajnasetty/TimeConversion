public class SupportedTypes {

    public static Types getSupportedType(String type) throws IllegalArgumentException{
        if(type.equalsIgnoreCase("s"))
            return Types.Standard;
        else if(type.equalsIgnoreCase("m"))
            return Types.Military;
        else
            throw new IllegalArgumentException("Time format not supported");
    }

    public enum Types{
        Standard,
        Military
    }
}
