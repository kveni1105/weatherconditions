package com.kce.wether.dto;

public class MonthlyStatusDTO {

    private double min;
    private double max;
    private double median;

    public MonthlyStatusDTO(double min, double max, double median) {
        this.min = min;
        this.max = max;
        this.median = median;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getMedian() {
        return median;
    }
}