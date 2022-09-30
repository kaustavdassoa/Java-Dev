package com.example.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoinningArraysExample {

    /*
    Join two arrays into a single array
     */

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> list2 = Arrays.asList(8,9,10,11,12,13);

        List<Integer> final_list= Stream.concat(list1.stream(),list2.stream())
                .collect(Collectors.toList());
        System.out.println("List 1 :"+list1.toString());
        System.out.println("List 2 :"+list2.toString());
        System.out.println("Final List  :"+final_list.toString());


        /**
         * Joining two Arrays and sorting the results in Descending order.
         */
        final_list= Stream.concat(list1.stream(),list2.stream())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Final reverse order  :"+final_list.toString());

    }
}
