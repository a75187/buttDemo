package org.example.services.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author chenshuai
 * @Description TODO
 * @createTime 2021年07月22日 14:07:00
 */
public class MD5Utils {
    private static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public MD5Utils() {
    }

    public static String md5(String text) {
        return md5(new String[]{text});
    }

    public static String md5(String[] text) {
        byte[] bytes = digest(text);
        return new String(encodeHex(bytes));
    }

    public static long halfDigest(String... text) {
        long ret = 0L;
        byte[] bytes = digest(text);

        for (int i = 0; i < 8; ++i) {
            ret = ret << 8 | (long) bytes[i] & 255L;
        }

        return ret;
    }

    public static byte[] digest(String... text) {
        MessageDigest msgDigest = null;

        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var6) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }

        try {
            String[] var2 = text;
            int var3 = text.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String str = var2[var4];
                msgDigest.update(str.getBytes("utf-8"));
            }
        } catch (UnsupportedEncodingException var7) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }

        return msgDigest.digest();
    }

    public static String md5_16(String text) {
        return md5(text).substring(8, 24);
    }

    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;

        for (int var4 = 0; i < l; ++i) {
            out[var4++] = DIGITS[(240 & data[i]) >>> 4];
            out[var4++] = DIGITS[15 & data[i]];
        }

        return out;
    }
}