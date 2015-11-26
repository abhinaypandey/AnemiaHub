/**
 * 
 */
package com.amgen.anemiahub.bean;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.amgen.anemiahub.bean.Category;
import com.amgen.anemiahub.bean.Document;
import com.amgen.anemiahub.bean.User;

/**
 * @author chaudmee
 *
 */
@Entity
@Table(name="Download")
public class Download {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="DocumentName")
	private String documentName;
	
	@Column(name="time")
	private Date date;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Document document;

	/**
	 * 
	 */
	public Download() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param documentName
	 * @param quantity
	 * @param user
	 * @param category
	 * @param document
	 */
	public Download(long id, String documentName, Date date, User user,
			Category category, Document document) {
		super();
		this.id = id;
		this.documentName = documentName;
		this.date = date;
		this.user = user;
		this.category = category;
		this.document = document;
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
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
