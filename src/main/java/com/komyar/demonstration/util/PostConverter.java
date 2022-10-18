package com.komyar.demonstration.util;

import com.komyar.demonstration.db.entity.PostEntity;
import com.komyar.demonstration.dto.response.PostOutDto;

public class PostConverter {

    public static PostOutDto convertEntityToDto(PostEntity entity){
        return new PostOutDto(entity.getId(), entity.getText(), entity.getUpdateDate().toString());
    }
}
