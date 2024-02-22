package io.github.blobanium.lt.timestamp;

public class Timestamp {
    private final long timeInMillis;
    private final long timeInNano;

    public Timestamp(){
        timeInNano = System.nanoTime();
        timeInMillis = System.currentTimeMillis();
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public long getTimeInNano() {
        return timeInNano;
    }
}
