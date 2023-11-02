package com.lpb.service.viettel.model.request;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class GetBillRequest {
    private String billing_code;
    private String order_id;
    private String username;
    private String password;
    private String trans_date;
    private String service_code;
    private channel_info channel_info;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class channel_info {
        private String channelType;
        private String websiteAddress;
        private String source;
        private String mobileNumber;
    }

}

