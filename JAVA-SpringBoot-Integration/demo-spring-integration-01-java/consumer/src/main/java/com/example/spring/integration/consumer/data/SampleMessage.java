package com.example.spring.integration.consumer.data;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@Data
public class SampleMessage {



    @Id
    @Column(name = "message_id", unique = true, nullable = false)
    private String messageID;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "cosumer_name")
    private String cosumerName;

    @Column(name = "message")
    private String message;

}
