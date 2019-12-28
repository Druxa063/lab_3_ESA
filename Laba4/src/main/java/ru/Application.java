package ru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;
import ru.entities.Group;
import ru.entities.Lesson;
import ru.entities.Teacher;
import ru.repositories.GroupRepository;
import ru.repositories.LessonRepository;
import ru.repositories.TeacherRepository;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
       ConfigurableApplicationContext context = SpringApplication.run(Application.class);
       UUID groupId = UUID.fromString("858afe27-1982-45ad-8488-1eafe00155fc");
       UUID groupId1 = UUID.fromString("558afe27-1982-45ad-8488-1eafe00155fc");
       UUID lessonId = UUID.fromString("888afe27-1982-45ad-8488-1eafe00155fc");
       UUID teacherId = UUID.fromString("554afe27-1982-45ad-8488-1eafe00155fc");
       GroupRepository groupRepository = context.getBean(GroupRepository.class);

       LessonRepository lessonRepository = context.getBean(LessonRepository.class);
       TeacherRepository teacherRepository = context.getBean(TeacherRepository.class);

       Group group = createGroup(groupRepository, "Name", groupId);
       Group anotherGroup = createGroup(groupRepository, "Another group", groupId1);
       Teacher teacher = createTeacher(teacherRepository, "first name", "last name", "position", teacherId);
       Lesson lesson = createLesson(lessonRepository, "name", LocalDateTime.now().plusMonths(1), group, teacher, lessonId);


    }



    private static Group createGroup(GroupRepository repository, String name, UUID id){
       Group group = new Group();
       group.setName(name);
       group.setId(id);
       repository.save(group);
       return group;
    }

    private static Teacher createTeacher(TeacherRepository repository, String firstName, String lastName, String position, UUID id){
       Teacher teacher = new Teacher();
       teacher.setId(id);
       teacher.setFirstName(firstName);
       teacher.setLastName(lastName);
       teacher.setPosition(position);
       repository.save(teacher);
       return teacher;
    }

    private static Lesson createLesson(LessonRepository repository, String name, LocalDateTime startTime, Group group, Teacher teacher, UUID id){
       Lesson lesson = new Lesson();
       lesson.setId(id);
       lesson.setName(name);
       lesson.setStartTime(startTime);
       lesson.setGroup(group);
       lesson.setTeacher(teacher);
       repository.save(lesson);
       return lesson;
    }

}
