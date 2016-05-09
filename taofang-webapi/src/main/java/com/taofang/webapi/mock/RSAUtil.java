package com.taofang.webapi.mock;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;

public class RSAUtil {

    /**
     * 加密
     */
    public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
            // 加密块大小为127
            // byte,加密后为128个byte;因此共有2个加密块，第一个127
            // byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
                // ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
                // OutputSize所以只好用dofinal方法。

                i++;
            }
            return raw;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 解密
     */
    public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(cipher.DECRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;

            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     *
     * 根据本地的RSAKey文件获取KeyPair
     *
     * @throws Exception
     */
    public static KeyPair getKeyPair(String rsaKeyStore) throws Exception {
        FileInputStream fis = new FileInputStream(rsaKeyStore);
        ObjectInputStream oos = new ObjectInputStream(fis);
        KeyPair kp = (KeyPair) oos.readObject();
        oos.close();
        fis.close();
        return kp;
    }

    /**
     *
     * 存储KeyPair到本地
     *
     * @throws Exception
     */
    public static void saveKeyPair(KeyPair kp, String path) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // 生成密钥
        oos.writeObject(kp);
        oos.close();
        fos.close();
    }

    /**
     *
     * 用于生成公匙或私匙
     *
     * @throws NoSuchAlgorithmException
     *
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {

        SecureRandom sr = new SecureRandom();
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        // 注意密钥大小最好为1024,否则解密会有乱码情况.
        kg.initialize(1024, sr);
        KeyPair genKeyPair = kg.genKeyPair();
        return genKeyPair;

    }

    /**
     *
     * 测试
     *
     */
    public static void main(String[] args) throws Exception {

        // 获取公匙及私匙
        KeyPair generateKeyPair = getKeyPair("E:\\code\\key");
//        //生成公钥及私钥
//        //KeyPair generateKeyPair = generateKeyPair();
//
//        // 公匙 用于前台加密
        PublicKey publicKey = generateKeyPair.getPublic();
        System.out.println(publicKey);
//
//        // 私匙 存储在后台用于解密
//        PrivateKey privateKey = generateKeyPair.getPrivate();
//        System.out.println(privateKey);

        // 存储KeyPair到本地用于后期解密 注意修改前台RSAKeyPair
        //saveKeyPair(generateKeyPair,"E:\\code\\key");

        // 测试加密解密
        String test = "saaaa";
        // test = "阿斯顿发送对发生地发送盗伐水电费圣达菲sadfsadf爱上对方爱上对方";


        byte[] en_test = encrypt(publicKey, test.getBytes());
        System.out.println("加密后字符:" + new String(en_test));

//        byte[] de_test = decrypt(privateKey, en_test);
//        System.out.println("解密后字符:" + new String(de_test));

    }

}