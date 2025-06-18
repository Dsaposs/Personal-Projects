package com.ttrpg.helper.services;

import org.modelmapper.ModelMapper;

public class EntityDtoMapper<A, B>  {
    final static ModelMapper modelMapper = new ModelMapper();
    public Object convertToDto(Object entity, Class<B> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }

    public Object convertToEntity(Object dto, Class<A> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}