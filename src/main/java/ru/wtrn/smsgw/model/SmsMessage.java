package ru.wtrn.smsgw.model;

import lombok.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Value
public class SmsMessage {
    UUID secret;
    String from;
    LocalDateTime timestamp;
    String body;

    private static final String delimiter = "\\^~";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm");

    public static SmsMessage parse(String hookPayload) {
        String[] parts = hookPayload.split(delimiter);

        String from = parts[1];
        String body = parts[2];

        String date = parts[3];
        String time = parts[4];
        LocalDateTime timestamp = LocalDateTime.parse(date + " " + time, dateTimeFormatter);

        return new SmsMessage(
                UUID.fromString(parts[0]),
                from,
                timestamp,
                body
        );
    }
}
