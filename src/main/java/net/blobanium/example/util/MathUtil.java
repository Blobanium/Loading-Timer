package net.blobanium.example.util;

public class MathUtil {

    public static double calculateMain(long startingTimeMathUtil) {
        long timeToLoad = System.currentTimeMillis() - startingTimeMathUtil;
		long tTLDeductor = timeToLoad/1000;
		double beforeFinalResult = (timeToLoad - (tTLDeductor * 1000));
		double finalResultMath = tTLDeductor + (beforeFinalResult / 1000);
        return finalResultMath;
    }

    public static double toastCalc(double finalResult){
        double roundedVariableOutput1 = finalResult*1000;
        double roundedVariableOutput2 = Math.round(roundedVariableOutput1);
        double roundedVariableOutput3 = roundedVariableOutput2/1000;
        return roundedVariableOutput3;
    }
}
