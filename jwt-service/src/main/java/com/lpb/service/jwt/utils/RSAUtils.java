package com.lpb.service.viettel.utils;

import com.lpb.esb.service.common.utils.rsa.RSAUtil;
import com.lpb.esb.service.common.utils.rsa.SHA1Utils;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Base64;
import sun.misc.BASE64Encoder;
@Log4j2
public class RSAUtils {
    private static final Logger logger = LoggerFactory.getLogger(RSAUtils.class);

//    public static String encryptSignature(String data) throws Exception {
//        byte[] messageBytes = data.getBytes(Constants.UTF8);
//        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Constants.RSA);
//        keyGen.initialize(1024);
//        KeyPair key = keyGen.genKeyPair();
//        Signature sig = Signature.getInstance(Constants.SHA1WithRSA);
//        sig.initSign(key.getPrivate());
//        sig.update(messageBytes);
//        byte[] signature = sig.sign();
//        return new BASE64Encoder().encode(signature);
//    }
    public static String generateSignature(String dataInput) throws NoSuchAlgorithmException,
        InvalidKeySpecException, InvalidKeyException, SignatureException, IOException {
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALehU0agXczBV0QQt1Wk w1lMmgJxxms04nkyqxTCLIhpMdYOlVCEiYKkNFcwa/Vv0VAXQG+DdYI0jCsq3MV1VkUa wPJulm7JsEDy8lBwwvOkB7Br01qG5AIdlExf+eku7waCpwNzZXtqIGtfWTPDrbRdPJRj\n" +
            "GPt1ezeHp4CTQ/VxAgMBAAECgYA/7zlxY7CE8+QQXMmYVg917gfJRhfRh846aHvMdHbQ\n" +
            "399sKhOuvxapl8ZpfQB5qf70pcPXj6vAM8+B0CCh12K7gQ6wZbCNfxA4IilE7JHY+2Lg ASc3lWt7LY99m1e11El4I1OY0rY18az2mtxUB/54nvQdi1YMddv39q+z8OLkAQJBAO7f n0WNP8hlD0PScuI7CG0sYrfWTrNhmulMBaoL5fEJPz83gx6pfpcWkboC3PSDm/lftLH+ zQlT4+kX+iPnW6ECQQDEy77FTxDloV3cbzIPykB3aeJVu7366/gfUT0Ng7sraoqxdEGt\n" +
            "8BrNql8ckIjMZhx1OMxmojpQ3RoxLVPaKMfRAkEApUEpc7mLRby8ecQu3FnQs46AYQQv\n" +
            "ACRnQjzoskJ2+nDWQ4rI+D50KFxhxpjSeYpPLo9Kd9V5zZku1ARVdd9J4QJAI1oqig1b\n" +
            "DrU/REMhbh66F/mIdDhGt5W+O/n/Crd4XyNDiP9GcTWpyvppHZuFR5qsUA6FAYbxDOe7 NcxbvNwIkQJARfZiwCZbv10khyqcyN8wgtv3JNyEMp2GWGzwSCDuA1w0IXiEnbbVj1mN vv9bVDc248wusWdL5DmjsltqohI9Vw==\n";
        StringBuilder pkcs8Lines = new StringBuilder();
        BufferedReader rdr = new BufferedReader(new StringReader(privateKey));
        String line;
        while ((line = rdr.readLine()) != null) {
            pkcs8Lines.append(line);
        }
        String pkcs8Pem = pkcs8Lines.toString();
        pkcs8Pem = pkcs8Pem.replaceAll("\\s+","");
        logger.info("Private Key:---->{} ",pkcs8Pem);
        byte [] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcs8Pem);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
        KeyFactory kf = KeyFactory.getInstance(Constants.RSA);
        PrivateKey privKey = kf.generatePrivate(keySpec);
        Signature md5withrsa = Signature.getInstance(Constants.SHA1WithRSA);
        md5withrsa.initSign(privKey);

        md5withrsa.update(dataInput.getBytes());
        byte[] res = md5withrsa.sign();
        return Base64.getEncoder().encodeToString(res);
    }

    public static byte[] encrypt(byte[] publicKey, byte[] inputData)
        throws Exception {

        PublicKey key = KeyFactory.getInstance(Constants.RSA)
            .generatePublic(new X509EncodedKeySpec(publicKey));

        Cipher cipher = Cipher.getInstance(Constants.RSA);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(inputData);
    }
    public static String encryptionPass(){
        try {
            String password = "changemeplease123a@";
            String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW0VomTHsZ4VoNCWI4L74ief91 bNKeBtbngsAO33DKnM6YY645KhJsw4rYaNllGTpO9iF7vqPVxcQ4dokXvlylo+ni \n" +
                "E7oUVxPJ1htQs+pt5fcDFZl0QMR3oVUAETmJcBJ368O1hKMSsssf2klBMJJpg8fg \n" +
                "49IofEHjm5qkGPqkCQIDAQAB \n";
            logger.info("public key entry password : {}",key);
            String publicKeyTemp = key.replaceAll("\\s+","");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyTemp.getBytes("UTF-8"));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            PublicKey fileGeneratedPublicKey = keyFactory.generatePublic(spec);
            RSAPublicKey rsaPub  = (RSAPublicKey)(fileGeneratedPublicKey);
            BigInteger publicKeyModulus = rsaPub.getModulus();
            BigInteger publicKeyExponent  = rsaPub.getPublicExponent();
            RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(publicKeyModulus, publicKeyExponent);
            keyFactory.generatePublic(rsaPubKey);
            Cipher cipher = Cipher.getInstance(Constants.RSA);
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(rsaPubKey));
            byte[] a = cipher.doFinal(password.getBytes());
            byte[] publicKey = Base64.getDecoder().decode(publicKeyTemp.getBytes());
            byte[] encryptedData = encrypt(publicKey,
                password.getBytes());
            return Base64.getEncoder().encodeToString(a);
        }catch (Exception e){
            logger.error("encryptionPass failed ---> {}",e.getMessage());
            return null;
        }
    }


}
