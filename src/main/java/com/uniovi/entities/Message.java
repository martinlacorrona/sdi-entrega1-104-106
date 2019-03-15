package com.uniovi.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    private Date date;

    private String message;

    public Message() {

    }

    public Message(Conversation conversation, User sender, Date date, String message) {
	super();
	this.conversation = conversation;
	this.sender = sender;
	this.date = date;
	this.message = message;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Conversation getConversation() {
	return conversation;
    }

    public void setConversation(Conversation conversation) {
	this.conversation = conversation;
    }

    public User getSender() {
	return sender;
    }

    public void setSender(User sender) {
	this.sender = sender;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getDateFormatted() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	return dateFormat.format(cal.getTime());
    }

    @Override
    public String toString() {
	return "Message [id=" + id + ", conversation=" + conversation + ", sender=" + sender + ", date=" + date + "]";
    }
}
