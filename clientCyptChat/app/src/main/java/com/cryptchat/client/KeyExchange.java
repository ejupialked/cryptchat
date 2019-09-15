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

public class KeyExchange extends AsyncTask<byte[], Integer, String> {


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
        sender.openDialogPublicKey();
    }

    @Override
    protected String doInBackground(byte[]... bytes) {
        PublicKey publicKey;

        try {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(1);

            publicKey = receivePublicKeyFromServer(bytes[0]);

            publishProgress(2);
           sender.sendPublicKey(getPublicKey());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if(values[0] == 1) sender.closeDialogPublicKey();
        if(values[0] == 2) sender.openDialogKeyPair();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        sender.closeDialogKeyPair();
        sender.showPrivateKey(s);
    }

    public interface SendKey {

        void openDialogPublicKey();
        void closeDialogPublicKey();

        void openDialogKeyPair();
        void closeDialogKeyPair();

        void sendPublicKey(PublicKey publicKey);
        void sendError(String error);
        void showPrivateKey(String s);
    }

}
