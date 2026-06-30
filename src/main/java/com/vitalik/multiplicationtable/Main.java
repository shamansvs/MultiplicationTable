package com.vitalik.multiplicationtable;

import com.vitalik.multiplicationtable.config.AppConfig;
import com.vitalik.multiplicationtable.config.PropertiesLoader;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = PropertiesLoader.load("application.properties");
        AppConfig config = new AppConfig(properties);
        String type = System.getProperty("number.type", "int").trim().toLowerCase();

    }
}