package com.alexdonea.intervalcalculator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class Interval {
    private int id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date start;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date end;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String duration;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("break")
    private String breakDuration;

    public Interval() {

    }

    public Interval(int id, Date start, Date end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(String breakDuration) {
        this.breakDuration = breakDuration;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", duration='" + duration + '\'' +
                ", breakDuration='" + breakDuration + '\'' +
                '}';
    }
}
