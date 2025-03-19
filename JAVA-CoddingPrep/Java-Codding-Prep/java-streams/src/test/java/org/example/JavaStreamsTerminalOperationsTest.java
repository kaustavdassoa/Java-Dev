package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JavaStreamsTerminalOperationsTest {

    JavaStreamsTerminalOperations testService;
    @BeforeEach
    void setup()
    {
        testService=new JavaStreamsTerminalOperations();
    }
    @Test
    void gathersElementsIntoACollection() {
        Map<String,Integer> namesAndYearOfBirth=Map.of("Bhargav Bhatt",1983,"Anand Kumar",1973,"Nayandeep Deka Baruah",1972,"Neena Gupta",1984,"Sucharit Sarkar",1983);

        System.out.println("namesAndYearOfBirth : "+namesAndYearOfBirth);
        List<String> names=testService.gathersElementsIntoACollection(namesAndYearOfBirth);
        System.out.println("names as list : "+names.toString());
    }

    @Test
    void reduceFunction() {
        List<String> list =List.of("one","two","three","four","five","six","seven","eight","nine","ten");
        Optional<String> result=testService.reduceFunction(list);
        System.out.println("List :"+list);
        System.out.println("One String :"+result.get());
    }

    @Test
    void countElements() {

        List<String> list =List.of("one","two","three","four","five","six","seven","eight","nine","ten");
        long count=testService.countElements(list);
        System.out.println("List :"+list+"  | total elements :"+count);
    }
}