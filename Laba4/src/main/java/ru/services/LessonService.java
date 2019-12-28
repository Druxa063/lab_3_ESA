package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.entities.EmailHistory;
import ru.entities.History;
import ru.entities.Lesson;
import ru.repositories.LessonRepository;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;
import java.util.UUID;

@Service
public class LessonService {
    @Autowired
    private LessonRepository repository;

    @Autowired
    private JmsTemplate template;

    public List<Lesson> findAll(){
        return repository.findAll();
    }

    public Lesson findById(UUID id){
        return repository.findById(id).get();
    }

    public List<Lesson> findByGroupId(UUID id){
        return repository.findAllByGroupId(id);
    }

    public List<Lesson> findByTeacherId(UUID id){
        return repository.findAllByTeacherId(id);
    }

    @Transactional
    public boolean save(Lesson lesson){
        if(lesson.getName().equals("Email")){
            template.send(new EmailMessageCreator(new EmailHistory("79372009578@yandex.ru", "Name equals to Email. Lesson has been inserted.")));
        }
        try{
            repository.save(lesson);
            template.send(new MessageCreatorImpl(new History(lesson.getId().toString(), "Insert", lesson.getClass().getName())));
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
            template.send(new EmailMessageCreator(new EmailHistory("79372009578@yandex.ru", "Name equals to Email. Lesson has been deleted.")));
        }
        repository.deleteById(id);
        template.send(new MessageCreatorImpl(new History(id.toString(), "Delete", Lesson.class.getName())));
    }
}
