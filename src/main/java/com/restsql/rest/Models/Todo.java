package com.restsql.rest.Models;

import jakarta.persistence.*;

@Entity
@Table(name="todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String description;
    @Column
    private Boolean is_done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_done() {
        return is_done;
    }

    public void setIs_done(Boolean is_done) {
        this.is_done = is_done;
    }
}