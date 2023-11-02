package com.lpb.service.viettel.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class GetFeeResponse {
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
        @JsonProperty("error_msg")
        private String errorMsg;
        @JsonProperty("receiver")
        private GetNameResponse.data.receiver receiver;
        @JsonProperty("group_name")
        private String groupName;
        @JsonProperty("original_trans_id")
        private String originalTransId;
        @JsonProperty("fee_type")
        private String feeType;
        @JsonProperty("channel_info")
        private GetNameResponse.data.channel_info channelInfo;
        @JsonProperty("sum_amount_fee")
        private String sumAmountFee;
        @JsonProperty("trans_fee")
        private String transFee;
        @JsonProperty("sender")
        private GetNameResponse.data.sender sender;
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
            @JsonProperty("channel_type")
            private String channelType;
            @JsonProperty("source")
            private String source;
            @JsonProperty("acc_no")
            private String accNo;
            @JsonProperty("shop_code")
            private String shopCode;
            @JsonProperty("atm_address")
            private String atmAddress;
            @JsonProperty("shop_name")
            private String shopName;
            @JsonProperty("shop_address")
            private String shopAddress;
            @JsonProperty("staff_id")
            private String staffId;
            @JsonProperty("bank_code")
            private String bankCode;
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
        }
    }
}
