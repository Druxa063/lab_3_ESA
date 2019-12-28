package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.entities.EmailHistory;
import ru.entities.History;
import ru.entities.Teacher;
import ru.repositories.TeacherRepository;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private JmsTemplate template;

    public List<Teacher> findAll(){
        return repository.findAll();
    }

    public Teacher findById(UUID id){
        return repository.findById(id).get();
    }

    @Transactional
    public boolean save(Teacher teacher){
        if(teacher.getFirstName().equals("Sergei")){
            template.send(new EmailMessageCreator(new EmailHistory("79372009578@yandex.ru", "Name equals to Sergei. Teacher has been inserted.")));
        }
        try{
            repository.save(teacher);
            template.send(new MessageCreatorImpl(new History(teacher.getId().toString(), "Insert", teacher.getClass().getName())));
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public void delete(UUID id){
        if(repository.findById(id).get().getFirstName().equals("Sergei")){
            template.send(new EmailMessageCreator(new EmailHistory("79372009578@yandex.ru", "Name equals to Sergei. Teacher has been deleted.")));
        }
        repository.deleteById(id);
        template.send(new MessageCreatorImpl(new History(id.toString(), "Delete", Teacher.class.getName())));
    }
}
