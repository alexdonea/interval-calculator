package com.alexdonea.intervalcalculator.service;
import com.alexdonea.intervalcalculator.entity.Interval;
import com.alexdonea.intervalcalculator.errors.BadRequestException;
import com.alexdonea.intervalcalculator.helpers.JsonHelpers;
import com.alexdonea.intervalcalculator.utils.IntervalCalculator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class IntervalService {

    private final List<Interval> intervals;   // Simulated persistence layer

    public IntervalService() {
        intervals = new ArrayList<>();
    }

    // Create intervals and if same id exist then update exiting id to last
    public List<Interval> createIntervals(JsonNode requestBody) {
        List<Interval> newIntervals;
        try {
            newIntervals = JsonHelpers.convertToIntervalList(requestBody);
            for (Interval interval : newIntervals) {
                int existingIndex = findIntervalIndexById(interval.getId());
                if (existingIndex != -1) {
                    int lastIndex = intervals.size() - 1;
                    int newId = intervals.get(lastIndex).getId() + 1;
                    interval.setId(newId);
                    intervals.add(interval);
                } else {
                    intervals.add(interval);
                }
            }
            updateDurationAndBreak();
        } catch (ParseException e) {
            throw new BadRequestException("Bad request error:" +e.getMessage());
        }
        return new ArrayList<>(newIntervals);
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(intervals);
    }

    public Interval getIntervalById(int id) {
        return intervals.stream()
                .filter(interval -> interval.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Interval deleteById(int id) {
        Iterator<Interval> iterator = intervals.iterator();
        while (iterator.hasNext()) {
            Interval interval = iterator.next();
            if (interval.getId() == id) {
                iterator.remove();
                updateDurationAndBreak();
                return interval;
            }
        }
        return null;
    }

    public Interval updateInterval(int id, Interval updatedInterval) {
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).getId() == id) {
                updatedInterval.setId(id);
                intervals.set(i, updatedInterval);
                updateDurationAndBreak();
                return updatedInterval;
            }
        }
        return null;
    }

    private void updateDurationAndBreak() {
        if (intervals != null && !intervals.isEmpty()) {
            // Sort intervals by id, just in case you need it
           // intervals.sort(Comparator.comparingInt(Interval::getId));

            // Compute duration and break time between intervals except first one
            // If start time is equal to end time, it will always put 0s because it's invalid
            // Same for break time, it compute always with prev interval.
            for (int i = 0; i < intervals.size(); i++) {
                Interval interval = intervals.get(i);
                    long durationInMillis = interval.getEnd().getTime() - interval.getStart().getTime();
                    String duration = IntervalCalculator.calculateTime(durationInMillis);
                    interval.setDuration(duration);
                if (i > 0) {
                    Interval prevInterval = intervals.get(i - 1);
                        long breakInMillis = interval.getStart().getTime() - prevInterval.getEnd().getTime();
                        String breakDuration = IntervalCalculator.calculateTime(breakInMillis);
                        interval.setBreakDuration(breakDuration);
                }
            }
        }
    }

    private int findIntervalIndexById(int id) {
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}