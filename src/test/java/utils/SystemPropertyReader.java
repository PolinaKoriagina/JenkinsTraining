package utils;

public class SystemPropertyReader {

    public static String getSelenoidUrl() {
        return System.getProperty("selenoidUrl", "selenoid.autotests.cloud");
    }

    public static String getVideoUrl() {
        return System.getProperty("videoUrl");
    }
}