package com.collective.powerplant.response;

import lombok.Builder;
import lombok.Data;

@Data
public class BaseResponse {
    private String responseMessage;
    private String responseCode;
    private String status;
}
