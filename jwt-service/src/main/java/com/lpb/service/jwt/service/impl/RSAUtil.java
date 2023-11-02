package com.lpb.service.viettel.service.impl;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {

    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3oVNGoF3MwVdEELdVpMNZTJoCccZrNOJ5MqsUwiyIaTHWDpVQhImCpDRXMGv1b9FQF0Bvg3WCNIwrKtzFdVZFGsDybpZuybBA8vJQcMLzpAewa9NahuQCHZRMX/npLu8GgqcDc2V7aiBrX1kzw620XTyUYxj7dXs3h6eAk0P1cQIDAQAB";
    private static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALehU0agXczBV0QQt1Wkw1lMmgJxxms04nkyqxTCLIhpMdYOlVCEiYKkNFcwa/Vv0VAXQG+DdYI0jCsq3MV1VkUawPJulm7JsEDy8lBwwvOkB7Br01qG5AIdlExf+eku7waCpwNzZXtqIGtfWTPDrbRdPJRjGPt1ezeHp4CTQ/VxAgMBAAECgYA/7zlxY7CE8+QQXMmYVg917gfJRhfRh846aHvMdHbQ399sKhOuvxapl8ZpfQB5qf70pcPXj6vAM8+B0CCh12K7gQ6wZbCNfxA4IilE7JHY+2LgASc3lWt7LY99m1e11El4I1OY0rY18az2mtxUB/54nvQdi1YMddv39q+z8OLkAQJBAO7fn0WNP8hlD0PScuI7CG0sYrfWTrNhmulMBaoL5fEJPz83gx6pfpcWkboC3PSDm/lftLH+zQlT4+kX+iPnW6ECQQDEy77FTxDloV3cbzIPykB3aeJVu7366/gfUT0Ng7sraoqxdEGt8BrNql8ckIjMZhx1OMxmojpQ3RoxLVPaKMfRAkEApUEpc7mLRby8ecQu3FnQs46AYQQvACRnQjzoskJ2+nDWQ4rI+D50KFxhxpjSeYpPLo9Kd9V5zZku1ARVdd9J4QJAI1oqig1bDrU/REMhbh66F/mIdDhGt5W+O/n/Crd4XyNDiP9GcTWpyvppHZuFR5qsUA6FAYbxDOe7NcxbvNwIkQJARfZiwCZbv10khyqcyN8wgtv3JNyEMp2GWGzwSCDuA1w0IXiEnbbVj1mNvv9bVDc248wusWdL5DmjsltqohI9Vw==";

    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            assert keyFactory != null;
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        try {
            String encryptedString = Base64.getEncoder().encodeToString(encrypt("changemeplease123a@", publicKey));
            System.out.println(encryptedString);
            String decryptedString = RSAUtil.decrypt(encryptedString, privateKey);
            System.out.println(decryptedString);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

    }
}
