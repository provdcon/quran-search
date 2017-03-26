package com.islamicintelligence.service;

import groovy.util.logging.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by mufy on 26/03/2017.
 */

@Service
public class StorageService {

    RestTemplate restTemplate;

    @Value("${quran.json.path}")
    private Resource quranJsonFileURI;

    @Value("${elastic.url}")
    private String elasticURI;

    @Value ("${quran.elastic.put.path}")
    public String quranElasticPutPath;

    @Autowired
    public StorageService(final RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public void loadJsonQuranContent() throws FileNotFoundException, IOException{

        System.out.println (quranJsonFileURI.getURL().toString());

        final InputStream fs = new FileInputStream(new File(quranJsonFileURI.getURI()));

        final RequestCallback requestCallback = new RequestCallback(){

            @Override
            public void doWithRequest (final ClientHttpRequest request) throws IOException{

                request.getHeaders().add("context-type", "application/octet-stream");
                IOUtils.copy(fs, request.getBody());
            }
        };

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        restTemplate.setRequestFactory(requestFactory);
        final HttpMessageConverterExtractor<String> responseExtractor =
                new HttpMessageConverterExtractor<String>(String.class, restTemplate.getMessageConverters());
        restTemplate.execute(elasticURI+quranElasticPutPath, HttpMethod.PUT, requestCallback, responseExtractor);
    }


    public HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}
