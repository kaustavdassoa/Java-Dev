package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JavaStreamsIntermediateOperationsTest {

    JavaStreamsIntermediateOperations testClass;

    @BeforeEach
    void init()
    {
        testClass = new JavaStreamsIntermediateOperations();
    }

    @Test
    void flatMapExample() {

        List<String> list1 =List.of("one","two","three");
        List<String> list2 =List.of("four","five","six");
        List<String> list3 =List.of("seven","eight","nine") ;


        Stream<String> result=testClass.flatMapExample(list1,list2,list3);
        System.out.println("Result Stream :"+result.collect(Collectors.toList()));

    }


    @Test
    void distinctExample() {
        List<String> entryList =List.of("Alex","Bob","Ram","Mina","Bob","Alex");
        //This will produce an mutableList, and available since java 8+
        List<String> listOfVisitors=testClass.distinctExample(entryList).collect(Collectors.toList());
        System.out.println("list Of visitors :"+listOfVisitors.stream().toList());
        listOfVisitors.add("new guest");
        System.out.println("new guest added to above list :"+listOfVisitors.stream().toList());
        listOfVisitors.add("new guest");
        System.out.println("new guest again added Final List has duplicates now :"+listOfVisitors.stream().toList());

        assertTrue(listOfVisitors.size() > listOfVisitors.stream().distinct().count(), "listOfVisitors allows duplicates to be added");

        //This will produce an immutableList, and available since java 16+,  cannot add, remove, or modify elements after creation , Attempting to modify will result in UnsupportedOperationException
        List<String> listOfVistorsFinal=testClass.distinctExample(entryList).toList();
        assertThrows(UnsupportedOperationException.class, () -> listOfVistorsFinal.add("unwanted visitors"));
        assertTrue(listOfVistorsFinal.size() == listOfVistorsFinal.stream().distinct().count(), "listOfVisitorsFinal dont allows duplicates to be added");
    }

    @Test
    void findAnyOrFirstElementExample() {
        List<String> entryList =List.of("Alex","Bob","Ram","Mina","Ritu","taxi");
        testClass.findAnyOrFirstElementExample(entryList);
    }

    @Test
    void minMaxExample() {
        List<String> listOfModernIndianMathematicians  =List.of("Bhargav Bhatt","Anand Kumar","Nayandeep Deka Baruah","Neena Gupta","Sucharit Sarkar","Akshay Venkatesh");
        testClass.minMaxExample(listOfModernIndianMathematicians);
    }
}