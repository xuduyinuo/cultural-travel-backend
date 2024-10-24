package com.xudu.culturaltravelbackend.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    static String KEY = "zxcvbnmasdfghjkl";

    /**
     * @MonthName： encrypt
     * @Description： 加密
     * @Author： xudu
     * @Date： 2024/4/18 12:00
     * @Param： [content, key: 16位]
     * @return： java.lang.String
     **/
    public static String encrypt(String content) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        return Hex.encodeHexString(b);
    }

    /**
     * @MonthName： decrypt
     * @Description： 解密
     * @Author： xudu
     * @Date： 2024/4/18 12:00
     * @Param： [encryptStr, key:16位]
     * @return： java.lang.String
     **/
    public static String decrypt(String encryptStr) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
        byte[] encrypttBytes = Hex.decodeHex(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encrypttBytes);
        return new String(decryptBytes);
    }

}