package com.rmznprhn.myApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@Entity
@Table(name = "lectures")
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;
    private Integer getTeacherId(){
        return teacher.getId();
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToMany
    @JoinTable(name = "user_lectures",
    joinColumns = {@JoinColumn(name  ="lecture_id",referencedColumnName = "id")}
    ,inverseJoinColumns ={@JoinColumn(name = "user_id",referencedColumnName = "id")})
    private List<User> lectures;
}
