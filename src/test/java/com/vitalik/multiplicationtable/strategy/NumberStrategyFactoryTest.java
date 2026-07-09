package com.vitalik.multiplicationtable.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberStrategyFactoryTest {
    @Test
    void shouldCreateIntStrategy() {
        NumberStrategy<?> strategy = NumberStrategyFactory.create("int");

        assertInstanceOf(IntNumberStrategy.class, strategy);
    }

    @Test
    void shouldCreateDoubleStrategy() {
        NumberStrategy<?> strategy = NumberStrategyFactory.create("double");

        assertInstanceOf(DoubleNumberStrategy.class, strategy);
    }

    @Test
    void shouldCreateLongStrategy() {
        NumberStrategy<?> strategy = NumberStrategyFactory.create("long");

        assertInstanceOf(LongNumberStrategy.class, strategy);
    }

    @Test
    void shouldThrowExceptionForUnsupportedType() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> NumberStrategyFactory.create("float")
        );

        assertTrue(exception.getMessage().contains("Unsupported number type"));
    }
}
