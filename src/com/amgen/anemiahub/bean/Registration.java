/**
 * @author pandabhi
 */
package com.amgen.anemiahub.bean;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.amgen.anemiahub.bean.Category;
import com.amgen.anemiahub.bean.Document;
import com.amgen.anemiahub.bean.User;

@Entity
@Table(name="Registration")
public class Registration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="EventName")
	private String eventName;
	
	@Column(name="time")
	private Date date;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Event event;

	/**
	 * 
	 */
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param eventName
	 * @param user
	 * @param category
	 * @param event
	 */
	public Registration(long id, String eventName, Date date, User user,
			Category category, Event event) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.date = date;
		this.user = user;
		this.category = category;
		this.event = event;
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
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setEventtName(String eventName) {
		this.eventName = eventName;
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
	public Event getEvent() {
		return event;
	}

	/**
	 * @param document the document to set
	 */
	public void setEvent(Event event) {
		this.event = event;
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

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	

}
