package com.collective.powerplant.controller;

import com.collective.powerplant.response.UrlSanitizeResponse;
import com.collective.powerplant.service.UrlSanitizerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static com.collective.powerplant.constants.StringConstants.INVALID_URL;

@Validated
@RestController
@RequestMapping("/v1/url")
@Slf4j
public class UrlSanitizerController {

  private final UrlSanitizerService urlSanitizerService;

  @Autowired
  public UrlSanitizerController(UrlSanitizerService urlSanitizerService) {
    this.urlSanitizerService = urlSanitizerService;
  }

  @PostMapping(value = "/short")
  public UrlSanitizeResponse createShortUrl(
      @RequestParam(value = "url", required = true) @Size(min = 40, message = INVALID_URL)
          String url) {

    log.info("Inside method createShortUrl");
    return urlSanitizerService.getShortURL(url);
  }

  @GetMapping(value = "/long")
  public UrlSanitizeResponse getLongUrl(
      @RequestParam(value = "tiny", required = true) @NotEmpty String tinyUrl) {

    log.info("Inside method getLongUrl");
    return urlSanitizerService.getLongURL(tinyUrl);
  }
}
