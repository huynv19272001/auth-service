package com.lpb.service.viettel.model.request;

import com.lpb.esb.service.common.model.request.BodyInfoDTO;
import com.lpb.esb.service.common.model.request.HeaderInfoDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class ViettelMoneyRequest {
    private HeaderInfoDTO header;
    private BodyInfoDTO body;
}
