package com.komyar.demonstration.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PageInfoDto {

    private String fullName;

    private String username;

    private String phoneNumber;

    private LocalDateTime updateDate;

    private List<String> following;

    private List<String> followedBy;

}
