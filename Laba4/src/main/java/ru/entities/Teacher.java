package ru.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teacher")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Teacher {
    @Id
    @XmlElement
    private UUID id;

    @Column
    @XmlElement
    private String firstName;

    @Column
    @XmlElement
    private String lastName;

    @Column
    @XmlElement
    private String position;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher", orphanRemoval = true)
    @XmlTransient
    @JsonIgnore
    private List<Lesson> lessons;

    public Teacher(){
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
