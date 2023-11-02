package com.lpb.service.viettel.service;


import com.lpb.esb.service.common.model.response.ResponseModel;
import com.lpb.service.viettel.model.request.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;


public interface ViettelService {
    ResponseModel getName(ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException;

    ResponseModel getFee(ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException;

    ResponseModel chainTransfer(ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException;

    ResponseModel queryTransfer(ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException;
}
