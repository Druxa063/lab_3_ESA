package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.entities.EmailHistory;
import ru.entities.Group;
import ru.entities.History;
import ru.repositories.GroupRepository;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;
import java.util.UUID;

@Service
public class GroupService {
    @Autowired
    private GroupRepository repository;

    @Autowired
    private JmsTemplate template;


    @Autowired
    private ConfigurableApplicationContext context;

    public List<Group> findAll(){
        return repository.findAll();
    }

    public Group findById(UUID id){
        return repository.findById(id).get();
    }

    @Transactional
    public boolean save(Group group){
        if(group.getName().equals("Email")){
            template.send(new EmailMessageCreator(new EmailHistory("79372009578@yandex.ru", "Group name equals to Email. Group has been inserted")));
        }
        try{
            repository.save(group);
            template.send(new MessageCreatorImpl(new History(group.getId().toString(), "Insert", group.getClass().getName())));
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public void delete(UUID id){
        if(repository.findById(id).get().getName().equals("Email")){
            template.send(new EmailMessageCreator(new EmailHistory("79372009578@yandex.ru", "Group name equals to Email. Group has been deleted")));
        }
        repository.deleteById(id);
        template.send(new MessageCreatorImpl(new History(id.toString(), "Delete", Group.class.getName())));
    }

    @Transactional
    public void delete(Group group){
        if(group.getName().equals("Email")){
            template.send(new EmailMessageCreator(new EmailHistory("79372009578@yandex.ru", "Group name equals to Email. Group has been deleted")));
        }
        repository.delete(group);
        template.send(new MessageCreatorImpl(new History(group.getId().toString(), "Delete", group.getClass().getName())));
    }
}
