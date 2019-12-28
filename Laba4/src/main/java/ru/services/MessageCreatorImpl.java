package ru.services;

import org.springframework.jms.core.MessageCreator;
import ru.entities.History;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class MessageCreatorImpl implements MessageCreator {
    private History history;

    public MessageCreatorImpl(History history){
        this.history = history;
    }
    @Override
    public Message createMessage(Session session) throws JMSException {
        Message message = session.createObjectMessage(history);
        return message;
    }
}
