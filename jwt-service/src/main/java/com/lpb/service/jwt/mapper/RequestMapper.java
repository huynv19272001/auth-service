package com.lpb.service.viettel.mapper;

import com.lpb.service.viettel.model.request.*;
import org.springframework.stereotype.Service;

@Service
public class RequestMapper {
    public GetNameRequest requestBodyGetName() {
        GetNameRequest getNameRequest = new GetNameRequest();

        /// map channelInfo
        GetNameRequest.channel_info channelInfo = new GetNameRequest.channel_info();
        channelInfo.setChannel_type("bank");
        channelInfo.setSource("shop");
        channelInfo.setAcc_no("2232323");
        channelInfo.setShop_code("30808");
        channelInfo.setAtm_address("HCM 305 Tô Hiến Thành");
        channelInfo.setShop_name("30808");
        channelInfo.setShop_address("HCM 305 Tô Hiến Thành");
        channelInfo.setStaff_id("5529");
        channelInfo.setBank_code("LPB");
        getNameRequest.setChannel_info(channelInfo);
        //map receiver
        GetNameRequest.receiver receiver = new GetNameRequest.receiver();
        receiver.setName("0376171076");
        receiver.setMsisdn("0967806109");
        getNameRequest.setReceiver(receiver);
        //map sender
        GetNameRequest.sender sender = new GetNameRequest.sender();
        sender.setName("Pham Van A");
        sender.setMsisdn("967806109");
        sender.setId_no("0967806109");
        getNameRequest.setSender(sender);

        getNameRequest.setUsername("partnerchain");
        getNameRequest.setPassword("8z3HlaBtTtUIrU9NnMkir50aefeX3zPmjPV4PcDyMp0K/cPlKRrAsn5jCvkoZmQleg7fEqr+6QEU9B7zgd1BNEobYJklJy2eXVfdW0/iyV4rq/zgvxJlP9WPW5Ts8yDkXrEU0Qk9u5z6bj08HRbVLd53vZmmqy7jiDZ74feVTnk\\u003d");
        getNameRequest.setOrder_id("202309302658192111");
        getNameRequest.setTrans_date("20220830001512");
        getNameRequest.setService_code("MMSERVICEAPI");
        getNameRequest.setPartner_code("DL_IOM");
        return getNameRequest;
    }

    public GetFeeRequest requestBodyGetFee(){
        GetFeeRequest getFeeRequest = new GetFeeRequest();

        /// map channelInfo
        getFeeRequest.setUsername("partnerchain");
        getFeeRequest.setPassword("8z3HlaBtTtUIrU9NnMkir50aefeX3zPmjPV4PcDyMp0K/cPlKRrAsn5jCvkoZmQleg7fEqr+6QEU9B7zgd1BNEobYJklJy2eXVfdW0/iyV4rq/zgvxJlP9WPW5Ts8yDkXrEU0Qk9u5z6bj08HRbVLd53vZmmqy7jiDZ74feVTnk\\u003d");
        GetFeeRequest.channel_info channelInfo = new GetFeeRequest.channel_info();
        channelInfo.setChannel_type("bank");
        channelInfo.setSource("shop");
        channelInfo.setAcc_no("2232323");
        channelInfo.setShop_code("30808");
        channelInfo.setAtm_address("HCM 305 Tô Hiến Thành");
        channelInfo.setShop_name("30808");
        channelInfo.setShop_address("HCM 305 Tô Hiến Thành");
        channelInfo.setStaff_id("5529");
        channelInfo.setBank_code("LPB");
        getFeeRequest.setChannel_info(channelInfo);
        //map receiver
        GetFeeRequest.receiver receiver = new GetFeeRequest.receiver();
        receiver.setName("Doi Quoc D***");
        receiver.setMsisdn("0967806109");
        getFeeRequest.setReceiver(receiver);
        //map sender
        GetFeeRequest.sender sender = new GetFeeRequest.sender();
        sender.setName("967806109");
        sender.setMsisdn("0967806109");
        sender.setId_no("Pham Van A");
        getFeeRequest.setSender(sender);
        getFeeRequest.setAmount("10000");
        getFeeRequest.setOrder_id("202309302658192238");
        getFeeRequest.setOriginal_order_id("202309302658192238");
        getFeeRequest.setOriginal_trans_id("231004629498398");
        getFeeRequest.setService_code("MMSERVICEAPI");
        getFeeRequest.setFee_type("0");
        return getFeeRequest;
    }

    public QueryTransRequest requestBodyQueryTrans(){
        QueryTransRequest queryTransRequest = new QueryTransRequest();

        /// map channelInfo
        QueryTransRequest.channel_info channelInfo = new QueryTransRequest.channel_info();
        channelInfo.setChannel_type("bank");
        channelInfo.setShop_name("HCM 305 Tô Hiến Thành");
        channelInfo.setStaff_id("5529");
        channelInfo.setShop_address("HCM 305 Tô Hiến Thành");
        channelInfo.setAcc_no("2232323");
        channelInfo.setSource("mobileBanking");
        channelInfo.setShop_code("30808");
        channelInfo.setBank_code("LPB");
        channelInfo.setMobile_number("0967806109");
        queryTransRequest.setChannel_info(channelInfo);
        QueryTransRequest.sender sender = new QueryTransRequest.sender();
        sender.setName("967806109");
        sender.setMsisdn("0967806109");
        sender.setId_no("Pham Van A");
        queryTransRequest.setSender(sender);
        QueryTransRequest.receiver receiver = new QueryTransRequest.receiver();
        receiver.setName("Doi Quoc D***");
        receiver.setMsisdn("0967806109");
        receiver.setId_no("");
        queryTransRequest.setReceiver(receiver);

        queryTransRequest.setOrder_id("202309302658192238");
        queryTransRequest.setUsername("partnerchain");
        queryTransRequest.setPassword("8z3HlaBtTtUIrU9NnMkir50aefeX3zPmjPV4PcDyMp0K/cPlKRrAsn5jCvkoZmQleg7fEqr+6QEU9B7zgd1BNEobYJklJy2eXVfdW0/iyV4rq/zgvxJlP9WPW5Ts8yDkXrEU0Qk9u5z6bj08HRbVLd53vZmmqy7jiDZ74feVTnk\\u003d");
        queryTransRequest.setTrans_date("20231004154434");
        queryTransRequest.setService_code("MMSERVICEAPI");
        queryTransRequest.setOriginal_order_id("202309302658192238");
        queryTransRequest.setStart_date("202309302658192238");
        queryTransRequest.setEnd_date("202310042658192238");
        queryTransRequest.setOriginal_request_date("202309302658192238");
        return queryTransRequest;
    }

    public ChainTransferRequest requestBodyChainTransfer(){
        ChainTransferRequest chainTransferRequest = new ChainTransferRequest();
        chainTransferRequest.setUsername("partnerchain");
        chainTransferRequest.setPassword("8z3HlaBtTtUIrU9NnMkir50aefeX3zPmjPV4PcDyMp0K/cPlKRrAsn5jCvkoZmQleg7fEqr+6QEU9B7zgd1BNEobYJklJy2eXVfdW0/iyV4rq/zgvxJlP9WPW5Ts8yDkXrEU0Qk9u5z6bj08HRbVLd53vZmmqy7jiDZ74feVTnk\\u003d");
        /// map channelInfo
        ChainTransferRequest.channel_info channelInfo = new ChainTransferRequest.channel_info();
        channelInfo.setChannel_type("bank");
        channelInfo.setSource("mobileBanking");
        channelInfo.setAcc_no("2232323");
        channelInfo.setShop_code("30808");
        channelInfo.setAtm_address("HCM 305 Tô Hiến Thành");
        channelInfo.setShop_name("30808");
        channelInfo.setShop_address("HCM 305 Tô Hiến Thành");
        channelInfo.setStaff_id("5529");
        channelInfo.setBank_code("LPB");
        channelInfo.setMobile_number("0967806109");
        chainTransferRequest.setChannel_info(channelInfo);
        //map receiver
        ChainTransferRequest.receiver receiver = new ChainTransferRequest.receiver();
        receiver.setName("Doi Quoc D***");
        receiver.setMsisdn("0967806109");
        receiver.setId_no("");
        chainTransferRequest.setReceiver(receiver);
        //map sender
        ChainTransferRequest.sender sender = new ChainTransferRequest.sender();
        sender.setName("967806109");
        sender.setMsisdn("0967806109");
        sender.setId_no("Pham Van A");
        chainTransferRequest.setSender(sender);

        chainTransferRequest.setService_code("MMSERVICEAPI");
        chainTransferRequest.setAmount("10000");
        chainTransferRequest.setTrans_content("hihi");
        chainTransferRequest.setTrans_fee("6688");
        chainTransferRequest.setOtp("");

        chainTransferRequest.setOriginal_order_id("202309302658192238");
        chainTransferRequest.setOriginal_trans_id("231004629498398");
        chainTransferRequest.setOrder_id("202309302658192238");
        chainTransferRequest.setSum_amount_fee("16688");
        chainTransferRequest.setFee_type("0");
        return chainTransferRequest;
    }

    public GetBillRequest getBillRequest(){
        GetBillRequest getBillRequest = new GetBillRequest();
        getBillRequest.setBilling_code("PH04000034491");
        getBillRequest.setOrder_id("1011111111110006");
        getBillRequest.setUsername("partnerchain");
        getBillRequest.setPassword("8z3HlaBtTtUIrU9NnMkir50aefeX3zPmjPV4PcDyMp0K/cPlKRrAsn5jCvkoZmQleg7fEqr+6QEU9B7zgd1BNEobYJklJy2eXVfdW0/iyV4rq/zgvxJlP9WPW5Ts8yDkXrEU0Qk9u5z6bj08HRbVLd53vZmmqy7jiDZ74feVTnk\\u003d");
        getBillRequest.setTrans_date("20230731230918");
        getBillRequest.setService_code("EVN");
        GetBillRequest.channel_info channelInfo = new GetBillRequest.channel_info();
        channelInfo.setChannelType("website");
        channelInfo.setSource("internetBanking");
        channelInfo.setWebsiteAddress("https://ecollect.vitapay.vn");
        channelInfo.setMobileNumber("0987654399");
        getBillRequest.setChannel_info(channelInfo);
        return getBillRequest;
    }

}
