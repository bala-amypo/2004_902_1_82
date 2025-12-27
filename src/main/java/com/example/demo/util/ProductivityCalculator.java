package com.example.demo.util;

public class ProductivityCalculator {

    // Main method used by most tests
    public static double computeScore(double hours, int tasks, int meetings) {

        // Handle NaN or negative input
        if (Double.isNaN(hours) || hours < 0 || tasks < 0 || meetings < 0) {
            return 0.0;
        }

        // Base productivity formula
        double score = (hours * 10) + (tasks * 5) - (meetings * 2);

        // Clamp range 0–100
        if (score < 0) score = 0;
        if (score > 100) score = 100;

        // Round to 2 decimals
        return Math.round(score * 100.0) / 100.0;
    }

    // Overload for int hours
    public static double computeScore(int hours, int tasks, int meetings) {
        return computeScore((double) hours, tasks, meetings);
    }
}
