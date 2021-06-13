package com.eshi.addis.config;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleAPI {
    @Value("${apiKey}")
    private String apiKey;

    public GeoApiContext getContext(){
      return new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }
}
