package com.jeyce.sample.aws.util;

import java.util.UUID;

/**
 *
 */
public class IDGenerator {
    // array de 64+2 digitos
    private final static char[] DIGITS66 = {
            '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'//,'-','.','_','~'
    };


    public static String next() {
        UUID u = UUID.randomUUID();
        return toIDString(u.getMostSignificantBits()) + toIDString(u.getLeastSignificantBits());
    }

    private static String toIDString(long i) {
        char[] buf = new char[28];
        int z = 60; // 1 << 6;
        int cp = 28;
        long b = z - 1;
        do {
            buf[--cp] = DIGITS66[(int)(i & b)];
            i >>>= 6;
        } while (i != 0);
        return new String(buf, cp, (28-cp));
    }
}