package org.example;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaStreamsCollectorOperations {

    protected Set<String> listToSet(List<String> list)
    {
        return list.stream().collect(Collectors.toSet());
    }

    protected List<String> listToList(List<String> list)
    {
        return list.stream().collect(Collectors.toList());
    }

    protected String joinElementOfAList(List<String> list,String delimiter)
    {
        return list.stream().collect(Collectors.joining(delimiter));
    }


    protected Map<Integer,List<String>> groupByStringLength(List<String> list)
    {
        return list.stream().collect(Collectors.groupingBy(String::length));
    }

    protected Map<Integer, List<String>> groupByOccurence(List<String> list)
    {
        Map<String,Long> countReference=list.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        return list.stream().distinct().collect(Collectors.groupingBy(s->countReference.get(s).intValue()));
    }

    protected Map<String,Long> groupByElementOccurences(List<String> list)
    {
       return list.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));

    }

    protected Map<Boolean, List<String>> partitioningBy(List<String> list)
    {
        return list.stream().collect(Collectors.partitioningBy(s -> s.startsWith("a")));
    }

    protected IntSummaryStatistics summarizingInt(List<String> list)
    {
        return list.stream()
                .collect(Collectors.summarizingInt(String::length));
    }
}
