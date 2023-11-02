package com.lpb.service.viettel.controller;

import com.lpb.esb.service.common.model.response.ResponseModel;
import com.lpb.esb.service.common.utils.code.EsbErrorCode;
import com.lpb.service.viettel.model.request.*;
import com.lpb.service.viettel.service.ViettelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(value = "/api/v1/info",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ViettelController {
    @Autowired
    private ViettelService viettelService;

    @PostMapping("/detail-name")
    public ResponseEntity getName(@RequestBody ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException {
        ResponseModel responseModel = viettelService.getName(viettelMoneyRequest);
        if (EsbErrorCode.SUCCESS.label.equals(responseModel.getResCode().getErrorCode())) {
            return ResponseEntity.ok(responseModel);
        } else {
            return ResponseEntity.badRequest().body(responseModel);
        }
    }

    @PostMapping("/fee")
    public ResponseEntity getFee(@RequestBody ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException {
        ResponseModel responseModel = viettelService.getFee(viettelMoneyRequest);
        if (EsbErrorCode.SUCCESS.label.equals(responseModel.getResCode().getErrorCode())) {
            return ResponseEntity.ok(responseModel);
        } else {
            return ResponseEntity.badRequest().body(responseModel);
        }
    }

    @PostMapping("/chain-transfer")
    public ResponseEntity chainTransfer(@RequestBody ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException {
        ResponseModel responseModel = viettelService.chainTransfer(viettelMoneyRequest);
        if (EsbErrorCode.SUCCESS.label.equals(responseModel.getResCode().getErrorCode())) {
            return ResponseEntity.ok(responseModel);
        } else {
            return ResponseEntity.badRequest().body(responseModel);
        }
    }

    @PostMapping("/detail-trans")
    public ResponseEntity queryTrans(@RequestBody ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException {
        ResponseModel responseModel = viettelService.queryTransfer(viettelMoneyRequest);
        if (EsbErrorCode.SUCCESS.label.equals(responseModel.getResCode().getErrorCode())) {
            return ResponseEntity.ok(responseModel);
        } else {
            return ResponseEntity.badRequest().body(responseModel);
        }
    }
}
