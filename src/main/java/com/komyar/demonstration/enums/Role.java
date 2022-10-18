package com.komyar.demonstration.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import static com.komyar.demonstration.enums.Constants.*;

@Getter @AllArgsConstructor
public enum Role {

    USER (USER_AUTHORITIES),

    ADMIN (ADMIN_AUTHORITIES),

    SUPER_ADMIN (SUPER_ADMIN_AUTHORITIES);

    private final List<String> Authorities;

}
