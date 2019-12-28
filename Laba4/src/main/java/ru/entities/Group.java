package ru.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clazz")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
    @Id
    @XmlElement
    private UUID id;

    @Column
    @NotNull
    @XmlElement
    private String name;

    @Column
    @XmlElement
    private Integer countOfPeople;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group", orphanRemoval = true)
    @JsonIgnore
    @XmlTransient
    private List<Lesson> lessons;

    public Group(){
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

    public Integer getCountOfPeople() {
        return countOfPeople;
    }

    public void setCountOfPeople(Integer countOfPeople) {
        this.countOfPeople = countOfPeople;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
