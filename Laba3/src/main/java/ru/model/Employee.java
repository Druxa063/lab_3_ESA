package ru.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity()
@Table(name = "emp")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "empno")
    @XmlElement
    private Integer empno;

    @Column(name = "ename")
    @XmlElement
    private String ename;

    @ManyToOne()
    @JoinColumn(name = "deptno")
    @XmlTransient
    private Department dept;

    public Employee() {
    }

    public Employee(Integer empno, String ename, Department dept) {
        this.empno = empno;
        this.ename = ename;
        this.dept = dept;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
