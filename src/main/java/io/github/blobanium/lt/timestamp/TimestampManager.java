package io.github.blobanium.lt.timestamp;

public class TimestampManager {
    private static final float secondsToMs = 1000.0F;
    private static final float secondsToNano = 100000000.0F;
    public static float calculateTime(Timestamp stamp, boolean isNano){
        if(isNano){
            return  (System.nanoTime() - stamp.getTimeInNano()) / secondsToNano;
        }else{
            return (System.currentTimeMillis() - stamp.getTimeInMillis()) / secondsToMs;
        }
    }

}
