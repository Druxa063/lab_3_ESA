package ru.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "emailhistory")
public class EmailHistory implements Serializable {
    @Id
    private UUID id;

    @Column
    private String email;

    @Column
    private String condition;

    public EmailHistory(){
        this.id = UUID.randomUUID();
    }

    public EmailHistory(String email, String condition){
        this.id = UUID.randomUUID();
        this.email = email;
        this.condition = condition;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
