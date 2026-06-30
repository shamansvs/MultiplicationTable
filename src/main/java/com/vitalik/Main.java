package com.vitalik;

import com.vitalik.config.AppConfig;
import com.vitalik.config.PropertiesLoader;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = PropertiesLoader.load("application.properties");
        AppConfig config = new AppConfig(properties);
    }
}