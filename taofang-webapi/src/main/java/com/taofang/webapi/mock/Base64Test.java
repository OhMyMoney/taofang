package com.taofang.webapi.mock;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Base64Test {
    private static BASE64Encoder encoder = new BASE64Encoder();
    private static BASE64Decoder decoder = new BASE64Decoder();

    public static String base64encode(String s){
        try{
            String encodeStr = encoder.encode(s.getBytes());

            return encodeStr;
        }catch(Exception e){
            return s;
        }
    }

    public static String base64decode(String s){
        try{
            String decodeStr = new String(decoder.decodeBuffer(s));

            return decodeStr;
        }catch(Exception e){
            return s;
        }
    }

    public static byte[] md5encode(byte[] input){
        byte[] digestedValue = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input);
            digestedValue = md.digest();
        }catch(Exception e){
            e.printStackTrace();
        }
        return digestedValue;
    }

    public static String getMD5(String md5Input) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(md5Input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String base64(String str){
        return new BASE64Encoder().encodeBuffer(str.getBytes());
    }

    public static void main(String args[]) throws IOException {
        System.out.println(getMD5("123456"));

        System.out.println(base64(getMD5("123456").substring(5,15)));
        //测试BASE64加密
//        System.out.println("------------------------------------");
//        String str = "123456";
//        String ret = null;
//        ret = base64encode(str);
//        System.out.println("加密前:"+str+" \n加密后:"+ret);

//        //测试BASE64解密
//        System.out.println("------------------------------------");
//        String str1 = ret;
//        ret = base64decode(ret);
//        System.out.println("解密前:"+str1+"\n 解密后:"+ret);

        //测试MD5加密，MD5是不可逆置的
//        System.out.println("------------------------------------");
//        String str2 = "123456";
//        byte[] ret1 = null;
//        ret1 = md5encode(str2.getBytes());
//        System.out.println("解密前:"+str2+" \n解密后:"+new String(ret1));

        //将MD5和BASE64结合起来使用
//        System.out.println("------------------------------------");
//        String str3 = "123456";
//        byte[] md5str = md5encode(str3.getBytes());
//        String temp = new String(md5str);
//        String basestr1 = base64encode(temp);
//        System.out.println("MD5加密后:"+temp+"\nBASE64加密后:"+basestr1);
//        String basestr2 = base64decode(basestr1);
//        System.out.println("BASE64加密后:"+basestr1+"\nBASE64解密后:"+basestr2);
//        System.out.println("------------------------------------");
    }
}