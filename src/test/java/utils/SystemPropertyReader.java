package utils;

public class SystemPropertyReader {

    public static String readProperty() {
        return System.getProperty("selenoidUrl");
    }
}