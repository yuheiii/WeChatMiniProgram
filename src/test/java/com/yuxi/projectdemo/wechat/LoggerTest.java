package com.yuxi.projectdemo.wechat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);


    public void test1() {
        //logger.debug("debug...");
        //logger.info("info...");
        //logger.error("error...");
        //log.debug("debug..");
        log.debug("Something's wrong here");
    }
}
