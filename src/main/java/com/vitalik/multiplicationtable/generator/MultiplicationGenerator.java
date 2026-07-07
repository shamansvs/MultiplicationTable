package com.vitalik.multiplicationtable.generator;

import com.vitalik.multiplicationtable.config.AppConfig;
import com.vitalik.multiplicationtable.model.MultiplicationExpression;
import com.vitalik.multiplicationtable.strategy.NumberStrategy;

import java.util.ArrayList;
import java.util.List;

public class MultiplicationGenerator {
    public <T> List<MultiplicationExpression> generate(
            AppConfig config,
            NumberStrategy<T> strategy
    ) {
        T min = strategy.parse(config.getMin());
        T max = strategy.parse(config.getMax());
        T increment = strategy.parse(config.getIncrement());

        List<MultiplicationExpression> expressions = new ArrayList<>();

        for (T left = min;
             strategy.compare(left, max) <= 0;
             left = strategy.add(left, increment)) {

            for (T right = min;
                 strategy.compare(right, max) <= 0;
                 right = strategy.add(right, increment)) {

                T result = strategy.multiply(left, right);

                expressions.add(new MultiplicationExpression(
                        strategy.format(left),
                        strategy.format(right),
                        strategy.format(result)
                ));
            }
        }

        return expressions;
    }
}
