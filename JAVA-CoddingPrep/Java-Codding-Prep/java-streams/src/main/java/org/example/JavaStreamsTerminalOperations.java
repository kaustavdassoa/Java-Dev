package org.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaStreamsTerminalOperations {

    protected List<String> gathersElementsIntoACollection(Map<String,Integer> names)
    {
        return names.keySet().stream().collect(Collectors.toList());
    }

    protected Optional<String> reduceFunction(List<String> list)
    {
        return list.stream().reduce((a,b) -> a+", "+b);
    }

    protected Long countElements(List<String> list)
    {
        return list.stream().count();
    }
}
