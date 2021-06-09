package de.superchat.superchatbackend.repository;

import de.superchat.superchatbackend.model.Contact;
import de.superchat.superchatbackend.model.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT m FROM Message m where " +
            "((m.messageSender.id = ?1 AND m.messageRecipient.id = ?2) " +
            "OR (m.messageSender.id= ?2 AND m.messageRecipient.id = ?1))")
    List<Message> findConversation(Long senderId, Long recipientId, Sort sort);

    @Query(value = "SELECT m.messageRecipient FROM Message m " +
            "JOIN Contact c on m.messageSender.id = c.id where (m.messageSender.id = ?1)")
    List<Contact> findConversations(long id, Sort sort);
}

