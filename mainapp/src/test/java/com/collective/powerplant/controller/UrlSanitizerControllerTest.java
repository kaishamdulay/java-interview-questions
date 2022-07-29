package com.collective.powerplant.controller;

import com.collective.powerplant.datasource.dataservice.URLModelDataService;
import com.collective.powerplant.exceptions.ShortUrlNotFoundException;
import com.collective.powerplant.response.UrlSanitizeResponse;
import com.collective.powerplant.service.UrlSanitizerService;
import com.collective.powerplant.util.UrlSanitizerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UrlSanitizerController.class)
public class UrlSanitizerControllerTest {

  private final String baseUrl = "/v1";
  private final String shortStringUrl = "/url/short";
  private final String longStringUrl = "/url/long";

  @Autowired private MockMvc mockMvc;
  @MockBean private URLModelDataService urlModelDataService;
  @MockBean private UrlSanitizerService urlSanitizerService;

  @MockBean UrlSanitizerUtil urlSanitizerUtil;

  @Test
  public void testGetShortUrlSuccess() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(baseUrl + shortStringUrl)
                .param(
                    "url",
                    "https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json5888")
                .header("x-api-key", "asdasd2343423weffsdf3423fsds"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void testGetShortUrlErrorMinLengthLessThan40() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(baseUrl + shortStringUrl)
                .param("url", "https://stash.backbase.com/proj")
                .header("x-api-key", "asdasd2343423weffsdf3423fsds"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetShortUrlErrorEmptyURl() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(baseUrl + shortStringUrl)
                .param("url", "")
                .header("x-api-key", "asdasd2343423weffsdf3423fsds"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetShortUrlWrongApiKEy() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(baseUrl + shortStringUrl)
                .param("url", "")
                .header("x-api-key", "121"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetShortUrlServerError() throws Exception {
    when(urlSanitizerService.getShortURL(anyString())).thenThrow(new RuntimeException());
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(baseUrl + shortStringUrl)
                .param(
                    "url",
                    "https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json5888")
                .header("x-api-key", "asdasd2343423weffsdf3423fsds"))
        .andDo(print())
        .andExpect(status().isInternalServerError());
  }

  @Test
  public void testGetLongUrlNotFound() throws Exception {
    when(urlSanitizerService.getLongURL(anyString()))
            .thenThrow(new ShortUrlNotFoundException("error"));
    mockMvc
        .perform(
            MockMvcRequestBuilders.get(baseUrl + longStringUrl)
                .param("tiny", "243242342343242423234234324")
                .header("x-api-key", "asdasd2343423weffsdf3423fsds"))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  public void testGetLongUrlSuccess() throws Exception {
    when(urlSanitizerService.getLongURL(anyString()))
        .thenReturn(
            UrlSanitizeResponse.builder()
                .longUrl(
                    "https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json5888")
                .build());
    mockMvc
        .perform(
            MockMvcRequestBuilders.get(baseUrl + longStringUrl)
                .param("tiny", "243242342343242423234234324")
                .header("x-api-key", "asdasd2343423weffsdf3423fsds"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void testGetLongUrlEmptyShortString() throws Exception {
    mockMvc
            .perform(
                    MockMvcRequestBuilders.get(baseUrl + longStringUrl)
                            .param("tiny", "")
                            .header("x-api-key", "asdasd2343423weffsdf3423fsds"))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetLongUrlWrongApiKey() throws Exception {
    when(urlModelDataService.getURLModelByLongUrl(anyString())).thenThrow(new RuntimeException());
    mockMvc
            .perform(
                    MockMvcRequestBuilders.get(baseUrl + longStringUrl)
                            .param("tiny", "sadsadasdsaasdsadsa")
                            .header("x-api-key", ""))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetLongUrlServerError() throws Exception {
    when(urlSanitizerService.getLongURL(anyString())).thenThrow(new RuntimeException());
    mockMvc
            .perform(
                    MockMvcRequestBuilders.get(baseUrl + longStringUrl)
                            .param(
                                    "tiny",
                                    "68ghhgjhgjhgh")
                            .header("x-api-key", "asdasd2343423weffsdf3423fsds"))
            .andDo(print())
            .andExpect(status().isInternalServerError());
  }


}
