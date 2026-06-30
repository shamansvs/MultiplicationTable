package com.vitalik.multiplicationtable.strategy;

public final class NumberStrategyFactory {
    private NumberStrategyFactory() {
    }

    public static NumberStrategy<?> create(String type) {
        return switch (type) {
            case "int" -> new IntNumberStrategy();
            default -> throw new IllegalArgumentException("Unsupported number type: " + type);
        };
    }
}
