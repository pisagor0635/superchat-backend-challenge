package de.superchat.superchatbackend.services.messagetoslack;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.superchat.superchatbackend.model.SlackMessage;

public interface MessageToSlackService {
    void sendMessage(SlackMessage message) throws JsonProcessingException;
}
