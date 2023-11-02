package com.lpb.service.viettel.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class ChainTransferResponse {
    @JsonProperty("data")
    private data data;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class data{
        @JsonProperty("amount")
        private String amount;
        @JsonProperty("trans_date")
        private String transDate;
        @JsonProperty("channel_info")
        private channel_info channelInfo;
        @JsonProperty("sender")
        private sender sender;
        @JsonProperty("receiver")
        private receiver receiver;
        @JsonProperty("error_msg")
        private String errorMsg;
        @JsonProperty("group_name")
        private String groupName;
        @JsonProperty("otp")
        private String otp;
        @JsonProperty("original_trans_id")
        private String originalTransId;
        @JsonProperty("trans_id")
        private String transId;
        @JsonProperty("fee_type")
        private String feeType;
        @JsonProperty("sum_amount_fee")
        private String sumAmountFee;
        @JsonProperty("trans_fee")
        private String transFee;
        @JsonProperty("trans_content")
        private String transContent;
        @JsonProperty("service_code")
        private String serviceCode;
        @JsonProperty("error_code")
        private String errorCode;
        @JsonProperty("order_id")
        private String orderId;
        @JsonProperty("original_order_id")
        private String originalOrderId;
        @JsonProperty("username")
        private String username;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class channel_info {
            @JsonProperty("bank_code")
            private String bankCode;
            @JsonProperty("shop_code")
            private String shopCode;
            @JsonProperty("acc_no")
            private String accNo;
            @JsonProperty("source")
            private String source;
            @JsonProperty("channel_type")
            private String channelType;
            @JsonProperty("mobile_number")
            private String mobileNumber;
        }
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class sender {
            @JsonProperty("id_no")
            private String idNo;
            @JsonProperty("name")
            private String name;
            @JsonProperty("msisdn")
            private String msisdn;
        }
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class receiver {
            @JsonProperty("msisdn")
            private String msisdn;
            @JsonProperty("name")
            private String name;
            @JsonProperty("id_no")
            private String idNo;
        }
    }

}
