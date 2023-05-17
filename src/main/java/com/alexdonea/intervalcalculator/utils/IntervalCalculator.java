package com.alexdonea.intervalcalculator.utils;

import com.alexdonea.intervalcalculator.entity.Interval;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class IntervalCalculator {
    public static String calculateTime(long durationMillis) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(durationMillis) % 60;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(durationMillis) % 60;
        long hours = TimeUnit.MILLISECONDS.toHours(durationMillis) % 24;
        long days = TimeUnit.MILLISECONDS.toDays(durationMillis) % 365;
        long years = TimeUnit.MILLISECONDS.toDays(durationMillis) / 365;

        StringBuilder sb = new StringBuilder();
        if (years > 0) {
            sb.append(years).append("y");
            sb.append(days).append("d");
            return sb.toString();
        }
        if (days > 0) {
            sb.append(days).append("d");
            sb.append(hours).append("h");
            return sb.toString();
        }
        if (hours > 0) {
            sb.append(hours).append("h");
            sb.append(minutes).append("m");
            return sb.toString();
        }
        if (minutes > 0) {
            sb.append(minutes).append("m");
            sb.append(seconds).append("s");
            return sb.toString();
        }
        if (seconds >= 0) {
            sb.append(seconds).append("s");
        }

        return sb.toString();
    }

}
