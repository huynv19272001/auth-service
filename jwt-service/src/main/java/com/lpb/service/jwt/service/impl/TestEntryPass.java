package com.lpb.service.viettel.service.impl;


import com.lpb.service.viettel.model.request.DataRequest;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TestEntryPass {
    public static void main(String[] args) throws Exception {
//        String[] arr = { "1", "2", "1", "3","4","3","2","1","2","3","4","4","3","2" };
//        Map<String, String> map = new HashMap<>();
//        map.put("b1", "a1");
//        map.put("b2", "a2");
//        map.put("b3", "a1");
//        Map<String, List<String>> result = map.entrySet()
//            .stream()
//            .collect(Collectors.groupingBy(Map.Entry::getValue,
//                Collectors.mapping(Map.Entry::getKey,
//                    Collectors.toList())));
//        List<String> list = Arrays.asList(arr);
//        Date date = new Date();
//        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String dateToString = df.format(date);
        String password = "changemeplease123a@";
        String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW0VomTHsZ4VoNCWI4L74ief91 bNKeBtbngsAO33DKnM6YY645KhJsw4rYaNllGTpO9iF7vqPVxcQ4dokXvlylo+ni \n" +
            "E7oUVxPJ1htQs+pt5fcDFZl0QMR3oVUAETmJcBJ368O1hKMSsssf2klBMJJpg8fg \n" +
            "49IofEHjm5qkGPqkCQIDAQAB \n";
        String publicKeyTemp = key.replaceAll("\\s+", "");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyTemp.getBytes("UTF8"));
            X509EncodedKeySpec  publicSpec = new X509EncodedKeySpec(keyBytes);
            PublicKey publicKey =  keyFactory.generatePublic(publicSpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] bytes = cipher.doFinal(password.getBytes("UTF8"));
            String s = Base64.getEncoder().encodeToString(bytes);
            System.out.println("dfđ-------->" + s);
        } catch (InvalidKeySpecException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static PublicKey getPublicKey(String base64PublicKey) {
        PublicKey publicKey = null;
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }
}
