package com.lpb.service.viettel.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class GetNameResponse {
    @JsonProperty("data")
    private data responseGetName;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class data{
        @JsonProperty("key_otp")
        private String keyOtp;
        @JsonProperty("channel_info")
        private channel_info channelInfo;
        @JsonProperty("sender")
        private sender sender;
        @JsonProperty("receiver")
        private receiver receiver;
        @JsonProperty("trans_date")
        private String transDate;
        @JsonProperty("error_msg")
        private String errorMsg;
        @JsonProperty("group_name")
        private String groupName;
        @JsonProperty("service_code")
        private String serviceCode;
        @JsonProperty("trans_id")
        private String transId;
        @JsonProperty("error_code")
        private String errorCode;
        @JsonProperty("order_id")
        private String orderId;
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
