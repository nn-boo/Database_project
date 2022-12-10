package ru.nnboo.NNBOO.entity;

import javax.persistence.*;

@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;
    private String empName;
    private String post;
    private Integer salary;

    public Worker() {
    }

    public Worker(String empName, String post, Integer salary) {
        this.empName = empName;
        this.post = post;
        this.salary = salary;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

}
