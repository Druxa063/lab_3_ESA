package ru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import ru.entities.Group;
import ru.entities.History;
import ru.repositories.GroupRepository;
import ru.repositories.HistoryRepository;
import ru.services.GroupService;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ReceiveMessage implements SessionAwareMessageListener<ObjectMessage> {

    @Autowired
    private HistoryRepository repository;

    @Override
    public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {
        if(objectMessage.getObject() instanceof History) {
            History history = (History) objectMessage.getObject();
            repository.save(history);
            System.out.println(1);
        }
    }

    /*@JmsListener(destination = "${jsa.activemq.topic}")
    public void receive(Change msg){
        System.out.println("Recieved Message: " + msg);
    }*/
}

