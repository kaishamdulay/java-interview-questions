package com.collective.backbase.datasource.dataservice;

import com.collective.backbase.datasource.model.URLModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class URLModelDataService {
    private URLModelRepository urlModelRepository;

    @Autowired
    public URLModelDataService(URLModelRepository urlModelRepository) {
        this.urlModelRepository = urlModelRepository;
    }

    public URLModel getURLModelByShortUrl(String url){
       return urlModelRepository.findByShortUrl(url);
    }

    public URLModel getURLModelByLongUrl(String url){
        return urlModelRepository.findByLongUrl(url);
    }

    public List<URLModel> getURLModelGreaterThanInterval(){

        return urlModelRepository.getUrlGreaterThanIntervalMinutes();
    }

    public void deleteAll(List<URLModel> urlModelList){
        urlModelRepository.deleteAll(urlModelList);
    }

    @Transactional
    public URLModel saveData(URLModel urlModel){
       return urlModelRepository.save(urlModel);
    }
}
