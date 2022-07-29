package com.collective.backbase.datasource.dataservice;

import com.collective.backbase.datasource.model.URLModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface URLModelRepository extends JpaRepository<URLModel, Long>, JpaSpecificationExecutor<URLModel> {

    URLModel findByShortUrl(String url);
    URLModel findByLongUrl(String url);

    @Query(value = "select * from url_model url where url.created_on <= (LOCALTIMESTAMP - INTERVAL '30' MINUTE)",nativeQuery = true)
    List<URLModel> getUrlGreaterThanIntervalMinutes();


}
