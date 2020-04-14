package com.ntu.edu.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello 被调用";
    }

}
