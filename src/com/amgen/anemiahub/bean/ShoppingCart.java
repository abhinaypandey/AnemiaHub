/**
 * 
 */
package com.amgen.anemiahub.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;
/**
 * @author chaudmee
 *
 */
@Entity
@Table(name="cart_user")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@OneToMany(mappedBy="shoppingCart",cascade =CascadeType.ALL)
	private Collection<Document> cartItems ;
	
	@OneToOne
	private User user;
	
	@Column(name="totalItems")
	private int total;

	/**
	 * 
	 */
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param cartItems
	 * @param user
	 * @param total
	 */
	public ShoppingCart(long id, Collection<Document> cartItems, User user,
			int total) {
		super();
		this.id = id;
        cartItems = new ArrayList<Document>();
		this.user = user;
		this.total = total;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the cartItems
	 */
	public Collection<Document> getCartItems() {
		return cartItems;
	}

	/**
	 * @param it the cartItems to set
	 */
	public void setCartItems(Collection<Document> it) {
		this.cartItems = it;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cartItems == null) ? 0 : cartItems.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		if (cartItems == null) {
			if (other.cartItems != null)
				return false;
		} else if (!cartItems.equals(other.cartItems))
			return false;
		return true;
	}
	
	
	

}
