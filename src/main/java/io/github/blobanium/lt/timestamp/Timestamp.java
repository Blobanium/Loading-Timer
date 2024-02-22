package io.github.blobanium.lt.timestamp;

public class Timestamp {
    private final Timestamps type;
    private final long timeInMillis;
    private final long timeInNano;

    public Timestamp(Timestamps type){
        timeInNano = System.nanoTime();
        timeInMillis = System.currentTimeMillis();
        this.type = type;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public long getTimeInNano() {
        return timeInNano;
    }

    public Timestamps getType() {
        return type;
    }
}
