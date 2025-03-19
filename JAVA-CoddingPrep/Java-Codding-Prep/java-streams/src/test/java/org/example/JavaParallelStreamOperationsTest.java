package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaParallelStreamOperationsTest {

    JavaParallelStreamOperations testClass;

    @BeforeEach
    void init()
    {
        testClass = new JavaParallelStreamOperations();
    }

    @Test
    void parallelStreamDemo() {
        testClass.parallelStreamDemo("4");
        testClass.parallelStreamDemo("40");
        testClass.parallelStreamDemo("60");
        testClass.parallelStreamDemo("70");
        testClass.parallelStreamDemo("100");
        testClass.parallelStreamDemo("200");
        testClass.parallelStreamDemo("300");
        testClass.parallelStreamDemo("400");
        testClass.parallelStreamDemo("500");
        testClass.parallelStreamDemo("600");
        testClass.parallelStreamDemo("700");
    }
}