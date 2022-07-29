package com.collective.backbase.scheduler;

import com.collective.backbase.datasource.dataservice.URLModelDataService;
import com.collective.backbase.datasource.model.URLModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Scheduler {
  private final URLModelDataService urlModelDataService;

  @Autowired
  public Scheduler(URLModelDataService urlModelDataService) {
    this.urlModelDataService = urlModelDataService;
  }

  @Scheduled(fixedRate = 30000)
  public void cronJobSch() {
    List<URLModel> urlModelList =
        urlModelDataService.getURLModelGreaterThanInterval();
     urlModelDataService.deleteAll(urlModelList);
  }
}
