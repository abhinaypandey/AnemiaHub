/**
 * 
 */
package com.amgen.anemiahub.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.amgen.anemiahub.bean.Address;
import com.amgen.anemiahub.bean.Download;
/**
 * @author chaudmee
 *
 */

@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="Username")
	private String uname;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="ContactNo")
	private long contactNo;
	
	@Column(name="HouseNo")
	private int houseNo;
	
	@Column(name="street")
	private String street;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="zip")
	private Long zip;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private Collection<Download> totalDownloads = new ArrayList<Download>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private Collection<Address> address=new ArrayList<Address>();
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Collection<Registration> registrations=new ArrayList<Registration>();
	
//	@OneToMany(mappedBy= "user", cascade=CascadeType.ALL)
//	private Collection<DocumentOrder> documentOrder=new ArrayList<DocumentOrder>();
	
//	@OneToOne(cascade=CascadeType.ALL)
//	private Cart cart;

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param uname
	 * @param password
	 * @param email
	 * @param contactNo
	 * @param houseNo
	 * @param street
	 * @param city
	 * @param state
	 * @param country
	 * @param zip
	 * @param totalDownloads
	 * @param address
	 * @param document
	 * @param cart
	 */
	public User(long id, String firstName, String lastName, String uname,
			String password, String email, long contactNo, int houseNo,
			String street, String city, String state, String country, Long zip,
			Collection<Download> totalDownloads, Collection<Address> address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.uname = uname;
		this.password = password;
		this.email = email;
		this.contactNo = contactNo;
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.totalDownloads = totalDownloads;
		this.address = address;
	
		//this.cart = cart;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactNo
	 */
	public long getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the houseNo
	 */
	public int getHouseNo() {
		return houseNo;
	}

	/**
	 * @param houseNo the houseNo to set
	 */
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zip
	 */
	public Long getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(Long zip) {
		this.zip = zip;
	}

	/**
	 * @return the totalDownloads
	 */
	public Collection<Download> getTotalDownloads() {
		return totalDownloads;
	}

	/**
	 * @param totalDownloads the totalDownloads to set
	 */
	public void setTotalDownloads(Collection<Download> totalDownloads) {
		this.totalDownloads = totalDownloads;
	}

	/**
	 * @return the address
	 */
	public Collection<Address> getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Collection<Address> address) {
		this.address = address;
	}

	/**
	 * @return the registrations
	 */
	public Collection<Registration> getRegistrations() {
		return registrations;
	}

	/**
	 * @param registrations the registrations to set
	 */
	public void setRegistrations(Collection<Registration> registrations) {
		this.registrations = registrations;
	}

	/**
	 * @return the documentOrder
	 */
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
	 * @return the cart
	 */
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

	
	

}
