package com.alexdonea.intervalcalculator.helpers;

import com.alexdonea.intervalcalculator.entity.Interval;
import com.fasterxml.jackson.databind.JsonNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonHelpers {
    // Convert json object or json value to Interval
    private static Interval convertToInterval(JsonNode intervalNode) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Interval interval = new Interval();
        interval.setId(intervalNode.get("id").asInt());
        interval.setStart(dateFormat.parse(intervalNode.get("start").asText()));
        interval.setEnd(dateFormat.parse(intervalNode.get("end").asText()));

        return interval;
    }

    public static List<Interval> convertToIntervalList(JsonNode requestBody) throws ParseException {
        List<Interval> intervalList = new ArrayList<>();

        if (requestBody.isArray()) {
            for (JsonNode intervalNode : requestBody) {
                Interval interval = convertToInterval(intervalNode);
                intervalList.add(interval);
            }
        } else if (requestBody.isObject()) {
            Interval interval = convertToInterval(requestBody);
            intervalList.add(interval);
        }

        return intervalList;
    }
}