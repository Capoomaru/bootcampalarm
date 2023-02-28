package com.btca.bootcampalarm.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCodeUtils {

    public int createCode() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        return random.nextInt(899999) + 100000;
    }
}
