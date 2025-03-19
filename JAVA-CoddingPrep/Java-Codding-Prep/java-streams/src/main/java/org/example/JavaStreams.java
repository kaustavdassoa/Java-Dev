package org.example;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;


public class JavaStreams  {

    private final static List<String> NAME_LIST=Arrays.asList("Alex", "bob", "Charli");

    public static void main(String[] args) {

        System.out.println("Java Streams");
        streamCreationDemo();
        streamsIntermediateOperations();

    }


    protected static void streamCreationDemo()
    {
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> streamFromList = list.stream();

        // From array
        String[] array = {"a", "b", "c"};
        Stream<String> streamFromArray = Arrays.stream(array);

        // Stream builder
        Stream<String> streamBuilder = Stream.<String>builder()
                .add("a").add("b").add("c").build();

        // Stream.of()
        Stream<String> streamOf = Stream.of("a", "b", "c");


        System.out.println("streamFromList :"+streamFromList.collect(Collectors.joining(",")));
        System.out.println("streamFromArray :"+streamFromArray.collect(Collectors.joining(",")));
        System.out.println("streamBuilder :"+streamBuilder.collect(Collectors.joining(",")));
        System.out.println("streamOf :"+streamOf.collect(Collectors.joining(",")) );
    }


    protected static void streamsIntermediateOperations()
    {
        // filter - keeps elements that match a predicate
        Stream<String> filtered = NAME_LIST.stream()
                .filter(name -> name.startsWith("A"));
        System.out.println("Filter NAME_LIST elements starting with 'A' "+filtered.collect(Collectors.joining(",")));

        // map - transforms each element
        Stream<Integer> length= NAME_LIST.stream().map(String::length);
        System.out.println("Count char length for each element in NAME_LIST"+length.collect(Collectors.toList()));



    }




}


