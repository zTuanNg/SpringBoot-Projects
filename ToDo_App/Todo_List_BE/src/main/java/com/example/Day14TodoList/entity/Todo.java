package com.example.Day14TodoList.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String title;

    @Column(name = "status")
    private Boolean status;



    // if want to set status default is 'false'
        // C1 ->  private Boolean status = false
        // C2 -> columnDefination ben trong @Column
        // C3 -> Su dung lifecycle

    // Vi du C3:
    @PrePersist
    public void prePersist() {
        if(status == null){
            status = false;
        }

    }

    public Todo(String name){
        this.title = name;
    }

    public Todo(String name, Boolean status){
        this(name);
        this.status = status;

    }

}
