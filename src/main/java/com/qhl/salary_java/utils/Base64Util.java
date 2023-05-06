package com.qhl.salary_java.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author qhl
 * @date 2023/4/21 16:11
 */

public class Base64Util {
    /***
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryBASE64(String key) throws Exception{
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /***
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception{
        return (new BASE64Encoder()).encode(key);
    }

    public static void main(String[] args) throws Exception {
        String jm = encryptBASE64("salaryadmin".getBytes());
        String je = new String(decryBASE64(jm));
        System.out.println(jm);
        System.out.println(je);
    }
}
