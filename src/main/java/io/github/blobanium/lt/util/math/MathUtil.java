package io.github.blobanium.lt.util.math;

import io.github.blobanium.lt.config.ConfigReader;

public class MathUtil {
    private static int tenMulti = 1000;

    public static double calculateMain(long startingTimeMathUtil) {
        if (ConfigReader.insanePrecision) {
            tenMulti = 1000000000;
            long timeToLoad = System.nanoTime() - startingTimeMathUtil;
            long tTLDeductor = timeToLoad/tenMulti;
            double beforeFinalResult = (timeToLoad - (tTLDeductor * tenMulti));
            double finalResultMath = tTLDeductor + (beforeFinalResult / tenMulti);
            return finalResultMath;
        } else {
            long timeToLoad = System.currentTimeMillis() - startingTimeMathUtil;
            long tTLDeductor = timeToLoad / tenMulti;
            double beforeFinalResult = (timeToLoad - (tTLDeductor * tenMulti));
            double finalResultMath = tTLDeductor + (beforeFinalResult / tenMulti);
            return finalResultMath;
        }
    }

    public static double roundValue(double finalResult){
        double roundedVariableOutput1 = finalResult*tenMulti;
        double roundedVariableOutput2 = Math.round(roundedVariableOutput1);
        double roundedVariableOutput3 = roundedVariableOutput2/tenMulti;
        return roundedVariableOutput3;
    }
}
