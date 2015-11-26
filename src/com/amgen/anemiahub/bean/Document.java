/**
 * 
 */
package com.amgen.anemiahub.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author chaudmee
 *
 */
@Entity
@Table(name="file")
public class Document implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="DocumentName")
	private String documentName;
	
//	@Column(name="quantity")
//	private int quantity;
	
    @Column(name="file")
    private String file;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy="document",cascade = CascadeType.ALL)
	private Collection<Download> download =  new ArrayList<Download>();
	
//	@OneToMany(mappedBy="document",cascade = CascadeType.ALL)
//	private Collection<DocumentOrder> documentOrder =  new ArrayList<DocumentOrder>();
//	
	@ManyToOne
	private Event event;
	
//	@ManyToOne
//	private Cart cart;
	
    @ManyToOne
    private ShoppingCart shoppingCart;

	/**
	 * 
	 */
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param documentName
	 * @param quantity
	 * @param user
	 * @param category
	 * @param download
	 */
	public Document(long id, String documentName,  
			Category category, Collection<Download> download, Event event, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.documentName = documentName;
		//this.quantity = quantity;
		this.category = category;
		this.download = download;
		this.event = event;
		this.shoppingCart = shoppingCart;
	}

	public Document(String documentName,Category category,User user
			) {
		// TODO Auto-generated constructor stub
		this.documentName = documentName;
	//	this.quantity = quantity;
		this.category = category;
		
		
	}

	
	public Document(Document docu) {
		// TODO Auto-generated constructor stub
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
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the download
	 */
	public Collection<Download> getDownload() {
		return download;
	}

	/**
	 * @param download the download to set
	 */
	public void setDownload(Collection<Download> download) {
		this.download = download;
	}

//	/**
//	 * @return the documentOrder
//	 */
//	public Collection<DocumentOrder> getDocumentOrder() {
//		return documentOrder;
//	}
//
//	/**
//	 * @param documentOrder the documentOrder to set
//	 */
//	public void setDocumentOrder(Collection<DocumentOrder> documentOrder) {
//		this.documentOrder = documentOrder;
//	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

//	/**
//	 * @return the cart
//	 */
//	public Cart getCart() {
//		return cart;
//	}
//
//	/**
//	 * @param cart the cart to set
//	 */
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}

	/**
	 * @return the shoppingCart
	 */
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * @param shoppingCart the shoppingCart to set
	 */
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((documentName == null) ? 0 : documentName.hashCode());
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
		Document other = (Document) obj;
		if (documentName == null) {
			if (other.documentName != null)
				return false;
		} else if (!documentName.equals(other.documentName))
			return false;
		return true;
	}

	
	
	
}
