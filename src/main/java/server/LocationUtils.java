package server;

public class LocationUtils {

    public static String endSlash(String s) {
        return s.substring(s.lastIndexOf("/"));
    }

    public static String fixStartSlash(String s) {
        return s.startsWith("/") ? s : "/" + s;
    }

    public static String fixEndSlash(String s) {
        return s.endsWith("/") ? s : s + "/";
    }
}
