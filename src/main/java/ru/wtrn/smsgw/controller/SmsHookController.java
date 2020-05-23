package ru.wtrn.smsgw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wtrn.smsgw.model.SmsMessage;

@RestController
public class SmsHookController {
    private final Logger logger = LoggerFactory.getLogger(SmsHookController.class);

    @RequestMapping("hooks/sms")
    public void handleIncomingSms(@RequestBody String dataStr) {
        SmsMessage message;
        try {
            message = SmsMessage.parse(dataStr);
        } catch (Exception e) {
            logger.warn(String.format("Failed to parse received sms payload: %s", dataStr));
            throw e;
        }
    }
}
