package io.github.blobanium.lt.util.math;

public class MathUtil {
    public static int tenMulti = 1000;

    public static double calculateMain(long startingTimeMathUtil) {
        long timeToLoad = System.currentTimeMillis() - startingTimeMathUtil;
		long tTLDeductor = timeToLoad/tenMulti;
		double beforeFinalResult = (timeToLoad - (tTLDeductor * tenMulti));
		double finalResultMath = tTLDeductor + (beforeFinalResult / tenMulti);
        return finalResultMath;
    }

    public static double toastCalc(double finalResult){
        double roundedVariableOutput1 = finalResult*tenMulti;
        double roundedVariableOutput2 = Math.round(roundedVariableOutput1);
        double roundedVariableOutput3 = roundedVariableOutput2/tenMulti;
        return roundedVariableOutput3;
    }
}
