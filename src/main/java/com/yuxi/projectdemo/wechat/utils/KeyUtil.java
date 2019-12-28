package com.yuxi.projectdemo.wechat.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * generate unique key
     * @return format: time + random number
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        //generate 6-digit random number
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
