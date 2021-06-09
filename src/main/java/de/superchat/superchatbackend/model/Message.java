package de.superchat.superchatbackend.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "message_content")
    private String messageContent;

    @OneToOne()
    @JoinColumn(name = "messag_sender_id", referencedColumnName = "id")
    private Contact messageSender;

    @OneToOne()
    @JoinColumn(name = "message_recipient_id", referencedColumnName = "id")
    private Contact messageRecipient;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date creationDate;

    public Message() {
    }

    public Message(String messageContent, Contact messageSender, Contact messageRecipient) {
        this.messageContent = messageContent;
        this.messageSender = messageSender;
        this.messageRecipient = messageRecipient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Contact getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(Contact messageSender) {
        this.messageSender = messageSender;
    }

    public Contact getMessageRecipient() {
        return messageRecipient;
    }

    public void setMessageRecipient(Contact messageRecipient) {
        this.messageRecipient = messageRecipient;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
