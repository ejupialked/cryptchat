package com.cryptChat.clientCryptChat;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Alki on 11/06/2017.
 */

public class AES {

    String AES ="AES";

    public AES(){}

    public String decrypt(String encryptedMessage, String key) throws Exception
    {
        byte[] byteKey = key.getBytes();

        Key secretKey = new SecretKeySpec(byteKey, AES);

        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decodedValue = com.example.alki.clientcrip.Base64.decode(encryptedMessage);
        byte[] decValue =c.doFinal(decodedValue);

        String decryptedValue =new String (decValue);
        return decryptedValue;

    }

    public String encrypt(String message, String key) throws Exception {
        byte[] byteMessage = message.getBytes();
        byte[] byteKey = key.getBytes();


        Key secretKey = new SecretKeySpec(byteKey, "AES");


        Cipher c = Cipher.getInstance(AES);

        c.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] cipher = c.doFinal(byteMessage);

        String encryptedValue = com.example.alki.clientcrip.Base64.encode(cipher);
        return encryptedValue;

    }


}
