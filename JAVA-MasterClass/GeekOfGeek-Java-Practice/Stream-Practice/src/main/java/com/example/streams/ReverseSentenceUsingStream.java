package com.example.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseSentenceUsingStream {
    public static String reverseSentenceUsingStream(String sentence)
    {

        // Split the each word of the sentence into List <String> elements
        List<String> wordList= Arrays.stream(sentence.split(" "))
                .collect(Collectors.toList());

        // Reverse the List<String>
        Collections.reverse(wordList);

        // Add space to endOf each element then join each element return string value
        return wordList.stream()
                .map(word -> word+" ")
                .collect(Collectors.joining()).toString();


    }

    private static String reverseSentenceUsingRecursionWithStringBuffer(String sentence) {

        String [] wordList=sentence.split(" ");
        StringBuffer reverseSentence=new StringBuffer();
        for(int i=wordList.length-1 ; i >= 0; i--)
        {
            reverseSentence.append(wordList[i]+" ");
        }

        return reverseSentence.toString();

    }

    private static String reverseSentenceUsingRecursion(String sentence)
    {
        String [] wordList=sentence.split(" ");

        String [] reverseWordList=new String[wordList.length-1];
        int j=0;
        String reverseSentence="";
        for(int i=wordList.length-1 ; i >= 0; i--)
        {
            reverseSentence=reverseSentence+wordList[i]+" ";

        }

        return reverseSentence.toString();
    }

    public static void main(String[] args) {
            String sentence="This is a sample sentence which needs to be reversed";

            System.out.println(sentence+"\n\n");
            System.out.println("reverseSentenceUsingStream                   : "+reverseSentenceUsingStream(sentence));
            System.out.println("reverseSentenceUsingRecursion                : "+reverseSentenceUsingRecursion(sentence));
           System.out.println("reverseSentenceUsingRecursionWithStringBuffer : "+reverseSentenceUsingRecursionWithStringBuffer(sentence));
    }
}
