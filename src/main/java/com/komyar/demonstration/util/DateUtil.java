package com.komyar.demonstration.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {

    private final static String zoneId = "Iran";

    public static LocalDate localDateOfNow(){
        return LocalDate.now(ZoneId.of(zoneId));
    }

    public static LocalDateTime localDateTimeOfNow(){
        return LocalDateTime.now(ZoneId.of(zoneId));
    }
}
