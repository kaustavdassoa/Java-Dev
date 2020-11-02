package com.example.JAVAJPADemo1.domain;

import javax.persistence.*;

@Entity
@Table(name="messages")
public class Messages {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="TEXT")
    private String text;

    public Messages() {}



    public Messages(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", text=" + text + "]";
    }

}
