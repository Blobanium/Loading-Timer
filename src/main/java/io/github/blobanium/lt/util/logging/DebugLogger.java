package io.github.blobanium.lt.util.logging;

public class DebugLogger {
    private static String intro = "Debug: ";
    public static boolean debug = false;

    public static void debugMessage(int messageSelector, String placeholder){
        if(debug){
            System.out.println(intro + placeholder);
        }
    }
}
