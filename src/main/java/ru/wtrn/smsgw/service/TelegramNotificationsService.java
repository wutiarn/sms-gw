package ru.wtrn.smsgw.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.wtrn.smsgw.configuration.properties.SmsGwProperties;
import ru.wtrn.smsgw.dto.telegram.SendMessageRequest;

import javax.validation.constraints.NotNull;

@Service
public class TelegramNotificationsService {

    private final RestTemplate restTemplate;
    private final String chatId;

    public TelegramNotificationsService(SmsGwProperties smsGwProperties) {
        SmsGwProperties.@NotNull TelegramProperties telegramProperties = smsGwProperties.getTelegram();
        restTemplate = new RestTemplateBuilder()
                .rootUri("https://api.telegram.org/bot" + telegramProperties.getApplicationToken())
                .build();
        chatId = telegramProperties.getRecipientId();
    }

    public void sendNotification(String message) {
        SendMessageRequest request = SendMessageRequest.builder()
                .text(message)
                .chatId(chatId)
                .build();
        restTemplate.postForEntity("/sendMessage", request, String.class);
    }
}
