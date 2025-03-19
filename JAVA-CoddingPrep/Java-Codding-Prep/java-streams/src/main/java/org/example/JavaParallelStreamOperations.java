package org.example;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaParallelStreamOperations {

    protected void parallelStreamDemo(String threadCount)
    {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", threadCount);

        List<Integer> numbers = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .collect(Collectors.toList());

        // Sequential sum
        long start = System.currentTimeMillis();
        long sequentialSum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sequential sum: " + (System.currentTimeMillis() - start) + "ms"+" Thread Count :"+threadCount);

        // Parallel sum
        start = System.currentTimeMillis();
        long parallelSum = numbers.parallelStream()
                .reduce(0, Integer::sum);
        System.out.println("Parallel sum: " + (System.currentTimeMillis() - start) + "ms"+" Thread Count :"+threadCount);
    }
}
