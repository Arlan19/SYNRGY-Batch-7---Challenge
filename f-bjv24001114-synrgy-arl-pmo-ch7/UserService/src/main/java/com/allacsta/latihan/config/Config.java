package com.allacsta.latihan.config;

import org.springframework.stereotype.Component;

@Component
public class Config {

    public static String APP_NAME = "name";

    public static String yourName() {
        return "Arlan";
    }
}
