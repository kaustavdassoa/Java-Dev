package com.example.streams;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupByExample {

    public static void main(String[] args) {
        List<String> fruit_list= Arrays.asList("orange","apple","orange","banana");
        // Group by fruits name
        Map<String , Long> fruit_map= fruit_list.stream()
                .collect(Collectors.groupingBy(Function.identity()
                        ,Collectors.counting()));

        // un-sorted
        System.out.println("fruit list : "+fruit_list.toString());
        System.out.println("fruit map  : "+fruit_map.toString());

        // sort the map by value
        Map<String,Long> fruit_map_2= new LinkedHashMap<>();

        fruit_map.entrySet().stream()
                .sorted(Map.Entry.<String,Long>comparingByValue())
                .forEachOrdered(e -> fruit_map_2.put(e.getKey(), e.getValue()));

        System.out.println("Sorted map :"+fruit_map_2.toString());
    }


}
