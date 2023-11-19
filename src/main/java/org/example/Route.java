package org.example;

import java.io.Serializable;

public class Route implements Serializable {
    private String start;
    private String end;
    private int number;

    public Route(String start, String end, int number) {
        this.start = start;
        this.end = end;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("| %10s | %10s | %3s |", start, end, number);
    }

    public int getNumber() {
        return number;
    }

    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
}
