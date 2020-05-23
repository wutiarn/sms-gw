package ru.wtrn.smsgw.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@ConfigurationProperties("ru.wtrn.sms-gw")
public class SmsGwProperties {
    public UUID hookSecret;
}
