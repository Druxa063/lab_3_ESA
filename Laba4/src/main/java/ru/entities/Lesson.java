package ru.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "lesson")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Lesson {
    static class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>{

        @Override
        public LocalDateTime unmarshal(String s) throws Exception {
            return LocalDateTime.parse(s);
        }

        @Override
        public String marshal(LocalDateTime localDateTime) throws Exception {
            String[] time = localDateTime.toString().split("T");
            String[] minhour = time[1].split(":");
            return time[0] + " " + minhour[0] + " " + minhour[1];
        }
    }
    @Id
    @XmlElement
    private UUID id;

    @Column
    @XmlElement
    private String name;

    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = Lesson.LocalDateTimeAdapter.class)
    @Column
    @XmlElement
    private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    @XmlElement
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    @XmlElement
    private Teacher teacher;

    public Lesson(){
        this.id = UUID.randomUUID();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
