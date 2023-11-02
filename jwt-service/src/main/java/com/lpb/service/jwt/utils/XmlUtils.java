package com.lpb.service.viettel.utils;


import com.lpb.service.viettel.model.request.ChainTransferRequest;
import com.lpb.service.viettel.model.request.GetFeeRequest;
import com.lpb.service.viettel.model.request.GetNameRequest;
import com.lpb.service.viettel.model.request.QueryTransRequest;

public class XmlUtils {
//    public static String buildRequestViettel(String type, String bodyGetName, String signature) {
//        String xmlRaw =
//            "   <par:process>\n" +
//            "   <cmd>" + "%s" + "</cmd>\n" +
//            "   <data>" + "%s" + "</data>\n" +
//            "      <signature>" + "%s" + "</signature>\n" +
//            "</par:process>\n" +
//            "    </soapenv:Body>\n" +
//            "</soapenv:Envelope>";
//        return String.format(xmlRaw, type, bodyGetName, signature);
//    }
    public static String buildRequestViettel(String type, String bodyGetName, String signature) {
        String xmlRaw =
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:par=\"http://partnerapi.bankplus.viettel.com/\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <par:process>\n" +
                "            <cmd>%s</cmd>\n" +
                "            <data>%s</data>\n" +
                "            <signature>%s</signature>\n" +
                "        </par:process>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        return String.format(xmlRaw, type, bodyGetName, signature);
    }
    public static String requestBodyGetName(GetNameRequest getNameRequest){
        String requestGetName = "{\"username\":\"%s\",\"password\":\"%s\", \"channel_info\":{\"channel_type\":\"%s\",\"source\":\"%s\",\"acc_no\":\"%s\",\"shop_code\":\"%s\",\"atm_address\":\"%s\",\"shop_name\":\"%s\",\"shop_address\":\"%s\",\"staff_id\":\"%s\",\"bank_code\":\"%s\"},\"sender\":{ \"msisdn\":\"%s\",\"name\":\"%s\",\"id_no\":\"%s\" },\"receiver\": { \"msisdn\":\"%s\" },\"service_code\":\"%s\",\"order_id\":\"%s\"}";
        return String.format(requestGetName,getNameRequest.getUsername(),
              getNameRequest.getPassword(),
            getNameRequest.getChannel_info().getChannel_type(),
            getNameRequest.getChannel_info().getSource(),
            getNameRequest.getChannel_info().getAcc_no(),
            getNameRequest.getChannel_info().getShop_code(),
            getNameRequest.getChannel_info().getAtm_address(),
            getNameRequest.getChannel_info().getShop_name(),
            getNameRequest.getChannel_info().getShop_address(),
            getNameRequest.getChannel_info().getStaff_id(),
            getNameRequest.getChannel_info().getBank_code(),
            getNameRequest.getReceiver().getMsisdn(),
            getNameRequest.getSender().getMsisdn(),
            getNameRequest.getSender().getName(),
            getNameRequest.getSender().getId_no(),
            getNameRequest.getService_code(),
            getNameRequest.getOrder_id()
            );
    }
    public static String requestGetFee(GetFeeRequest getFeeRequest){
        String requestGetFee = "{\"username\":\"%s\",\"password\":\"%s\",\"amount\": \"%s\",\"channel_info\":{\"channel_type\":\"%s\",\"source\":\"%s\",\"acc_no\":\"%s\",\"shop_code\":\"%s\",\"atm_address\":\"%s\",\"shop_name\":\"%s\",\"shop_address\":\"%s\",\"staff_id\":\"%s\",\"bank_code\":\"%s\"},\"sender\": {\"id_no\": \"%s\",\"name\": \"%s\",\"msisdn\": \"%s\"},\"receiver\": {\"msisdn\": \"%s\",\"name\": \"%s\"},\"service_code\": \"%s\",\"order_id\": \"%s\",\"original_order_id\": \"%s\",\"original_trans_id\": \"%s\",\"fee_type\": \"%s\"}";
        return String.format(requestGetFee,getFeeRequest.getUsername(),
            getFeeRequest.getPassword(),
            getFeeRequest.getAmount(),
            getFeeRequest.getChannel_info().getChannel_type(),
            getFeeRequest.getChannel_info().getSource(),
            getFeeRequest.getChannel_info().getAcc_no(),
            getFeeRequest.getChannel_info().getShop_code(),
            getFeeRequest.getChannel_info().getAtm_address(),
            getFeeRequest.getChannel_info().getShop_name(),
            getFeeRequest.getChannel_info().getShop_address(),
            getFeeRequest.getChannel_info().getStaff_id(),
            getFeeRequest.getChannel_info().getBank_code(),
            getFeeRequest.getSender().getId_no(),
            getFeeRequest.getSender().getName(),
            getFeeRequest.getSender().getMsisdn(),
            getFeeRequest.getReceiver().getMsisdn(),
            getFeeRequest.getReceiver().getName(),
            getFeeRequest.getService_code(),
            getFeeRequest.getOrder_id(),
            getFeeRequest.getOriginal_order_id(),
            getFeeRequest.getOriginal_trans_id(),
            getFeeRequest.getFee_type());
    }

    public static String requestChainTransfer(ChainTransferRequest chainTransferRequest){
        String requestChainTransfer = "{\"username\":\"%s\",\"password\":\"%s\",\"channel_info\": {\"channel_type\": \"%s\",\"shop_code\": \"%s\",\"source\": \"%s\",\"acc_no\": \"%s\",\"bank_code\": \"%s\",\"mobile_number\": \"%s\"},\"service_code\": \"%s\",\"sender\": {\"msisdn\": \"%s\",\"name\": \"%s\",\"id_no\": \"%s\"},\"receiver\": {\"msisdn\": \"%s\",\"name\": \"%s\",\"id_no\": \"%s\"},\"amount\": \"%s\",\"trans_content\": \"%s\",\"trans_fee\": \"%s\",\"otp\": \"%s\",\"original_order_id\": \"%s\",\"original_trans_id\": \"%s\",\"order_id\": \"%s\",\"sum_amount_fee\": \"%s\",\"fee_type\": \"%s\"}";
        return String.format(requestChainTransfer,
            chainTransferRequest.getUsername(),
            chainTransferRequest.getPassword(),
            chainTransferRequest.getChannel_info().getChannel_type(),
            chainTransferRequest.getChannel_info().getShop_code(),
            chainTransferRequest.getChannel_info().getSource(),
            chainTransferRequest.getChannel_info().getAcc_no(),
            chainTransferRequest.getChannel_info().getBank_code(),
            chainTransferRequest.getChannel_info().getMobile_number(),
            chainTransferRequest.getService_code(),
            chainTransferRequest.getSender().getMsisdn(),
            chainTransferRequest.getSender().getName(),
            chainTransferRequest.getSender().getId_no(),
            chainTransferRequest.getReceiver().getMsisdn(),
            chainTransferRequest.getReceiver().getName(),
            chainTransferRequest.getReceiver().getId_no(),
            chainTransferRequest.getAmount(),
            chainTransferRequest.getTrans_content(),
            chainTransferRequest.getTrans_fee(),
            chainTransferRequest.getOtp(),
            chainTransferRequest.getOriginal_order_id(),
            chainTransferRequest.getOriginal_trans_id(),
            chainTransferRequest.getOrder_id(),
            chainTransferRequest.getSum_amount_fee(),
            chainTransferRequest.getFee_type());
    }

    public static String requestQueryTrans(QueryTransRequest queryTransRequest){
        String queryTrans = "{\"username\":\"%s\",\"password\":\"%s\",\"channel_info\": {\"channel_type\": \"%s\",\"shop_code\": \"%s\",\"source\": \"%s\",\"acc_no\": \"%s\",\"bank_code\": \"%s\",\"mobile_number\": \"%s\"},\"order_id\": \"%s\",\"trans_date\": \"%s\",\"service_code\": \"%s\",\"original_order_id\": \"%s\",\"start_date\": \"%s\",\"end_date\": \"%s\",\"original_request_date\": \"%s\",\"sender\": {\"msisdn\": \"%s\",\"name\": \"%s\",\"id_no\": \"%s\"},\"receiver\": {\"msisdn\": \"%s\",\"name\": \"%s\",\"id_no\": \"%s\"}}";
        return String.format(queryTrans,
            queryTransRequest.getUsername(),
            queryTransRequest.getPassword(),
            queryTransRequest.getChannel_info().getChannel_type(),
            queryTransRequest.getChannel_info().getShop_code(),
            queryTransRequest.getChannel_info().getSource(),
            queryTransRequest.getChannel_info().getAcc_no(),
            queryTransRequest.getChannel_info().getBank_code(),
            queryTransRequest.getChannel_info().getMobile_number(),
            queryTransRequest.getOrder_id(),
            queryTransRequest.getTrans_date(),
            queryTransRequest.getService_code(),
            queryTransRequest.getOriginal_order_id(),
            queryTransRequest.getStart_date(),
            queryTransRequest.getEnd_date(),
            queryTransRequest.getOriginal_request_date(),
            queryTransRequest.getSender().getMsisdn(),
            queryTransRequest.getSender().getName(),
            queryTransRequest.getSender().getId_no(),
            queryTransRequest.getReceiver().getMsisdn(),
            queryTransRequest.getReceiver().getName(),
            queryTransRequest.getReceiver().getId_no());
    }
}
