package com.collective.backbase.response;

import lombok.Data;

@Data
public class BaseResponse {
    private String responseMessage;
    private String responseCode;
    private String status;
}
