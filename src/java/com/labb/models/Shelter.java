package com.labb.models;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by localadmin on 8/10/16.
 */
@Entity
@Table(name = "shelters")
@Access(AccessType.PROPERTY)
public class Shelter {

    private String name;
    private int id;
    private Date opened;

    @Basic
    @Column(name = "opened", nullable = false)
    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    public Shelter(String name, Date inOpened) {
        this.opened = inOpened;
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
