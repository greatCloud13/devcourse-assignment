package com.todo.entity;

import com.todo.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TODO")
public class Todo extends BaseIdAndTime {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean isCompleted;

    public Todo(String title){
        this.title = title;
        this.isCompleted = false;
    }

    public void setCompleted(){
        this.isCompleted = true;
    }

    public void setIncomplete(){
        this.isCompleted = false;
    }

    public void setTitle(String title){
        this.title = title;
    }

}
