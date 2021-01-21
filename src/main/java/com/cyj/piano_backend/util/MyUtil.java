package com.cyj.piano_backend.util;

import java.util.UUID;

/**
 * @author changyingjie
 */
public class MyUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
