package net.blobanium.example.util;

public class TimeLogger {
    public static void loggerMessage(int messageSelector, double variable, String extraText){

        if(messageSelector == 1) {System.out.println("Minecraft took " + variable + " seconds to initialize");}
        if(messageSelector == 2) {System.out.println("Minecraft took " + variable + " seconds to Fully Load");}
        if(messageSelector == 3) {System.out.println("That is " + variable + " seconds worth of Raw Loading time" + extraText);}
        if(!(messageSelector >= 1 && messageSelector <= 3)){
            throw new IndexOutOfBoundsException("Invalid Value For int messageSelector has been given: " + messageSelector + " ");
        }
    }
}
