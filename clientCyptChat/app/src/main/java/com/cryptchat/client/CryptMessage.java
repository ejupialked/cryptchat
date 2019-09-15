package com.cryptchat.client;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

public class CryptMessage {

    static String AES = "AES";

    private PublicKey  publicKey;
    private PrivateKey privateKey;

    private KeyFactory         keyFactory;
    private KeyPair            keyPair;
    private KeyAgreement       keyAgreement;
    private KeyPairGenerator   keyPairGenerator;
    private X509EncodedKeySpec x509EncodedKeySpec;
    private DHParameterSpec    dhParamFromServerPubKey;

    private byte[] secretKey;

    //1 after receiving pk
    public void receivePublicKeyFromServer(byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        keyFactory = KeyFactory.getInstance("DH");
        x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);

        this.publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
    }


    //2
    public DHParameterSpec retrieveDHParamFromPB(PublicKey key){
        return ((DHPublicKey) key).getParams();
    }


    //3
    public void generateDHKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        DHParameterSpec DHParam = retrieveDHParamFromPB(publicKey);
        keyPairGenerator = KeyPairGenerator.getInstance(AES);
        keyPairGenerator.initialize(DHParam);
        keyPair = keyPairGenerator.generateKeyPair();
    }


    //4
    public void initDHKeyAgreement() throws NoSuchAlgorithmException, InvalidKeyException {

        this.privateKey = keyPair.getPrivate();

        keyAgreement = KeyAgreement.getInstance(AES);
        keyAgreement.init(privateKey);
    }


    //after sending the pubk back
    public void doPhase() throws InvalidKeyException {
        keyAgreement.doPhase(publicKey, true);

    }

    //send pk to server
    public String encodeBase64(byte[] data) {
        return Base64.encode(data);
    }

    public byte[] decodeBase64(String data) {
        return Base64.decode(data);
    }



    public byte[] generateSecretKey() {
        return keyAgreement.generateSecret();
    }


    public SecretKeySpec generateAESKey(byte[] secretKey){
        return new SecretKeySpec(secretKey, 0, 16, AES);
    }


    public String encrypt(String message, SecretKeySpec key) throws Exception {

        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plainText = message.getBytes();
        byte[] cipherText = cipher.doFinal(plainText);

        return encodeBase64(cipherText);
    }





    public String decrypt(String encryptedMessage) throws Exception {

        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, generateAESKey(secretKey));

        byte[] decodedMessage = Base64.decode(encryptedMessage);
        byte[] recovered = cipher.doFinal(decodedMessage);

        return new String(recovered);
    }
}
