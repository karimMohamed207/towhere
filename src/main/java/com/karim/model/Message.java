package com.karim.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Message {

    //----------------------- Attributes ---------------------------

    @Id     //id
    @GeneratedValue(strategy = GenerationType.AUTO)     //generate the value of id
    private long id ;   // id

    @NotEmpty       //value should be not empty
    private String username ;       //username

    @NotEmpty   //value should be not empty
    @Email  // value should be email
    private String email ;      //email

    @NotEmpty   //value should be not empty
    @Lob        //take long string
    private String message ;    //message
    private Date date ;     //date

    //------------------------- Constructor ----------------------------
    public Message() {
    }

    public Message(String username , String email , String message){
        this.username = username ;
        this.email = email ;
        this.message = message ;
        this.date = new Date();
    }

    //-------------------- Setter And Getter ------------------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //------------------- toString ------------------------
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

}
