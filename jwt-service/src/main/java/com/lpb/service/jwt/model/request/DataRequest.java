package com.lpb.service.viettel.model.request;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class DataRequest {
    private String order_id;
    private String username;
    private String password;
    private channel_info channel_info;
    private sender sender;
    private receiver receiver;
    private String trans_date;
    private String service_code;
    private String command;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class channel_info {
        private String channel_type;
        private String source;
        private String acc_no;
        private String bank_code;
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
