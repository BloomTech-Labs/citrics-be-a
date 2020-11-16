package com.lambdaschool.foundation.models;

public class MinMaxDbl {
    private double min = Double.MIN_VALUE;
    private double max = Double.MAX_VALUE;

    public MinMaxDbl(){}

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
