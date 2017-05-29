package com.shlagoverflow.core.util;

import java.util.Random;

/**
 * @author Leboc Philippe.
 */
public class Utils {
    public static int getRandomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        final Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
