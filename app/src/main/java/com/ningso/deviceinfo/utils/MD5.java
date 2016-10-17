package com.ningso.deviceinfo.utils;

import java.security.MessageDigest;

/**
 * Created by NingSo on 16/3/10.下午10:19
 *
 * @author: NingSo
 * @Email: ningdev@163.com
 */
public class MD5 {

    private static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String hexdigest(String string) {
        String s = null;
        try {
            s = hexdigest(string.getBytes());
        } catch (Exception ignored) {
        }
        return s;
    }

    public static String hexdigest(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] tmp = md.digest();
            char[] str = new char[32];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                int i2 = k + 1;
                str[k] = hexDigits[(byte0 >>> 4) & 15];
                k = i2 + 1;
                str[i2] = hexDigits[byte0 & 15];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
