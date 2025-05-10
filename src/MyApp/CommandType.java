package MyApp;

public enum CommandType {
    ENCRYPT,
    DECRYPT,
    BRUTE_FORCE;

    public static CommandType fromString (String value){
        return CommandType.valueOf(value.toUpperCase());
    }
}
