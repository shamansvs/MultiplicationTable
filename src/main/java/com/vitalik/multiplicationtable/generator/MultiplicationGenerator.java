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
        T min = parseProperty("min", config.getMin(), strategy);
        T max = parseProperty("max", config.getMax(), strategy);
        T increment = parseProperty("increment", config.getIncrement(), strategy);

        validateValues(min, max, increment, strategy);

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

    private <T> T parseProperty(
            String propertyName,
            String value,
            NumberStrategy<T> strategy
    ) {
        try {
            return strategy.parse(value);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(
                    "Property '" + propertyName + "' has invalid value: '" + value + "'",
                    e
            );
        }
    }

    private <T> void validateValues(
            T min,
            T max,
            T increment,
            NumberStrategy<T> strategy
    ) {
        if (strategy.compare(increment, strategy.zero()) <= 0) {
            throw new IllegalArgumentException("Property 'increment' must be greater than zero");
        }

        if (strategy.compare(min, max) > 0) {
            throw new IllegalArgumentException("Property 'min' must not be greater than property 'max'");
        }
    }
}
