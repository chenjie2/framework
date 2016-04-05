package com.chenjie.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="users")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public User(){
        super();
    }

    @Id
//    @GeneratedValue(generator = "system-uuid")   
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")   //ָ������������  
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private String id;
    
    @Column(name="user_name",length=32)
    private String name;
    
    @Column(name="age")
    private Integer age;
    
    @Column(name="nice_name",length=32)
    private String nice_name;

    @Override
    public String toString() {
        return "User [id=" + id + ", user_name=" + name + ", age=" + age
                + ", nice_name=" + nice_name + "]";
    }

    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final Integer getAge() {
        return age;
    }

    public final void setAge(Integer age) {
        this.age = age;
    }

    public final String getNice_name() {
        return nice_name;
    }

    public final void setNice_name(String nice_name) {
        this.nice_name = nice_name;
    }

}