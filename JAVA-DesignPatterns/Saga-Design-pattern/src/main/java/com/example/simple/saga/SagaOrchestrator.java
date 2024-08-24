package com.example.simple.saga;

import com.example.simple.saga.exception.SagaException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SagaOrchestrator<T> {

    private final List<SagaStep<T>> steps;

    public SagaOrchestrator(List<SagaStep<T>> steps) {
        this.steps = steps;
    }

    public void execute(T data) {
        List<SagaStep<T>> executedSteps = new ArrayList<SagaStep<T>>();
        try {
            for (SagaStep<T> step : steps) {
                step.execute(data);
                executedSteps.add(step);
            }
        } catch (SagaException e) {
            compensate(data, executedSteps);

        }


    }

    private void compensate(T data, List<SagaStep<T>> executedSteps) {
        Collections.reverse(executedSteps);
        for (SagaStep<T> step : executedSteps) {
            try {
                step.compensate(data);
            } catch (SagaException e) {
                // Log the error and continue compensating
            }
        }
    }
}
