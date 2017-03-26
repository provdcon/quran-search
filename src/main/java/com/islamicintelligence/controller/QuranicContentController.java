package com.islamicintelligence.controller;

import com.islamicintelligence.service.StorageService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mufy on 26/03/2017.
 */

@Controller
@RequestMapping("quran")
@Slf4j
public class QuranicContentController {

    @Value("${quran.json.path}")
    private Resource quranJsonFileURI;

    private StorageService storageService;

    @Autowired
    public QuranicContentController(StorageService storageService){

        this.storageService = storageService;
    }

    @GetMapping("/load")
    @ResponseBody
    public String getQuranicContent() throws Exception{

            System.out.println (quranJsonFileURI.getURL().toString());
            storageService.loadJsonQuranContent();
            return "Storage to elastic store was successful";

    }

    public String getSearchContent (String searchTerm){

        return null;
    }
}
