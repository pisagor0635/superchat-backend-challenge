package de.superchat.superchatbackend.services.message;

import de.superchat.superchatbackend.model.Contact;
import de.superchat.superchatbackend.model.Message;


import java.util.List;
import java.util.Map;

public interface MessageService {

    Message sendMessage(Message message) ;
    List<String> getConversation(Long senderId,Long recipientId);
    Map<String,List<String>> getConversations(Contact senderContact);
}
