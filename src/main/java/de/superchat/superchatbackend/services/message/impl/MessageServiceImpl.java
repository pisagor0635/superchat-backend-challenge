package de.superchat.superchatbackend.services.message.impl;

import de.superchat.superchatbackend.model.Contact;
import de.superchat.superchatbackend.model.Message;
import de.superchat.superchatbackend.repository.MessageRepository;
import de.superchat.superchatbackend.services.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private Environment env;

    @Autowired
    private MessageRepository messageRepository;

    @Value("bitcoin")
    private String bitcoin;

    @Override
    public Message sendMessage(Message message) {
        Pattern pattern = Pattern.compile("\\[([^\\]]*)\\]");
        Matcher matcher = pattern.matcher(message.getMessageContent());
        if (matcher.find()) {
            message = replacePlaceHolder(message, matcher);
        }
        return messageRepository.save(message);
    }

    @Override
    public List<String> getConversation(Long senderId, Long recipientId) {
        return messageRepository.findConversation(senderId, recipientId, Sort.by(Sort.Direction.ASC, "creationDate"))
                .stream().map(s -> s.getMessageContent()).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<String>> getConversations(Contact senderContact) {

        List<Contact> contactList = messageRepository
                .findConversations(senderContact.getId(), Sort.by(Sort.Direction.ASC, "creationDate"))
                .stream().distinct().collect(Collectors.toList());

        Map<String, List<String>> detailList = new HashMap<>();

        for (Contact contact : contactList) {
            List<String> conversation = getConversation(senderContact.getId(), contact.getId());
            detailList.put(contact.getFirstName() + " " + contact.getLastName(), conversation);
        }

        return detailList;
    }

    private Message replacePlaceHolder(Message message, Matcher matcher) {

        String type = matcher.group(1);
        String partOfReplacement = matcher.group(0);
        switch (type) {
            case "name": {
                String messageContent = message.getMessageContent().replace(partOfReplacement, message.getMessageRecipient().getFirstName());
                message.setMessageContent(messageContent);
            }
            break;
            case "bitcoin": {
                String messageContent = message.getMessageContent().replace(partOfReplacement, env.getProperty("bitcoin"));
                message.setMessageContent(messageContent);
            }
            break;
        }
        return message;
    }
}
