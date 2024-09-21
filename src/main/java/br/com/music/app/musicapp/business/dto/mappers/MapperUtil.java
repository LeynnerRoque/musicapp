package br.com.music.app.musicapp.business.dto.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    public void transform(Object o, Object t){
         BeanUtils.copyProperties(o,t);
    }

    public Object toResponse(Object entity){
        var response = new Object();
        BeanUtils.copyProperties(entity,response);
        return response;
    }

    public Object toEntity(Object response){
        var entity = new Object();
        BeanUtils.copyProperties(response,entity);
        return entity;
    }

}
