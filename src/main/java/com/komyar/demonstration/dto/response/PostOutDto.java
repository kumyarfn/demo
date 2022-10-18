package com.komyar.demonstration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PostOutDto {

    private Long id;

    private String text;

    private String updateDate;

}
