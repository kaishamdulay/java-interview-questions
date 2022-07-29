package com.collective.backbase.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlSanitizeResponse {
    private String shortUlr;
    private String longUrl;
}
