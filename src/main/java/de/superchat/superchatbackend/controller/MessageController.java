package de.superchat.superchatbackend.controller;

import de.superchat.superchatbackend.model.Contact;
import de.superchat.superchatbackend.model.Message;
import de.superchat.superchatbackend.services.message.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/message/v1")
@Api(tags = "Message operations")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/send")
    @ApiOperation(value = "send message - rest api")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.sendMessage(message));
    }

    @PostMapping("/conversations")
    @ApiOperation(value = "all conversations of a contact - rest api")
    public ResponseEntity<Map<String,List<String>>> getConversations(@RequestBody Contact senderContact) {
        return ResponseEntity.ok(messageService.getConversations(senderContact));
    }

    @GetMapping("/get")
    @ApiOperation(value = "get message dialogs of two contacts - rest api")
    public ResponseEntity<List<String>> getConversation(@RequestParam Long senderId, @RequestParam Long recipientId) {
        return ResponseEntity.ok(messageService.getConversation(senderId, recipientId));
    }
}
