package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {

	@Id 
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "bid_id")
	private Bid bid;
	
	@ManyToOne
	@JoinColumn(name = "intersetedUser_id")
	private User intersetedUser;
	
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private Set<Message> messages;
	
	public Conversation() {
		
	}

	public Conversation(User intersetedUser, Bid bid) {
		super();
		this.intersetedUser = intersetedUser;
		this.bid = bid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getIntersetedUser() {
		return intersetedUser;
	}

	public void setIntersetedUser(User intersetedUser) {
		this.intersetedUser = intersetedUser;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "Conversation [id=" + id + ", bid=" + bid + ", intersetedUser=" + intersetedUser + ", messages="
				+ messages + "]";
	}
}
