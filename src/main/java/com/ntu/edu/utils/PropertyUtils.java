package com.ntu.edu.utils;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyUtils {

    public static String getProjectConfig(String key) {
        Properties pros = new Properties();
        String value = "";
        try {
            pros.load(new InputStreamReader(PropertiesUtil.class.getResourceAsStream("/application.yml"), "UTF-8"));
            value = pros.getProperty(key);
        } catch (Exception e) {
            return e.getMessage();
        }
        return value;
    }

}
