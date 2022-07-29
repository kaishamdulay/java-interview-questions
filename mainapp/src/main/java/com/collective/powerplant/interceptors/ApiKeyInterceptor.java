package com.collective.powerplant.interceptors;

import com.collective.powerplant.exceptions.ApiKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.collective.powerplant.constants.StringConstants.INVALID_API_KEY;

@Component
@Slf4j
public class ApiKeyInterceptor implements HandlerInterceptor {

  @Value("${collective.authtoken.headername}")
  private String principalRequestHeader;

  @Value("${collective.authtoken}")
  private String principalRequestValue;

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

    final String apiKey = request.getHeader(principalRequestHeader);

    if (principalRequestValue.equals(apiKey)) {
      return true;
    } else {
      log.error("apiKey value not valid {}", apiKey);
      throw new ApiKeyException(INVALID_API_KEY);
    }
  }
}
