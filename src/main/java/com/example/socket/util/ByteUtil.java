package com.example.socket.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ByteUtil {
    public static String byte2Hex(byte[] bytes,int length){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<length;i++){
            temp = String.format("%02X", bytes [i]);
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
    public static String bytesToHex(byte[] bytes,int length) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }
    public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=hexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }

    public static  byte[] string2Bytes(String str) {

        if (str==null || str.equals("")) {
            return null;
        }

        str = str.toUpperCase();
        int length = str.length() / 2;
        char[] strChar = str.toCharArray();
        byte[] bt = new byte[length];

        for (int i = 0; i < length; i++) {
            int index = i * 2;
            bt[i] = (byte) (char2Byte(strChar[index]) << 4 | char2Byte(strChar[index + 1]));
        }

        return bt;
    }

    private static byte char2Byte(char ch) {
        return (byte) "0123456789ABCDEF".indexOf(ch);
    }

    public final static long getLong(byte[] bt, boolean isAsc) {
        //BIG_ENDIAN
        if (bt == null) {
            throw new IllegalArgumentException("byte array is null.");
        }
        if (bt.length > 8) {
            throw new IllegalArgumentException("byte array size more than 8.");
        }
        long result = 0;
        if (isAsc)
            for (int i = bt.length - 1; i >= 0; i--) {
                result <<= 8;
                result |= (bt[i] & 0x00000000000000ff);
            }
        else
            for (int i = 0; i < bt.length; i++) {
                result <<= 8;
                result |= (bt[i] & 0x00000000000000ff);
            }
        return result;
    }

    /**
     * 将字符串翻转并且加小数点
     * @param num
     * @param ve
     * @return
     */
    public final static String addFloat(String num,int ve) {
        byte[] bytes = string2Bytes(num);
        String b = Long.toHexString(getLong(bytes,true));//翻转
        int len = b.length();
        StringBuilder sb = new StringBuilder(b);
        sb.insert(len-ve,".");
        return sb.toString();
    }

    /**
     * 翻转集合添加小数点
     * @param num
     * @param ve
     * @return
     */
    public final static String listAddFloat(List num,int ve) {
        Collections.reverse(num);
        String b = listToString(num);
        int len = b.length();
        StringBuilder sb = new StringBuilder(b);
        sb.insert(len-ve,".");
        return sb.toString();
    }

    /**
     * 集合转字符串
     * @param mList
     * @return
     */
    public static String listToString(List<String> mList) {
        String convertedListStr = "";
        if (null != mList && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                convertedListStr += mList.get(i);
            }
            return convertedListStr;
        } else {return "List is null!!!";}
    }

    /**
     * 获取16进制随机数
     * @param len
     * @return
     */
    public static String randomHexString(int len)  {
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<len;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return null;

    }
}
