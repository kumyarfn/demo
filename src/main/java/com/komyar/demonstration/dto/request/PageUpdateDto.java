package com.komyar.demonstration.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PageUpdateDto {

    private String uuid;

    private String fullName;

    private String username;

    private String password;

}
