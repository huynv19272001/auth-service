package com.lpb.service.viettel.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class QueryTransResponse {
    @JsonProperty("data")
    private data data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class data{
        @JsonProperty("end_date")
        private String endDate;
        @JsonProperty("channel_info")
        private channel_info channelInfo;
        @JsonProperty("sender")
        private sender sender;
        @JsonProperty("receiver")
        private receiver receiver;
        @JsonProperty("original_request_date")
        private String originalRequestDate;
        @JsonProperty("trans_date")
        private String transDate;
        @JsonProperty("error_msg")
        private String errorMsg;
        @JsonProperty("group_name")
        private String groupName;
        @JsonProperty("service_code")
        private String serviceCode;
        @JsonProperty("error_code")
        private String errorCode;
        @JsonProperty("reference_code")
        private String referenceCode;
        @JsonProperty("reference_msg")
        private String referenceMsg;

        @JsonProperty("order_id")
        private String orderId;
        @JsonProperty("original_order_id")
        private String originalOrderId;
        @JsonProperty("username")
        private String username;
        @JsonProperty("start_date")
        private String startDate;
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
            @JsonProperty("mobile_number")
            private String mobileNumber;
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
            @JsonProperty("id_no")
            private String idNo;
            @JsonProperty("msisdn")
            private String msisdn;
            @JsonProperty("name")
            private String name;
        }
    }
}
