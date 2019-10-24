package com.example.realtimeguestlist;

public class Guest {
    private String name;
    private String type;
    private int extras;

    public Guest(String name, String type, int extras) {
        this.name = name;
        this.type = type;
        this.extras = extras;
    }
    public Guest(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExtras() {
        return extras;
    }

    public void setExtras(int extras) {
        this.extras = extras;
    }
}
