package org.example;

import java.util.*;
import java.util.stream.Stream;

public class JavaStreamsIntermediateOperations {

    protected Stream<String> flatMapExample(List<String> list1, List<String> list2, List<String> list3)
    {
        // flatMap - flattens nested streams
        List<List<String>> listOfList=List.of(list1,list2,list3);
        return listOfList.stream().flatMap(Collection::stream);
    }

    protected Stream<String> distinctExample(List<String> ramdomList)
    {
        // distinct - removes duplicates
        return ramdomList.stream().distinct();
    }

    protected void findAnyOrFirstElementExample(List<String> ramdomList)
    {

        /*  findFirst/findAny - find an element

            Key characteristics of findAny():
             1. Non-deterministic: The element returned is deliberately unspecified to allow for optimization, especially in parallel streams.
             2.Short-circuiting: It's a short-circuiting terminal operation, meaning it may not process the entire stream before returning a result.
             3.Returns Optional: The result is an Optional<T> which might be empty if the stream contains no elements.

         */
        Optional<String> anyElement=ramdomList.stream().findAny();
        Optional<String> firstElement=ramdomList.stream().findFirst();

        int i=0;
        while (i<=10)
        {
            System.out.println("firstElement :"+firstElement.get());
            System.out.println("anyElement :"+anyElement.get());
            i++;
        }
    }

    protected void minMaxExample(List<String> ramdomList)
    {

        Map<String,Integer> db=Map.of("Bhargav Bhatt",1983,"Anand Kumar",1973,"Nayandeep Deka Baruah",1972,"Neena Gupta",1984,"Sucharit Sarkar",1983);

        Optional<String> longestName= ramdomList.stream().max(Comparator.comparing(String::length));
        Optional<String> shortestName= ramdomList.stream().min(Comparator.comparing(String::length));
        Optional<String> youngest = ramdomList.stream().max(Comparator.comparingInt(name -> db.getOrDefault(name,2025)));
        Optional<String> oldest = ramdomList.stream().min(Comparator.comparingInt(name -> db.getOrDefault(name,2025)));



        System.out.println(db);

        System.out.println("longestName ="+longestName.get().toString());
        System.out.println("shortestName ="+shortestName.get().toString());
        System.out.println("youngest ="+youngest.get().toString());
        System.out.println("oldest ="+oldest.get().toString());
    }
}
