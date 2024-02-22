package io.github.blobanium.lt.timestamp;

public class TimestampManager {
    private static final float secondsToMs = 1000.0F;
    private static final float secondsToNano = 100000000.0F;

    public static Timestamp[] timestamps = new Timestamp[8];

    public static float calculateTime(Timestamps stamptype, boolean isNano){
        Timestamp stamp = timestamps[stamptype.getId()];
        if(isNano){
            return  (System.nanoTime() - stamp.getTimeInNano()) / secondsToNano;
        }else{
            return (System.currentTimeMillis() - stamp.getTimeInMillis()) / secondsToMs;
        }
    }

    public static void createTimeStamp(Timestamps type){
        timestamps[type.getId()] = new Timestamp(type);
    }

}
