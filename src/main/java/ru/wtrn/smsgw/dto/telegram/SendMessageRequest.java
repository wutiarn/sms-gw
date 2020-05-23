package ru.wtrn.smsgw.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SendMessageRequest {
    String text;

    @JsonProperty("chat_id")
    String chatId;
}
