package com.lpb.service.viettel.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class GetNameRequest {
    private String username;
    private String password;
    private channel_info channel_info;
    private String order_id;
    private String trans_date;
    private String service_code;
    private sender sender;
    private receiver receiver;
    private String partner_code;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class channel_info {
        private String channel_type;
        private String source;
        private String acc_no;
        private String shop_code;
        private String atm_address;
        private String shop_name;
        private String shop_address;
        private String staff_id;
        private String bank_code;
        private String staff_id_no;
        private String staff_mobile_number;
        private String website_name;
        private String website_address;
        private String atm_number;
        private String mobile_number;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class sender {
        private String id_no;
        private String name;
        private String msisdn;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class receiver {
        private String msisdn;
        private String name;
    }
}
