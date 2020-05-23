package ru.wtrn.smsgw.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
}
