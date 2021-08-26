package demo.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduceDemoApp {

    public static void main(String[] args) {
        List<String> listOfWords= Arrays.asList("its","a","beautiful","day");
        List<Integer> listOfIntegers=Arrays.asList(1,12,13,34,234,23,24);

        Optional<String> longestWord=listOfWords.stream().
                reduce((currentWord,nextWord) -> currentWord.length() > nextWord.length()? currentWord : nextWord);

        Optional<Integer> sumOfNumbers = listOfIntegers.stream()
                .reduce((number1, number2) -> number1 + number2);
        System.out.println("longestWord "+longestWord.get());
        System.out.println("Sum of all numbers in the list "+listOfIntegers+" is "+sumOfNumbers.get());
    }
}
