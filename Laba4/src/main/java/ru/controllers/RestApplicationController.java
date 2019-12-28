package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.entities.*;
import ru.repositories.HistoryRepository;
import ru.services.GroupService;
import ru.services.LessonService;
import ru.services.TeacherService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@RestController()
public class RestApplicationController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private HistoryRepository historyRepository;

    @RequestMapping(value = "/rest/history", method = RequestMethod.GET)
    public ResponseEntity<List<History>> watchHistory(){
        List<History> histories = historyRepository.findAll();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
    @RequestMapping(value = {"/rest/", "/rest/index"}, method = RequestMethod.GET)
    public String watchIndex(Model model){
        return "index";
    }

    @RequestMapping(value = "/rest/groups", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GroupList> watchGroups(){
        List<Group> groups = groupService.findAll();
        return new ResponseEntity<>(new GroupList(groups), HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/groupsByLesson", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Group> watchGroupsByLesson(@RequestParam("id") String id){
        Group group = groupService.findById(UUID.fromString(id));
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/createGroup", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Group> createGroup(@RequestBody Group group){
        return saveGroup(group);
    }

    private ResponseEntity<Group> saveGroup(Group group){
        boolean saveStatus = groupService.save(group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/deleteGroup", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Group> deleteGroup(@RequestParam("id") String id){
        groupService.delete(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/groupsUpdate", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Group> updateGroup(@RequestBody Group group){
        List<Lesson> lesson = lessonService.findByGroupId(group.getId());
        group.setLessons(lesson);
       if(groupService.save(group)) {
           return new ResponseEntity<>(group, HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @RequestMapping(value = "/rest/lessonsByGroup", method = RequestMethod.GET)
    public ResponseEntity<LessonList> watchLessonsByGroup(@RequestParam("id") String id){
        List<Lesson> lessons = lessonService.findByGroupId(UUID.fromString(id));
        return new ResponseEntity<>(new LessonList(lessons), HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/lessons", method = RequestMethod.GET)
    public ResponseEntity<LessonList> watchLessons(){
        List<Lesson> lessons = lessonService.findAll();
        return new ResponseEntity<>(new LessonList(lessons), HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/lessonsByTeacher", method = RequestMethod.GET)
    public ResponseEntity<LessonList> watchLessonsByTeacher(@RequestParam("id") String id){
        List<Lesson> lessons = lessonService.findByTeacherId(UUID.fromString(id));
        return new ResponseEntity<>(new LessonList(lessons), HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/createLesson", method = RequestMethod.POST)
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson){
        if(checkIfBusy(lesson.getGroup().getId().toString(), lesson.getStartTime().toString())){
            return new ResponseEntity<>(lesson, HttpStatus.BAD_REQUEST);
        }
        if(checkIfBusy(lesson.getTeacher().getId().toString(), lesson.getStartTime().toString())){
            return new ResponseEntity<>(lesson, HttpStatus.BAD_REQUEST);
        }


        return saveLesson(lesson);
    }

    private ResponseEntity<Lesson> saveLesson(Lesson lesson){
        Group group = groupService.findById(lesson.getGroup().getId());
        Teacher teacher = teacherService.findById(lesson.getTeacher().getId());
        lesson.setGroup(group);
        lesson.setTeacher(teacher);
        boolean saveStatus = lessonService.save(lesson);
        if(saveStatus) {
            return new ResponseEntity<>(lesson, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    private boolean checkIfBusy(String id, String startTime){
        if(lessonService.findByGroupId(UUID.fromString(id)).stream()
                .filter(lesson -> {
                    return LocalDateTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME).equals(lesson.getStartTime());
                }).count() > 0){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/rest/lessonsUpdate", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Lesson> updateLesson(@RequestBody Lesson lesson){
        if(checkIfBusy(lesson.getGroup().getId().toString(), lesson.getStartTime().toString())){
            return new ResponseEntity<>(lesson, HttpStatus.BAD_REQUEST);
        }
        if(checkIfBusy(lesson.getTeacher().getId().toString(), lesson.getStartTime().toString())){
            return new ResponseEntity<>(lesson, HttpStatus.BAD_REQUEST);
        }

        return saveLesson(lesson);
    }

    @RequestMapping(value = "/rest/deleteLesson", method = RequestMethod.DELETE)
    public ResponseEntity<Lesson> deleteLesson(@RequestParam("id") String id){
        lessonService.delete(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/rest/teachersByLesson", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Teacher> watchTeachersByLesson(@RequestParam("id") String id){
        Teacher teacher = teacherService.findById(UUID.fromString(id));
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/teachers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TeacherList> watchTeachers(Model model){
        List<Teacher> teachers = teacherService.findAll();
       return new ResponseEntity<>(new TeacherList(teachers), HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/createTeacher", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
       return saveTeacher(teacher);
    }

    private ResponseEntity<Teacher> saveTeacher(Teacher teacher){
        if(teacherService.save(teacher)) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/rest/deleteTeacher", method = RequestMethod.DELETE)
    public ResponseEntity<Teacher> deleteTeacher(@RequestParam("id") String id){
        teacherService.delete(UUID.fromString(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/teachersUpdate", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher){
        List<Lesson> lessons = lessonService.findByTeacherId(teacher.getId());
        teacher.setLessons(lessons);
        return saveTeacher(teacher);
    }
}
