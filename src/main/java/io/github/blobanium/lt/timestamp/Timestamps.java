package io.github.blobanium.lt.timestamp;

public enum Timestamps {
    GAME_LOAD(1);

    private final int id;

    Timestamps(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

