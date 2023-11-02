package com.lpb.service.viettel.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpb.esb.service.common.model.response.LpbResCode;
import com.lpb.esb.service.common.model.response.ResponseModel;
import com.lpb.esb.service.common.model.response.ServiceInfo;
import com.lpb.esb.service.common.service.ConnectInfoService;
import com.lpb.esb.service.common.utils.code.ErrorMessage;
import com.lpb.service.viettel.config.RestTemplateConfig;
import com.lpb.service.viettel.mapper.RequestMapper;
import com.lpb.service.viettel.model.request.*;
import com.lpb.service.viettel.model.response.ChainTransferResponse;
import com.lpb.service.viettel.model.response.GetFeeResponse;
import com.lpb.service.viettel.model.response.GetNameResponse;
import com.lpb.service.viettel.model.response.QueryTransResponse;
import com.lpb.service.viettel.service.ViettelService;
import com.lpb.service.viettel.utils.Constants;
import com.lpb.service.viettel.utils.ConvertDate;
import com.lpb.service.viettel.utils.RSAUtils;
import com.lpb.service.viettel.utils.XmlUtils;
import net.logstash.logback.encoder.org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class ViettelServiceImpl implements ViettelService {
    private static final Logger logger = LoggerFactory.getLogger(ViettelServiceImpl.class);
    @Value("${configMM.userName}")
    private String userName;
    @Value("${configMM.password}")
    private String password;
    final RequestMapper requestMapper;
    final RestTemplateConfig restTemplate;


    public ViettelServiceImpl(RequestMapper requestMapper, RestTemplateConfig restTemplate) {
        this.requestMapper = requestMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseModel<Object> getName(ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException {
        ResponseModel<Object> responseModel;
        LpbResCode lpbResCode;
        ObjectMapper objectMapper = new ObjectMapper();
        ServiceInfo serviceInfo = getServiceInfo(viettelMoneyRequest);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GetNameRequest getNameRequest = objectMapper.readValue(objectMapper.writeValueAsString(viettelMoneyRequest.getBody().getData()), GetNameRequest.class);
        getNameRequest.setUsername(userName);
        getNameRequest.setPassword(password);
        getNameRequest.setPartner_code("DL_IOM");
        getNameRequest.setService_code(Constants.MMSERVICEAPI);
        getNameRequest.setOrder_id(ConvertDate.genOrderId(new Date()));
        getNameRequest.setTrans_date(ConvertDate.genTransDate(new Date()));
        String getName =XmlUtils.requestBodyGetName(getNameRequest);
        logger.info("chainGetName : json body get name ----> {}",getName);
        String signature = RSAUtils.generateSignature(getName);
        String requestGetName = XmlUtils.buildRequestViettel(Constants.MM_CHAIN_GET_NAME,getName,signature);
        logger.info("chainGetName : request xml get Name ----> {}",requestGetName);
        try {
            JSONObject jsonObject = requestBuildVT(serviceInfo, requestGetName);
            GetNameResponse getNameResponse=objectMapper.readValue(jsonObject.toString(), GetNameResponse.class);
            logger.info("response getName -----> {}",jsonObject);
            if(StringUtils.equalsIgnoreCase(getNameResponse.getResponseGetName().getErrorCode(),Constants.SUCCESS)){
                lpbResCode = LpbResCode.builder()
                    .errorCode(ErrorMessage.SUCCESS.label)
                    .errorDesc(ErrorMessage.SUCCESS.description)
                    .build();
            }else {
                lpbResCode = LpbResCode.builder()
                    .errorCode(ErrorMessage.FAIL.label)
                    .errorDesc(ErrorMessage.FAIL.description)
                    .build();
            }
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(getNameResponse)
                .build();
        }catch (RestClientResponseException e) {
            logger.error("chainGetName : call api get name timeout: {}", ExceptionUtils.getStackTrace(e));
            lpbResCode = LpbResCode.builder()
                .errorCode(ErrorMessage.TIMEOUT.label)
                .errorDesc(ErrorMessage.TIMEOUT.description)
                .build();
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(getNameRequest)
                .build();
        }catch (Exception e) {
            logger.error("chainGetName : call api get name error: {}", e.getMessage());
            lpbResCode = LpbResCode.builder()
                .errorCode(ErrorMessage.FAIL.label)
                .errorDesc(ErrorMessage.FAIL.description)
                .build();
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(getNameRequest)
                .build();
        }
        return responseModel;
    }


    @Override
    public ResponseModel<Object> getFee(ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException {
        ResponseModel<Object> responseModel;
        LpbResCode lpbResCode;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ServiceInfo serviceInfo = getServiceInfo(viettelMoneyRequest);
        GetFeeRequest getFeeRequest = objectMapper.readValue(objectMapper.writeValueAsString(viettelMoneyRequest.getBody().getData()), GetFeeRequest.class);
        getFeeRequest.setUsername(userName);
        getFeeRequest.setPassword(password);
        getFeeRequest.setService_code(Constants.MMSERVICEAPI);
        String bodyGetFee = XmlUtils.requestGetFee(getFeeRequest);
        logger.info("getFee : json body get fee ----> {}",bodyGetFee);
        String signature = RSAUtils.generateSignature(bodyGetFee);
        String requestGetFee = XmlUtils.buildRequestViettel(Constants.MM_CHAIN_GET_FEE,bodyGetFee,signature);
        logger.info("getFee : request xml get fee ----> {}",requestGetFee);
        try {
            JSONObject jsonObject = requestBuildVT(serviceInfo, requestGetFee);
            GetFeeResponse getFeeResponse=objectMapper.readValue(jsonObject.toString(), GetFeeResponse.class);
            logger.info("response getFee -----> {}",getFeeResponse);
            if(StringUtils.equalsIgnoreCase(getFeeResponse.getData().getErrorCode(),Constants.SUCCESS)){
                lpbResCode = LpbResCode.builder()
                    .errorCode(ErrorMessage.SUCCESS.label)
                    .errorDesc(ErrorMessage.SUCCESS.description)
                    .build();
            }else {
                lpbResCode = LpbResCode.builder()
                    .errorCode(ErrorMessage.FAIL.label)
                    .errorDesc(ErrorMessage.FAIL.description)
                    .build();
            }
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(getFeeResponse)
                .build();
        }catch (RestClientResponseException e) {
            logger.error("getFee : call api get name timeout: {}", ExceptionUtils.getStackTrace(e));
            lpbResCode = LpbResCode.builder()
                .errorCode(ErrorMessage.TIMEOUT.label)
                .errorDesc(ErrorMessage.TIMEOUT.description)
                .build();
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(getFeeRequest)
                .build();
        }catch (Exception e) {
            logger.error("getFee : call api get name error: {}", e.getMessage());
            lpbResCode = LpbResCode.builder()
                .errorCode(ErrorMessage.FAIL.label)
                .errorDesc(ErrorMessage.FAIL.description)
                .build();
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(getFeeRequest)
                .build();
        }
        return responseModel;
    }

    @Override
    public ResponseModel<Object> chainTransfer(ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException {
        ResponseModel<Object> responseModel;
        LpbResCode lpbResCode;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ServiceInfo serviceInfo = getServiceInfo(viettelMoneyRequest);
        ChainTransferRequest chainTransferRequest = objectMapper.readValue(objectMapper.writeValueAsString(viettelMoneyRequest.getBody().getData()), ChainTransferRequest.class);
        chainTransferRequest.setUsername(userName);
        chainTransferRequest.setPassword(password);
        chainTransferRequest.setService_code(Constants.MMSERVICEAPI);
        String requestChainTransfer = XmlUtils.requestChainTransfer(chainTransferRequest);
        logger.info("chainTransfer : json body chain transfer ----> {}",requestChainTransfer);
        String signature = RSAUtils.generateSignature(requestChainTransfer);
        String requestTransfer = XmlUtils.buildRequestViettel(Constants.MM_CHAIN_TRANSFER,requestChainTransfer,signature);
        logger.info("chainTransfer : request xml chain transfer ----> {}",requestTransfer);
        try {
            JSONObject jsonObject = requestBuildVT(serviceInfo, requestTransfer);
            ChainTransferResponse chainTransferResponse=objectMapper.readValue(jsonObject.toString(), ChainTransferResponse.class);
            logger.info("response chainTransfer -----> {}",chainTransferResponse);
            if(StringUtils.equalsIgnoreCase(chainTransferResponse.getData().getErrorCode(),Constants.SUCCESS)){
                lpbResCode = LpbResCode.builder()
                    .errorCode(ErrorMessage.SUCCESS.label)
                    .errorDesc(ErrorMessage.SUCCESS.description)
                    .build();
            }else {
                lpbResCode = LpbResCode.builder()
                    .errorCode(ErrorMessage.FAIL.label)
                    .errorDesc(ErrorMessage.FAIL.description)
                    .build();
            }
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(chainTransferResponse)
                .build();
        }catch (RestClientResponseException e) {
            logger.error("chainGetName : call api get name timeout: {}", ExceptionUtils.getStackTrace(e));
            lpbResCode = LpbResCode.builder()
                .errorCode(ErrorMessage.TIMEOUT.label)
                .errorDesc(ErrorMessage.TIMEOUT.description)
                .build();
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(chainTransferRequest)
                .build();
        }catch (Exception e) {
            logger.error("chainGetName : call api get name error: {}", e.getMessage());
            lpbResCode = LpbResCode.builder()
                .errorCode(ErrorMessage.FAIL.label)
                .errorDesc(ErrorMessage.FAIL.description)
                .build();
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(chainTransferRequest)
                .build();
        }
        return responseModel;
    }

    @Override
    public ResponseModel<Object> queryTransfer(ViettelMoneyRequest viettelMoneyRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, InvalidKeyException {
        ResponseModel<Object> responseModel;
        LpbResCode lpbResCode;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ServiceInfo serviceInfo = getServiceInfo(viettelMoneyRequest);
        QueryTransRequest queryTransRequest = objectMapper.readValue(objectMapper.writeValueAsString(viettelMoneyRequest.getBody().getData()), QueryTransRequest.class);
        queryTransRequest.setUsername(userName);
        queryTransRequest.setPassword(password);
        queryTransRequest.setService_code(Constants.MMSERVICEAPI);
        String requestQueryTrans = XmlUtils.requestQueryTrans(queryTransRequest);
        logger.info("queryTransfer : json body get query Transfer ----> {}",requestQueryTrans);
        String signature = RSAUtils.generateSignature(requestQueryTrans);
        String requestQueryBill = XmlUtils.buildRequestViettel(Constants.MM_CHAIN_QUERY_TRANS,requestQueryTrans,signature);
        logger.info("queryTransfer : request xml query Transfer ----> {}",requestQueryBill);
        try {
            JSONObject jsonObject = requestBuildVT(serviceInfo, requestQueryBill);
            QueryTransResponse queryTransResponse=objectMapper.readValue(jsonObject.toString(), QueryTransResponse.class);
            logger.info("response query transfer  -----> {}",queryTransResponse);
            if(StringUtils.equalsIgnoreCase(queryTransResponse.getData().getErrorCode(),Constants.SUCCESS)){
                lpbResCode = LpbResCode.builder()
                    .errorCode(ErrorMessage.SUCCESS.label)
                    .errorDesc(ErrorMessage.SUCCESS.description)
                    .build();
            }else {
                lpbResCode = LpbResCode.builder()
                    .errorCode(ErrorMessage.FAIL.label)
                    .errorDesc(ErrorMessage.FAIL.description)
                    .build();
            }
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(queryTransResponse)
                .build();
        }catch (RestClientResponseException e) {
            logger.error("queryTransfer : call api query transfer  timeout: {}", ExceptionUtils.getStackTrace(e));
            lpbResCode = LpbResCode.builder()
                .errorCode(ErrorMessage.TIMEOUT.label)
                .errorDesc(ErrorMessage.TIMEOUT.description)
                .build();
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(queryTransRequest)
                .build();
        }catch (Exception e) {
            logger.error("queryTransfer : call api query transfer  error: {}", e.getMessage());
            lpbResCode = LpbResCode.builder()
                .errorCode(ErrorMessage.FAIL.label)
                .errorDesc(ErrorMessage.FAIL.description)
                .build();
            responseModel = ResponseModel.builder()
                .resCode(lpbResCode)
                .data(queryTransRequest)
                .build();
        }
        return responseModel;
    }
    private JSONObject requestBuildVT(ServiceInfo serviceInfo, String request) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("text/xml; charset=utf-8"));
        HttpEntity<String> httpEntity = new HttpEntity<>(request,httpHeaders);
        ResponseEntity<String> response = restTemplate.getRestTemplatePartner()
            .exchange(serviceInfo.getConnectorURL(),
                HttpMethod.POST,httpEntity,String.class);
        List<String> body = Arrays.asList(Arrays.asList(Objects.requireNonNull(response.getBody()).split("<return>")).get(1).split("</return>"));
        String returnBody = body.get(0).replaceAll("&quot;","'");
        return new JSONObject(returnBody);
    }
    private ServiceInfo getServiceInfo(ViettelMoneyRequest viettelMoneyRequest){
        List<ServiceInfo> list = ConnectInfoService.getServiceInfo(restTemplate.getRestTemplateLB() , viettelMoneyRequest.getHeader().getServiceId(),
            viettelMoneyRequest.getHeader().getProductCode());
        return list.get(0);
    }
}
