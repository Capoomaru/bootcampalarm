package com.btca.bootcampalarm.util;

import java.util.Random;

public class RandomCodeUtil {

    public static int createCode() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        return random.nextInt(899999) + 100000;
    }
}
