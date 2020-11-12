package com.lambdaschool.foundation.models;

public class MinMaxInt {
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    public MinMaxInt(){}

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
