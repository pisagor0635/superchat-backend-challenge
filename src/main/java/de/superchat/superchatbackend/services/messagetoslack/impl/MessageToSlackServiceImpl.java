package de.superchat.superchatbackend.services.messagetoslack.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.superchat.superchatbackend.model.SlackMessage;
import de.superchat.superchatbackend.services.messagetoslack.MessageToSlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageToSlackServiceImpl implements MessageToSlackService {

    @Autowired
    private Environment env;

    @Value("hooks_url")
    private String hooks_url;

    @Value("user_channel_id")
    private String user_channel_id;

    @Override
    public void sendMessage(SlackMessage message) throws JsonProcessingException {
        String userWebhookUrl = String.format(env.getProperty("hooks_url"), env.getProperty("user_channel_id"));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String messageJson = objectMapper.writeValueAsString(message);
        HttpEntity<String> entity = new HttpEntity<>(messageJson, headers);
        ResponseEntity<String> x = restTemplate.exchange(userWebhookUrl, HttpMethod.POST, entity, String.class);
        System.out.println("Deneme");
    }
}
