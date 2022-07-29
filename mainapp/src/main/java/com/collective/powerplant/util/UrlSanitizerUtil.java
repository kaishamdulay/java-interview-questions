package com.collective.powerplant.util;

import com.collective.powerplant.datasource.model.URLModel;
import com.collective.powerplant.response.UrlSanitizeResponse;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class UrlSanitizerUtil {

  private static final String ALPHABET =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int BASE = ALPHABET.length();

  public String sanitizeUrl(String longUrl) {
    log.info("Inside method sanitizeUrl");

    String url = "";
    for (int i = 0; i < longUrl.length(); i++) {
      if ('a' <= longUrl.charAt(i) && longUrl.charAt(i) <= 'z')
        url = url + RandomString.make(2) + longUrl.charAt(i) + 'a';
      if ('A' <= longUrl.charAt(i) && longUrl.charAt(i) <= 'Z')
        url = url + RandomString.make(2) + longUrl.charAt(i) + 'A';

      if ('0' <= longUrl.charAt(i) && longUrl.charAt(i) <= '9')
        url = url + RandomString.make(2) + longUrl.charAt(i) + '0';
    }
    return url.substring(0, 38);
  }

  public URLModel buildURLModel(String longUrl,String shortUrl){
    log.info("Inside method buildURLModel");

    URLModel urlModel=new URLModel();
    urlModel.setLongUrl(longUrl);
    urlModel.setShortUrl(shortUrl);
    urlModel.setCreatedOn(new Date());
    urlModel.setUpdatedOn(new Date());
    return urlModel;

  }

  public UrlSanitizeResponse buildShortUrlSanitizerResponse(URLModel urlModel) {
    log.info("Inside method buildShortUrlSanitizerResponse");
    return UrlSanitizeResponse.builder().shortUlr(urlModel.getShortUrl()).build();
  }

  public UrlSanitizeResponse buildLongUrlSanitizerResponse(URLModel urlModel) {
    log.info("Inside method buildLongUrlSanitizerResponse");
    return UrlSanitizeResponse.builder().longUrl(urlModel.getLongUrl()).build();
  }
}
