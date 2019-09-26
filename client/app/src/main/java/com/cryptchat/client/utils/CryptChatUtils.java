package com.cryptchat.client.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptChatUtils {

    public static String AES = "AES";

    public static String PROTOCOL_SEPARATOR = "@@";

    public static int COMMAND_INDEX      = 0;
    public static int MESSAGE_INDEX      = 1;
    public static int PUBLIC_KEY         = 3;
    public static int ENCRYPTED_MESSAGE  = 4;
    public static int REQUEST_NEW_KEY    = 5;
    public static int REQUEST_PUBLIC_KEY = 6;
    public static int READY              = 8;


    public static String encodeBase64(byte[] data) {
        return com.cryptchat.client.utils.Base64.encode(data);
    }

    public static byte[] decodeBase64(String data) {
        return Base64.decode(data);
    }

    public synchronized static String decrypt(String encryptedMessage, SecretKeySpec aesKey) throws Exception {

        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE,aesKey);

        System.out.println("im decrypting : " + Base64.encode(aesKey.getEncoded()));


        byte[] decodedMessage = com.cryptchat.client.utils.Base64.decode(encryptedMessage);
        byte[] recovered = cipher.doFinal(decodedMessage);

        return new String(recovered);
    }

    public synchronized static String encrypt(String message, SecretKeySpec aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] plainText = message.getBytes();
        byte[] cipherText = cipher.doFinal(plainText);

        return encodeBase64(cipherText);
    }

    public synchronized static String decrypt(String encryptedMessage, String aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);

        byte[] key = aesKey.getBytes();

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES);

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);



        byte[] decodedMessage = Base64.decode(encryptedMessage);
        byte[] recovered = cipher.doFinal(decodedMessage);

        return new String(recovered);
    }

    public synchronized static String encrypt(String message, String aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        byte[] key = aesKey.getBytes();


        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] plainText = message.getBytes();
        byte[] cipherText = cipher.doFinal(plainText);

        return encodeBase64(cipherText);
    }

    public static SecretKeySpec generateAESKey(byte[] secretKey){
        return new SecretKeySpec(secretKey, 0, 16, AES);
    }

    public static void delay(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static int retrieveCommand(String data){
        System.err.println(data);
        return Integer.parseInt(data.split(PROTOCOL_SEPARATOR)[COMMAND_INDEX]);
    }

    public static String retrieveMessage(String data){
        return data.split(PROTOCOL_SEPARATOR)[MESSAGE_INDEX];

    }

}
