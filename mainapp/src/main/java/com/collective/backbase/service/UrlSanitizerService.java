package com.collective.backbase.service;

import com.collective.backbase.datasource.dataservice.URLModelDataService;
import com.collective.backbase.datasource.model.URLModel;
import com.collective.backbase.exceptions.ShortUrlNotFoundException;
import com.collective.backbase.response.UrlSanitizeResponse;
import com.collective.backbase.util.UrlSanitizerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.collective.backbase.constants.StringConstants.SHORT_URL_NOT_FOUND;

@Service
@Slf4j
public class UrlSanitizerService {

  private UrlSanitizerUtil urlSanitizerUtil;
  private URLModelDataService urlModelDataService;

  @Autowired
  public UrlSanitizerService(UrlSanitizerUtil encoderUtil, URLModelDataService modelDataService) {
    this.urlSanitizerUtil = encoderUtil;
    this.urlModelDataService = modelDataService;
  }

  public UrlSanitizeResponse getShortURL(String longUrl) {
    log.info("Inside method sanitizeUrl");

    URLModel savedModel = urlModelDataService.getURLModelByLongUrl(longUrl);
    if (savedModel != null) {
      return urlSanitizerUtil.buildShortUrlSanitizerResponse(savedModel);
    }

    String shortUrl = urlSanitizerUtil.sanitizeUrl(longUrl);
    return urlSanitizerUtil.buildShortUrlSanitizerResponse(saveUrlModel(longUrl, shortUrl));
  }

  public UrlSanitizeResponse getLongURL(String shortUrl) {
    URLModel urlModel = urlModelDataService.getURLModelByShortUrl(shortUrl);
    if (urlModel == null) {
      throw new ShortUrlNotFoundException(SHORT_URL_NOT_FOUND);
    }

    return urlSanitizerUtil.buildLongUrlSanitizerResponse(urlModel);
  }

  public URLModel saveUrlModel(String longUrl, String shortUrl) {
    log.info("Inside method saveUrlModel");

    URLModel urlModel = urlSanitizerUtil.buildURLModel(longUrl, shortUrl);
    return urlModelDataService.saveData(urlModel);
  }
}
