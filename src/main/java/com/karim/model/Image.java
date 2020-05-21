package com.karim.model;

import javax.persistence.*;

@Entity
public class Image {

//-------------------------------- Attributes -------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String type;

    @Column(length = 473186)
    private byte[] picBytes;

//----------------------- Constructors ------------------------
    public Image() {
    }

    public Image(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picBytes = picByte;
    }

//--------------------------- Setter And Getter -----------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicBytes() {
        return picBytes;
    }

    public void setPicBytes(byte[] picBytes) {
        this.picBytes = picBytes;
    }
}