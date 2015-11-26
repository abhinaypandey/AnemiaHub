/**
* @author pandabhi
 *
 */
package com.amgen.anemiahub.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionOfElements;

@Entity
@Table(name="Event")
public class Event implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="eventId")
	private long eventId;
	
	@Column(name="eventName")
	private String eventName;
	
	@Column(name="eventDesc")
	private String eventDescription;
	
	@Column(name="eventStartDate")
	private Date eventStartDate;
	
	@Column(name="eventEndDate")
	private Date eventEndDate;
	
	@Column(name="location")
	private String location;
	
	@OneToMany(mappedBy="event",cascade=CascadeType.ALL)
	private Collection<Registration> totalRegistrations = new ArrayList<Registration>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	
	@OneToMany(mappedBy="event",cascade = CascadeType.ALL)
	private Collection<Document> document=new ArrayList<Document>();	
	
	public Event() {
		super();
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventDescription
	 * @param eventStartDate
	 * @param eventEndDate
	 * @param location
	 * @param user
	 * @param catergory
	 * @param totalRegistrations
	 */
	
	public Event(long eventId, String eventName, String eventDescription,
			Date eventStartDate, Date eventEndDate, String location,
			Collection<Registration> totalRegistrations, Category category,Collection<Document> document) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.location = location;
		this.totalRegistrations = totalRegistrations;
		this.category = category;
		this.document = document;
	}
	
	/**
	 * 
	 * @return id
	 */
	public long getEventId() {
		return eventId;
	}
	
	/**
	 * 
	 * @param eventId set event id
	 */
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	/**
	 * 
	 * @return eventName
	 */
	public String getEventName() {
		return eventName;
	}
	
	/**
	 * 
	 * @param eventName set eventName
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	/**
	 * 
	 * @return event description
	 */
	public String getEventDescription() {
		return eventDescription;
	}
	
	/**
	 * 
	 * @param eventDescription set eventDescription
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	/**
	 * 
	 * @return event start date
	 */
	public Date getEventStartDate() {
		return eventStartDate;
	}
	
	/**
	 * 
	 * @param eventStartDate set eventStartDate
	 */
	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	
	/**
	 * 
	 * @return event end date
	 */
	
	public Date getEventEndDate() {
		return eventEndDate;
	}
	
	/**
	 * 
	 * @param eventEndDate set eventEndDate
	 */
	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	
	/**
	 * 
	 * @return event location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * 
	 * @param location set event location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * 
	 * @return categories associated with this event
	 */
	public Category getCategory() {
		return category;
	}
	
	/**
	 * 
	 * @param categoryId set categories associated with this event
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * 
	 * @return total no of registered users with this event 
	 */
	public Collection<Registration> getTotalRegistrations() {
		return totalRegistrations;
	}

	/**
	 * 
	 * @param totalRegistrations set users registered with this event
	 */
	public void setTotalRegistrations(Collection<Registration> totalRegistrations) {
		this.totalRegistrations = totalRegistrations;
	}

	/**
	 * @return the document
	 */
	public Collection<Document> getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Collection<Document> document) {
		this.document = document;
	}


	
}
