package com.uniovi.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bid {
	
	@Id 
	@GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	private Date date;
	private double price;
	private boolean specialBid;
	
	@Enumerated(EnumType.STRING)
	private BidStatus status;
	
	@ManyToOne
	@JoinColumn(name = "user_id") //la relacion sera a traves d este campo
	private User user;

	/**
	 * Usuario que ha comprado el objeto, en caso de que no haya comprado nadie,
	 * sera null.
	 */
	@ManyToOne
	@JoinColumn(name = "buyerUser_id")
	private User buyerUser;
	
	@OneToMany(mappedBy = "bid", cascade = CascadeType.ALL)
	private Set<Conversation> conversations;
	
	public Bid() { }

	public Bid(Long id, String title, String description, double price, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = new Date();
		this.price = price;
		this.specialBid = false;
		this.user = user;
		this.status = BidStatus.ACTIVED;
		this.buyerUser = null;
	}

	public Bid(String title, String description, double price, User user) {
		super();
		this.title = title;
		this.description = description;
		this.date = new Date();
		this.price = price;
		this.specialBid = false;
		this.user = user;
		this.status = BidStatus.ACTIVED;
		this.buyerUser = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isSpecialBid() {
		return specialBid;
	}

	public void setSpecialBid(boolean specialBid) {
		this.specialBid = specialBid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(user != null)
			this.status = BidStatus.BUYED;
		this.user = user;
	}

	public User getBuyerUser() {
		return buyerUser;
	}

	public void setBuyerUser(User buyerUser) {
		if(buyerUser != null)
			this.status = BidStatus.BUYED;
		this.buyerUser = buyerUser;
	}

	public BidStatus getStatus() {
		return status;
	}

	public void setStatus(BidStatus status) {
		this.status = status;
	}

	/**
	 * Devuelve true si esta oferta fue comprada.
	 * @return
	 */
	public boolean isBuyed() {
		return status.equals(BidStatus.BUYED);
	}
	
	public Set<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", price="
				+ price + ", specialBid=" + specialBid + ", user=" + user + "]";
	}
	
	/**
	 * Devuelve el dinero con dos decimales para tener un formato acorde con la UI.
	 * 
	 * @return El string del dinero formateado.
	 */
	public String getPriceFormatted() {
		return String.format("%.2f", price) + " €";
	}
}
