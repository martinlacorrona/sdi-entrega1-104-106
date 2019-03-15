<<<<<<< HEAD
package com.uniovi.entities;

import javax.persistence.*;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String lastName;
    private String role;
    private Double money;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Bid> bids;

    @OneToMany(mappedBy = "buyerUser")
    private Set<Bid> buyedBids;

    @OneToMany(mappedBy = "interestedUser", cascade = CascadeType.ALL)
    private Set<Conversation> conversationBuyer;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Set<Message> sentMessages;

    private String password;
    @Transient // propiedad que no se almacena en la tabla.
    private String passwordConfirm;

    public User() {

    }

    /**
     * Borrarmos todos los setBuyedUser ya que si no se fastidiara la persistencia.
     */
    @PreRemove
    private void preRemove() {
	for (Bid b : buyedBids) {
	    b.setBuyerUser(null);
	}
    }

    public User(String email, String name, String lastName) {
	super();
	this.email = email;
	this.name = name;
	this.lastName = lastName;
	this.money = 100.0;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getFullName() {
	return this.name + " " + this.lastName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getPasswordConfirm() {
	return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
	this.passwordConfirm = passwordConfirm;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public Set<Bid> getBids() {
	return bids;
    }

    public void setBids(Set<Bid> bids) {
	this.bids = bids;
    }

    public Double getMoney() {
	return money;
    }

    public boolean isPurschable(Double precio) {
	if (this.money - precio >= 0) {
	    return true;
	}
	return false;
    }

    public boolean isOutstanding() {
	if (this.money - 20 >= 0) {
	    return true;
	}
	return false;
    }

    public void setMoney(Double money) {
	this.money = money;

    }

    public Set<Bid> getBuyedBids() {
	return buyedBids;
    }

    public void setBuyedBids(Set<Bid> buyedBids) {
	this.buyedBids = buyedBids;
    }

    public Set<Conversation> getConversationBuyer() {
	return conversationBuyer;
    }

    public void setConversationBuyer(Set<Conversation> conversationBuyer) {
	this.conversationBuyer = conversationBuyer;
    }

    public Set<Message> getSentMessages() {
	return sentMessages;
    }

    public void setSentMessages(Set<Message> sentMessages) {
	this.sentMessages = sentMessages;
    }

    /**
     * Devuelve el dinero con dos decimales para tener un formato acorde con la UI.
     * 
     * @return El string del dinero formateado.
     */
    public String getMoneyFormatted() {
	return String.format("%.2f", money) + " €";
    }
=======
<<<<<<< HEAD
package com.uniovi.entities;

import javax.persistence.*;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String dni;
	private String name;
	private String lastName;
	private String role;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Mark> marks;
	
	

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;

	public User(String dni, String name, String lastName) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}

	public Set<Mark> getMarks() {
		return marks;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
||||||| merged common ancestors
package com.uniovi.entities;

import javax.persistence.*;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Bid> bids;
	
	@OneToMany(mappedBy = "buyerUser")
	private Set<Bid> buyedBids;
	

	private String password;
	@Transient // propiedad que no se almacena en la tabla.
	private String passwordConfirm;

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
=======
package com.uniovi.entities;

import javax.persistence.*;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private Double money;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Bid> bids;

	@OneToMany(mappedBy = "buyerUser")
	private Set<Bid> buyedBids;

	@OneToMany(mappedBy = "interestedUser", cascade = CascadeType.ALL)
	private Set<Conversation> conversationBuyer;

	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private Set<Message> sentMessages;

	private String password;
	@Transient // propiedad que no se almacena en la tabla.
	private String passwordConfirm;

	public User() {

	}

	/**
	 * Borrarmos todos los setBuyedUser ya que si no se fastidiara la persistencia.
	 */
	@PreRemove
	private void preRemove() {
		for (Bid b : buyedBids) {
			b.setBuyerUser(null);
		}
	}

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.money = 100.0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Double getMoney() {
		return money;
	}

	public boolean isPurschable(Double precio) {
		if (this.money - precio >= 0) {
			return true;
		}
		return false;
	}

	public void setMoney(Double money) {
		this.money = money;

	}

	public Set<Bid> getBuyedBids() {
		return buyedBids;
	}

	public void setBuyedBids(Set<Bid> buyedBids) {
		this.buyedBids = buyedBids;
	}

	public Set<Conversation> getConversationBuyer() {
		return conversationBuyer;
	}

	public void setConversationBuyer(Set<Conversation> conversationBuyer) {
		this.conversationBuyer = conversationBuyer;
	}

	public Set<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(Set<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	/**
	 * Devuelve el dinero con dos decimales para tener un formato acorde con la UI.
	 * 
	 * @return El string del dinero formateado.
	 */
	public String getMoneyFormatted() {
		return String.format("%.2f", money) + " €";
	}
>>>>>>> develop
>>>>>>> refs/remotes/origin/master
}