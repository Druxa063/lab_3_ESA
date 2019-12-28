package ru.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "history")
public class History implements Serializable {
    private static long serialVersionUID = 1214124124;
    @Id
    private UUID id;

    @Column
    private String changeType;

    @Column
    private String changeEntity;

    @Column
    private String value;

    public History(){
        this.id = UUID.randomUUID();
    }

    public History(String value, String changeType, String changeEntity){
        this.id = UUID.randomUUID();
        this.value = value;
        this.changeType = changeType;
        this.changeEntity = changeEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getChangeEntity() {
        return changeEntity;
    }

    public void setChangeEntity(String changeEntity) {
        this.changeEntity = changeEntity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Changed entity " + changeEntity + " changed value " + value + " changed action" + changeType;
    }
}
