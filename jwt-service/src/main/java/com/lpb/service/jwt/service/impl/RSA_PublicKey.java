package com.lpb.service.viettel.service.impl;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;


public class RSA_PublicKey {
    public static final String NEW_LINE_CHARACTER = "\n";
    public static final String PUBLIC_KEY_START_KEY_STRING = "-----BEGIN PUBLIC KEY-----";
    public static final String PUBLIC_KEY_END_KEY_STRING = "-----END PUBLIC KEY-----";
    public static final String EMPTY_STRING = "";
    public static final String NEW_CR_CHARACTER = "\r";
    private static final String ALGORITHM = "RSA";
    public static String secretMessage  = "Some random words in no particular order.";

    public static void main(String[] args) {
        String pub_Key="-----BEGIN PUBLIC KEY-----\r\n" +
            "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA993qkLy6s+EL5SQ7vqmZ\r\n" +
            "LBZEV0/tc7T5x8yRJwN0WmWy2TxOnxjFuLvO2b9ai3aEe7X5a6jzl5aE99RXbo86\r\n" +
            "IB2PDH1dDN+W2IKfQViRyZE8hKRZnLH+QEbbRr3a\r\n" +
            "x/EVAnC/cObCKgjNY4jp30sCAwEAAQxxxxxxxx==\r\n" +
            "-----END PUBLIC KEY-----";
        pub_Key=pub_Key.replaceAll(NEW_LINE_CHARACTER, EMPTY_STRING)
            .replaceAll(PUBLIC_KEY_START_KEY_STRING, EMPTY_STRING)
            .replaceAll(PUBLIC_KEY_END_KEY_STRING, EMPTY_STRING)
            .replaceAll(NEW_CR_CHARACTER, EMPTY_STRING);

        byte[] publicKey = Base64.getDecoder().decode(pub_Key.getBytes());
        try {
            byte[] encryptedData = encrypt(publicKey,
                secretMessage.getBytes());

            String encryptedString = Base64.getEncoder().encodeToString(encryptedData);

            System.out.println("Output encryptedString: " + encryptedString);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        
    }

    public static byte[] encrypt(byte[] publicKey, byte[] inputData)
        throws Exception {

        PublicKey key = KeyFactory.getInstance(ALGORITHM)
            .generatePublic(new X509EncodedKeySpec(publicKey));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes = cipher.doFinal(inputData);

        return encryptedBytes;
    }
}
