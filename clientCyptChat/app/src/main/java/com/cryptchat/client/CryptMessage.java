package com.cryptchat.client;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Random;

public class CryptMessage {

    static String AES = "AES";

    public static String generateKey() {
        String key = "";
        for (int x = 0; x < 16; x++) {
            int c = new Random().nextInt(122 - 48) + 48;
            if ((c >= 50 && c <= 64) | (c >= 91 && c <= 96)) {
                x--;
                continue;
            }

            key += ((char) c);
        }

        return key;
    }

    public static String encrypt(String message, String key) throws Exception {

        if(key.equals("error")) throw new Exception("key not valid");



        byte[] byteMessage = message.getBytes();
        byte[] byteKey = key.getBytes();

        Key secretKey = new SecretKeySpec(byteKey, "AES");

        Cipher c = Cipher.getInstance(AES);

        c.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] cipher = c.doFinal(byteMessage);

        String encryptedValue = Base64.encode(cipher);
        return encryptedValue;

    }


    public static String getKey(String encryptedMessage){
        int fineChiave = encryptedMessage.indexOf("/#&#/");
        int inizioChiave = 0;
        return encryptedMessage.substring(inizioChiave, fineChiave);


    }

    public static String getEncryptedMessage(String encryptedMessage){
        int inizioMessaggio = encryptedMessage.indexOf("/#&#/") + 5;
        int fineMessaggio = encryptedMessage.length();

        return encryptedMessage.substring(inizioMessaggio, fineMessaggio);
    }

    public static String decrypt(String encryptedMessage) throws Exception {

        if(encryptedMessage.equals("error")) throw new Exception("message format not valid");



        String key =  getKey(encryptedMessage);
        String messsage = getEncryptedMessage(encryptedMessage);

        byte[] byteKey = key.getBytes();


        Key secretKey = new SecretKeySpec(byteKey, AES);

        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decodedValue = Base64.decode(messsage);
        byte[] decValue =c.doFinal(decodedValue);

        String decryptedValue = new String (decValue);
        return decryptedValue;

    }


    public static String encryptMessage(String message, String key) throws Exception{
        if(key.length() != 16 && key.length() != 0){
            throw new Exception("The key must be 16 characters.");
        }else if(key.length() == 0){
            throw new Exception("Key not inserted");
        }

        return CryptMessage.encrypt(message, key);
    }

}
