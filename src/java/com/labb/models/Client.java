package com.labb.models;

import javax.persistence.*;


/**
 * Created by localadmin on 8/10/16.
 */
@Entity
@Table(name = "clients")
@Access(AccessType.PROPERTY)
public class Client {

    private String name;
    private int id;

    public Client(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "name", unique = true, nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
