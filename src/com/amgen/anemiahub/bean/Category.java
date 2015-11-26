/**
 * 
 */
package com.amgen.anemiahub.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author chaudmee
 *
 */
@Entity
@Table(name="Category")
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="CategoryName")
	private String categoryName;
	
	@OneToMany(mappedBy= "category", cascade=CascadeType.ALL)
	private Collection<Document> document = new ArrayList<Document>();
	
	@OneToMany(mappedBy="category" , cascade =CascadeType.ALL)
	private Collection<Download> download = new ArrayList<Download>();
	
//	@OneToMany(mappedBy="category" , cascade =CascadeType.ALL)
//	private Collection<DocumentOrder> docOrder = new ArrayList<DocumentOrder>();

	/**
	 * 
	 */
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param categoryName
	 * @param document
	 * @param download
	 */
	public Category(long id, String categoryName,
			Collection<Document> document,
			Collection<Download> download) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.document = document;
		this.download = download;
		
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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	/**
	 * @return the docOrder
	 */
//	public Collection<DocumentOrder> getDocOrder() {
//		return docOrder;
//	}
//
//	/**
//	 * @param docOrder the docOrder to set
//	 */
//	public void setDocOrder(Collection<DocumentOrder> docOrder) {
//		this.docOrder = docOrder;
//	}

}
