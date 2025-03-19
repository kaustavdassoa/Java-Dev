package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class JavaStreamsCollectorOperationsTest {


    JavaStreamsCollectorOperations testClass;

    @BeforeEach
    void init()
    {
        testClass = new JavaStreamsCollectorOperations();
    }

    @Test
    void listToSet() {
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "banana", "apple", "grape");
        System.out.println(testClass.listToSet(words));
    }

    @Test
    void listToList() {
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "banana", "apple", "grape");
        System.out.println(testClass.listToList(words));
    }

    @Test
    void joinElementOfAList() {
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "banana", "apple", "grape");
        System.out.println(testClass.joinElementOfAList(words,"|"));
        System.out.println(testClass.joinElementOfAList(words,","));
        System.out.println(testClass.joinElementOfAList(words,";"));
    }

    @Test
    void groupByStringLength() {
    }

    @Test
    void groupByOccurence() {

        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "banana", "apple", "grape");
        System.out.println("Input :"+words);
        Map<Integer, List<String>> result = testClass.groupByOccurence(words);
        System.out.println("result :"+result);
    }

    @Test
    void groupByElementOccurences() {
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "banana", "apple", "grape");
        System.out.println("Input :"+words);
        Map<String,Long> result = testClass.groupByElementOccurences(words);
        System.out.println("result :"+result);
    }

    @Test
    void partitioningBy() {

        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "banana", "apple", "grape");
        System.out.println("Input :"+words);
        Map<Boolean, List<String>> result = testClass.partitioningBy(words);
        System.out.println("result :"+result);
    }

    @Test
    void summarizingInt() {

        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "banana", "apple", "grape");
        System.out.println("Input :"+words);
        IntSummaryStatistics result = testClass.summarizingInt(words);
        System.out.println("IntSummaryStatistics length of char :"+result);

    }
}