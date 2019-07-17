package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;


@Entity
@Table(name="todo")
public class Todo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="content")
    private String content;
    @Column(name="dead_line")
    private String deadLine;
    @Column(name="created")
    private String created;
    @Column(name="is_finish")
    private boolean isFinish;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(boolean finish) {
        isFinish = finish;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id  + "]";
    }
}