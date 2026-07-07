package com.vitalik.multiplicationtable;

import com.vitalik.multiplicationtable.config.AppConfig;
import com.vitalik.multiplicationtable.config.PropertiesLoader;
import com.vitalik.multiplicationtable.generator.MultiplicationGenerator;
import com.vitalik.multiplicationtable.model.MultiplicationExpression;
import com.vitalik.multiplicationtable.output.ConsolePrinter;
import com.vitalik.multiplicationtable.strategy.NumberStrategy;
import com.vitalik.multiplicationtable.strategy.NumberStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("Application started");
            Properties properties = PropertiesLoader.load("application.properties");
            AppConfig config = new AppConfig(properties);
            String type = System.getProperty("number.type", "int").trim().toLowerCase();
            NumberStrategy<?> strategy = NumberStrategyFactory.create(type);
            MultiplicationGenerator generator = new MultiplicationGenerator();
            List<MultiplicationExpression> expressions = generator.generate(config, strategy);
            ConsolePrinter.print(expressions);
            LOGGER.info("Application finished successfully");
        } catch (RuntimeException e) {
            LOGGER.error("Application failed: {}", e.getMessage());
            System.exit(1);
        }
    }
}