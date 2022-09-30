package com.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertListToMapExample {

    public static void main(String[] args) {
        List<String> wordList= Arrays.asList("This","is","a","sample","sentence");

        Map<String,Integer> wordMap= wordList.stream()
                .collect(Collectors.toMap(s -> s, s -> s.length()));
        System.out.println("Word List :"+wordList.toString());
        System.out.println("Word Map  :"+wordMap.toString());
    }
}
