package io.github.zxgangandy.utils;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @className: BytesUtils
 * @description: TODO 类描述
 * @author: andy
 * @date: 2023/2/11
 **/
public class BytesUtils {

    private BytesUtils() {
    }

    /**
     * 将 long 值转为8字节的二进制数组；
     *
     * @param value  要转换的long整数；
     * @param bytes  要保存转换结果的二进制数组；转换结果将从高位至低位的顺序写入数组从 offset 指定位置开始的8个元素；
     * @param offset 写入转换结果的起始位置；
     * @return 返回写入的长度；
     */
    public static int toBytes(long value, byte[] bytes, int offset) {
        bytes[offset] = (byte) ((value >>> 56) & 0x00FF);
        bytes[offset + 1] = (byte) ((value >>> 48) & 0x00FF);
        bytes[offset + 2] = (byte) ((value >>> 40) & 0x00FF);
        bytes[offset + 3] = (byte) ((value >>> 32) & 0x00FF);
        bytes[offset + 4] = (byte) ((value >>> 24) & 0x00FF);
        bytes[offset + 5] = (byte) ((value >>> 16) & 0x00FF);
        bytes[offset + 6] = (byte) ((value >>> 8) & 0x00FF);
        bytes[offset + 7] = (byte) (value & 0x00FF);
        return 8;
    }

    public static byte[] toBytes(String str) {
        return toBytes(str, StandardCharsets.UTF_8.name());
    }

    public static byte[] toBytes(String str, String charset) {
        if (null == str) {
            return null;
        }
        try {
            return str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * Convert hex string to byte[]
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    /**
     * utf8 串转字节数组
     *
     * @param str utf8 串
     * @return byte数组
     */
    public static byte[] utf8ToArray(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 字节数组转 utf8 串
     *
     * @param arr byte数组
     * @return utf8 串
     */
    public static String arrayToUtf8(byte[] arr) {
        return new String(arr, StandardCharsets.UTF_8);
    }

}
