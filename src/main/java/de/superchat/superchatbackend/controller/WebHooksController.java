package de.superchat.superchatbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.superchat.superchatbackend.model.SlackMessage;
import de.superchat.superchatbackend.services.messagetoslack.MessageToSlackService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slack/v1")
@Api(tags = "Sending messages using Incoming Webhooks")
public class WebHooksController {
    @Autowired
    private MessageToSlackService messageToSlackService;

    @PostMapping(path = "/messages", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(@RequestBody SlackMessage slackMessage) {
        try{
            messageToSlackService.sendMessage(slackMessage);
            return ResponseEntity.ok().build();
        }catch (JsonProcessingException e){
            return ResponseEntity.badRequest().build();
        }


    }


}
