package ru.wtrn.smsgw.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Validated
@ConfigurationProperties("ru.wtrn.sms-gw")
public class SmsGwProperties {
    @NotNull
    UUID hookSecret;

    @NotNull
    @NestedConfigurationProperty
    TelegramProperties telegram;

    @Validated
    @Setter
    @Getter
    public static final class TelegramProperties {
        @NotNull
        String applicationToken;
        @NotNull
        String recipientId;
    }
}
