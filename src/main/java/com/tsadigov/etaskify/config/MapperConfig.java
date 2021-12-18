package com.tsadigov.etaskify.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperConfig{

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
