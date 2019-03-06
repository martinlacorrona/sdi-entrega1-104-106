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
	@JoinColumn(name = "interestedUser")
	private User interestedUser;
	
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private Set<Message> messages;
	
	public Conversation() {
		
	}

	public Conversation(User intersetedUser, Bid bid) {
		super();
		this.interestedUser = intersetedUser;
		this.bid = bid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getInterestedUser() {
		return interestedUser;
	}

	public void setInterestedUser(User interestedUser) {
		this.interestedUser = interestedUser;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "Conversation [id=" + id + ", bid=" + bid + ", intersetedUser=" + interestedUser + ", messages="
				+ messages + "]";
	}
}
