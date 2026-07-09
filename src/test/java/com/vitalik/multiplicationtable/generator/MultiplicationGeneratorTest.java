package com.vitalik.multiplicationtable.generator;

import com.vitalik.multiplicationtable.config.AppConfig;
import com.vitalik.multiplicationtable.model.MultiplicationExpression;
import com.vitalik.multiplicationtable.strategy.DoubleNumberStrategy;
import com.vitalik.multiplicationtable.strategy.IntNumberStrategy;
import com.vitalik.multiplicationtable.strategy.LongNumberStrategy;
import com.vitalik.multiplicationtable.strategy.NumberStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MultiplicationGeneratorTest {
    private final MultiplicationGenerator generator = new MultiplicationGenerator();

    @Test
    void shouldGenerateExpressions() {
        AppConfig config = createConfig("1", "3", "1");
        NumberStrategy<Integer> strategy = new IntNumberStrategy();

        List<MultiplicationExpression> expressions = generator.generate(config, strategy);

        assertEquals(9, expressions.size());
        assertEquals("1 * 1 = 1", expressions.get(0).toString());
        assertEquals("3 * 3 = 9", expressions.get(8).toString());
    }

    @Test
    void shouldThrowExceptionWhenIncrementIsZero() {
        AppConfig config = createConfig("1", "3", "0");
        NumberStrategy<Integer> strategy = new IntNumberStrategy();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> generator.generate(config, strategy)
        );

        assertEquals("Property 'increment' must be greater than zero", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMinIsGreaterThanMax() {
        AppConfig config = createConfig("10", "1", "1");
        NumberStrategy<Integer> strategy = new IntNumberStrategy();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> generator.generate(config, strategy)
        );

        assertEquals(
                "Property 'min' must not be greater than property 'max'",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenIntValueIsInvalid() {
        AppConfig config = createConfig("abc", "10", "1");
        NumberStrategy<Integer> strategy = new IntNumberStrategy();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> generator.generate(config, strategy)
        );

        assertEquals(
                "Property 'min' has invalid value: 'abc'",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenLongValueIsInvalid() {
        AppConfig config = createConfig("1.5", "10", "1");
        NumberStrategy<Long> strategy = new LongNumberStrategy();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> generator.generate(config, strategy)
        );

        assertEquals(
                "Property 'min' has invalid value: '1.5'",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenDoubleValueIsInvalid() {
        AppConfig config = createConfig("abc", "10", "1");
        NumberStrategy<Double> strategy = new DoubleNumberStrategy();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> generator.generate(config, strategy)
        );

        assertEquals(
                "Property 'min' has invalid value: 'abc'",
                exception.getMessage()
        );
    }

    @Test
    void shouldGenerateExpressionsForInt() {
        AppConfig config = createConfig("1", "3", "1");
        NumberStrategy<Integer> strategy = new IntNumberStrategy();

        List<MultiplicationExpression> expressions = generator.generate(config, strategy);

        assertEquals(9, expressions.size());
        assertEquals("1 * 1 = 1", expressions.get(0).toString());
        assertEquals("2 * 3 = 6", expressions.get(5).toString());
        assertEquals("3 * 3 = 9", expressions.get(8).toString());
    }

    @Test
    void shouldGenerateExpressionsForDouble() {
        AppConfig config = createConfig("1.5", "2.5", "1.0");
        NumberStrategy<Double> strategy = new DoubleNumberStrategy();

        List<MultiplicationExpression> expressions = generator.generate(config, strategy);

        assertEquals(4, expressions.size());
        assertEquals("1.5 * 1.5 = 2.25", expressions.get(0).toString());
        assertEquals("2.5 * 2.5 = 6.25", expressions.get(3).toString());
    }

    @Test
    void shouldGenerateExpressionsForLong() {
        AppConfig config = createConfig("2", "4", "2");
        NumberStrategy<Long> strategy = new LongNumberStrategy();

        List<MultiplicationExpression> expressions = generator.generate(config, strategy);

        assertEquals(4, expressions.size());
        assertEquals("2 * 2 = 4", expressions.get(0).toString());
        assertEquals("4 * 4 = 16", expressions.get(3).toString());
    }

    private AppConfig createConfig(String min, String max, String increment) {
        Properties properties = new Properties();
        properties.setProperty("min", min);
        properties.setProperty("max", max);
        properties.setProperty("increment", increment);

        return new AppConfig(properties);
    }
}
