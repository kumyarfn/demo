package com.komyar.demonstration.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {

    public static final List<String> USER_AUTHORITIES = List.of("user::read", "user::write");

    public static final List<String> ADMIN_AUTHORITIES = Stream.concat(USER_AUTHORITIES.stream(),
            Stream.of("admin::read", "admin::write")).collect(Collectors.toList());

    public static final List<String> SUPER_ADMIN_AUTHORITIES = Stream.concat(ADMIN_AUTHORITIES.stream(),
            Stream.of("SUPER::ADMIN")).collect(Collectors.toList());

    public static final String BEARER_AUTH = "bearerAuth";


}
