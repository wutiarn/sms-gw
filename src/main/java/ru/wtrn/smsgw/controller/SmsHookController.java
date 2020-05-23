package ru.wtrn.smsgw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wtrn.smsgw.configuration.properties.SmsGwProperties;
import ru.wtrn.smsgw.exception.IncorrectSecretException;
import ru.wtrn.smsgw.model.SmsMessage;
import ru.wtrn.smsgw.service.TelegramNotificationsService;

@RestController
public class SmsHookController {
    private final Logger logger = LoggerFactory.getLogger(SmsHookController.class);

    final SmsGwProperties smsGwProperties;
    final TelegramNotificationsService telegramNotificationsService;

    public SmsHookController(SmsGwProperties smsGwProperties, TelegramNotificationsService telegramNotificationsService) {
        this.smsGwProperties = smsGwProperties;
        this.telegramNotificationsService = telegramNotificationsService;
    }

    @RequestMapping("hooks/sms")
    public void handleIncomingSms(@RequestBody String dataStr) throws IncorrectSecretException {
        SmsMessage message;
        try {
            message = SmsMessage.parse(dataStr);
        } catch (Exception e) {
            logger.warn(String.format("Failed to parse received sms payload: %s", dataStr));
            throw e;
        }

        if (!message.getSecret().equals(smsGwProperties.getHookSecret())) {
            throw new IncorrectSecretException();
        }

        telegramNotificationsService.sendNotification(
                message.getBody() + "\n---\n" + message.getFrom()
        );
    }
}
