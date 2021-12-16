package com.tsadigov.etaskify.config;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class MapperConfig<T extends Object, E extends Object> {

    ModelMapper mapper = new ModelMapper();

    public T toModel(T model, E dto){
        mapper.map(dto, model);
        return model;
    }
    public E toDTO(T model, E dto){
        mapper.map(model, dto);
        return dto;
    }
}
