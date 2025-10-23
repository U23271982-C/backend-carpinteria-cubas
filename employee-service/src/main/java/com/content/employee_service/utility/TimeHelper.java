package com.content.employee_service.utility;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class TimeHelper {
    private static final ZoneId defaultZone = ZoneId.of("America/Lima");

    public Instant getCurrentInstant() {
        return Instant.now();
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.ofInstant(Instant.now(), defaultZone);
    }
}
