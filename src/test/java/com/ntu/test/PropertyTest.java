package com.ntu.test;

import com.ntu.edu.utils.PropertyUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyTest {

    @Test
    public void getProperties(){
        Properties pros = new Properties();
        String value = "";
        try {
            pros.load(new InputStreamReader(PropertiesUtil.class.getResourceAsStream("/application.yml"), "UTF-8"));
            value = pros.getProperty("url");
        } catch (Exception e) {
            System.out.println("error");
        }
        System.out.println(value);
    }

}
