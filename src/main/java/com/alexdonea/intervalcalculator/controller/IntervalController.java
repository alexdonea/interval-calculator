package com.alexdonea.intervalcalculator.controller;

import com.alexdonea.intervalcalculator.errors.BadRequestException;
import com.alexdonea.intervalcalculator.errors.IntervalNotFoundException;
import com.alexdonea.intervalcalculator.service.IntervalService;
import com.alexdonea.intervalcalculator.entity.Interval;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intervals")
public class IntervalController {
    private final IntervalService intervalService;

    @Autowired
    public IntervalController(IntervalService intervalService) {
        this.intervalService = intervalService;
    }

    // Add an interval or add multiple intervals as json object
    @PostMapping
    public ResponseEntity<List<Interval>> createIntervals(@RequestBody JsonNode requestBody) {
        List<Interval> createdIntervals = intervalService.createIntervals(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIntervals);
    }

    // List all intervals
    @GetMapping
    public ResponseEntity<List<Interval>> getAllIntervals() {
        List<Interval> intervals = intervalService.getIntervals();
        return ResponseEntity.ok(intervals);
    }

    //  View an interval by id
    @GetMapping("/{id}")
    public ResponseEntity<Interval> getIntervalById(@PathVariable int id) {
        Interval interval = intervalService.getIntervalById(id);
        if (interval != null) {
            return ResponseEntity.ok(interval);
        } else {
            throw new IntervalNotFoundException("Interval cannot be found with ID:" + id);
        }
    }

    // Remove an interval by id or by interval
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeInterval(@PathVariable int id) {
        Interval interval = intervalService.deleteById(id);
        if (interval != null) {
            return ResponseEntity.ok().build();
        } else {
            throw new IntervalNotFoundException("Interval cannot be found with ID:" + id);
        }
    }

    // Additional to update an interval by id or by interval
    @PutMapping("/{id}")
    public ResponseEntity<Interval> updateIntervalById(@PathVariable int id, @RequestBody Interval updatedInterval) {
        Interval interval = intervalService.updateInterval(id, updatedInterval);
        if (interval != null) {
            return ResponseEntity.ok(interval);
        } else {
            throw new IntervalNotFoundException("Interval cannot be found with ID:" + id);
        }
    }
}