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
	@JoinColumn(name = "seller_id")
	private User sellerUser;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private User buyerUser;
	
	@OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
	private Set<Message> messages;
	
	@ManyToOne
	@JoinColumn(name = "bid_id")
	private Bid bid;
	
	public Conversation() {
		
	}

	public Conversation(User sellerUser, User buyerUser, Bid bid) {
		super();
		this.sellerUser = sellerUser;
		this.buyerUser = buyerUser;
		this.bid = bid;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSellerUser() {
		return sellerUser;
	}

	public void setSellerUser(User sellerUser) {
		this.sellerUser = sellerUser;
	}

	public User getBuyerUser() {
		return buyerUser;
	}

	public void setBuyerUser(User buyerUser) {
		this.buyerUser = buyerUser;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "Conversation [id=" + id + ", sellerUser=" + sellerUser + ", buyerUser=" + buyerUser + ", messages="
				+ messages + "]";
	}	
}
