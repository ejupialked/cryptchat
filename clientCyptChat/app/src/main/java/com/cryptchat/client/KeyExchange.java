package com.cryptchat.client;

import android.os.AsyncTask;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyExchange extends AsyncTask<byte[], byte[], String> {


    private PublicKey publicKey;
    private PrivateKey privateKey;

    private KeyFactory keyFactory;
    private KeyPair keyPair;
    private KeyAgreement keyAgreement;
    private KeyPairGenerator keyPairGenerator;
    private X509EncodedKeySpec x509EncodedKeySpec;

    private byte[] commonSecret;
    private SendKey sender;

    public KeyExchange(SendKey send) {
        this.sender = send;
    }


    //1 after receiving pk
    public PublicKey receivePublicKeyFromServer(byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        keyFactory = KeyFactory.getInstance("DH");
        x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);

        System.err.println("generating keys..");
        PublicKey serverPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        try {
            generateDHKeyPair(serverPublicKey);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return serverPublicKey;

    }


    //2
    public DHParameterSpec retrieveDHParamFromPB(PublicKey key){
        return ((DHPublicKey) key).getParams();
    }


    //3
    public void generateDHKeyPair(PublicKey serverPublicKey) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        DHParameterSpec DHParam = retrieveDHParamFromPB(serverPublicKey);

        keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(DHParam);
        keyPair = keyPairGenerator.generateKeyPair();


        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();


        System.err.println("Private: "+CryptChatUtils.encodeBase64(privateKey.getEncoded()));
        System.err.println("Public: "+CryptChatUtils.encodeBase64(publicKey.getEncoded()));

        try {
            initDHKeyAgreement();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }


    //4
    public void initDHKeyAgreement() throws NoSuchAlgorithmException, InvalidKeyException {

        this.privateKey = keyPair.getPrivate();

        keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(privateKey);
    }


    public void doPhase(PublicKey publicKey) throws InvalidKeyException {
        keyAgreement.doPhase(publicKey, true);

        System.err.println("client secret: " + Base64.encode(keyAgreement.generateSecret()));

    }


    public PublicKey getPublicKey() {
        return publicKey;
    }



    public SecretKeySpec getAESKey() {
        return CryptChatUtils.generateAESKey(commonSecret);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        sender.notifyCretingKeyPair();
    }

    @Override
    protected String doInBackground(byte[]... bytes) {
        PublicKey publicKey;

        try {
           publicKey = receivePublicKeyFromServer(bytes[0]);
           sender.sendPublicKey(getPublicKey());
           doPhase(publicKey);
           this.commonSecret = keyAgreement.generateSecret();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return Base64.encode(CryptChatUtils.generateAESKey(commonSecret).getEncoded());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        sender.showPrivateKey(s);
    }

    public interface SendKey {
        void sendPublicKey(PublicKey publicKey);
        void sendError(String error);
        void showPrivateKey(String s);
        void notifyCretingKeyPair();
    }

}
