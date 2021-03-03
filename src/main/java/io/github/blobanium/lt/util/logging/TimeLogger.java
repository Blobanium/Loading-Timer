package io.github.blobanium.lt.util.logging;

public class TimeLogger {
    public static void loggerMessage(int messageSelector, double variable, String extraText){

        if(messageSelector == 1) {System.out.println("Minecraft took " + variable + " seconds to initialize.");}
        if(messageSelector == 2) {System.out.println("Minecraft took " + variable + " seconds to fully load.");}
        if(messageSelector == 3) {System.out.println("That is " + variable + " seconds worth of raw loading time" + extraText);}
        if(!(messageSelector >= 1 && messageSelector <= 3)){
            throw new IndexOutOfBoundsException("Invalid value for int messageSelector has been given: " + messageSelector + " ");
        }
    }
}
