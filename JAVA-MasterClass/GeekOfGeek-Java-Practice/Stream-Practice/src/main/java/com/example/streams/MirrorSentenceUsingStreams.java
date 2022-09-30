package com.example.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MirrorSentenceUsingStreams {




    private static String mirrorSentenceUsingStreams(String sentence)
    {
        return Stream.of(sentence)
                .map(word -> new StringBuffer(word).reverse())
                .collect(Collectors.joining());
    }

    private static String mirrorSentenceUsingRecursion(String sentence)
    {
        String [] wordArray=sentence.split(" ");
        StringBuffer reverseSentence=new StringBuffer();
        for(int i=wordArray.length-1; i>=0 ; i--)
        {
            reverseSentence.append(new StringBuffer(wordArray[i]).reverse().toString()+" ");
        }

        return reverseSentence.toString().trim();
    }


    public static void main(String[] args) {
        String sentence="This is a sample sentence which needs to be reversed";
        System.out.println(sentence);
        System.out.println(mirrorSentenceUsingStreams(sentence));
        System.out.println(mirrorSentenceUsingRecursion(sentence));
    }
}
