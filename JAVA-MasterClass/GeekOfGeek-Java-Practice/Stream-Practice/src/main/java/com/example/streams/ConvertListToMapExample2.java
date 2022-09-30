package com.example.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertListToMapExample2 {

    public static void main(String[] args) {
        List<String> sample_list= Arrays.asList("This","is","a","sample","sentence","with","duplicates","string","length");
        Map<Integer,String> word_map=sample_list.stream()
                .collect(Collectors.toMap(s->s.length(), s->s, (oldvalue, newValue) -> newValue));

        //by providing merge functions we can remove conflict
        System.out.println("Word map :"+word_map.toString());

    }
}
