/**
 * 
 */
package com.amgen.anemiahub.service;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.amgen.anemiahub.bean.Address;
import com.amgen.anemiahub.bean.CartItem;
import com.amgen.anemiahub.bean.Document;
import com.amgen.anemiahub.bean.Event;
import com.amgen.anemiahub.bean.Registration;

import com.amgen.anemiahub.bean.Download;
import com.amgen.anemiahub.bean.ShoppingCart;

import com.amgen.anemiahub.service.HibernateUtil;
import com.amgen.anemiahub.bean.Category;
import com.amgen.anemiahub.bean.User;

/**
 * @author chaudmee
 *
 */
public class UtilityService {

	public static boolean loginCheck(String username, String password) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx =null;
		try {
			tx=session.beginTransaction();
			User user = new User();
			Query qry =session.createQuery("from User as user where user.uname='"+ username+"' and user.password='"+ password+"'");
			user =  (User) qry.uniqueResult();
			String urname = user.getUname();
			String psword = user.getPassword();
			if(username.equals(urname) && password.equals(psword)){
				return true;
			}
			else{
				System.out.println("not matched...");
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public static User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from User where uname='"+username+"'");
			user = (User)query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			//			if (tx != null) {
			//				tx.rollback();
			//			}
			e.printStackTrace();
		} 
		return user;
	}


	public static List<Category> getCategory() {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		List<Category> category = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query qr = session.createQuery("From Category");
			category = qr.list();
			tx.commit();
		} catch (Exception e) {
			//			if (tx != null) {
			//				tx.rollback();
			//			}
			e.printStackTrace();
		} 
		return category;
	}


	public static void addUser(String fname, String lname, String uname,
			String pword, String email, Long contact, int house, String street,
			String city, String state, String country, Long zip) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			User user = new User();
			user.setFirstName(fname);
			user.setLastName(lname);
			user.setUname(uname);
			user.setPassword(pword);
			user.setEmail(email);
			user.setContactNo(contact);
			user.setHouseNo(house);
			user.setStreet(street);
			user.setCity(city);
			user.setState(state);
			user.setCountry(country);
			user.setZip(zip);
			session.save(user);
			if (!tx.wasCommitted()){
				tx.commit();
			} 
		}catch(HibernateException e){
			e.printStackTrace(); 
		}
		finally{
			session.close();
		}

	}

	public static void addAddress(int house, String street, String city,
			String state, String country, Long zip) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Address address =  new Address();
			User user = new User();
			address.setStreet(street);
			address.setCity(city);
			address.setState(state);
			address.setCountry(country);
			address.setZipCode(zip);
			address.setHouseNo(house);
			Query q =  (Query) session.createQuery("select id From User as user where user.houseNo = '"+ house+ "'");
			Long id = (long) q.uniqueResult();
			user.setId(id);
			address.setUser(user);
			session.save(address);
			if (!tx.wasCommitted()){
				tx.commit();
			} 
		}catch(HibernateException e){
			e.printStackTrace(); 
		}
		finally{
			session.close();
		}


	}


	public static List<Document> getDocument(String category) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		List<Document> document = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query qry = session.createQuery("select id from Category as cat where cat.categoryName = '" + category + "'");
			Long id = (Long) qry.uniqueResult();
			Query q = session.createQuery("From Document as doc where doc.category.id = '"+ id + "'");
			document = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		return document;
	}

	public static void DownloadedDocument(String document, User user,Date date) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		Download download = new Download();
		Document document1 = new Document();
		try {
			tx = session.getTransaction();
			tx.begin();
			Query q=session.createQuery("From Document as doc where doc.documentName = '"+ document + "'");
			document1 = (Document) q.uniqueResult();
			download.setCategory(document1.getCategory());
			download.setDocumentName(document);
			download.setUser(user);
			download.setDocument(document1);
			download.setDate(date);
			session.save(download);
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 

	} 


	public static Document getDocumentId(String document) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		Document docs = new Document();
		try {
			tx = session.getTransaction();
			tx.begin();
			Query q = session.createQuery("From Document as doc where doc.documentName = '" + document + "'");
			docs = (Document) q.uniqueResult();
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return docs;

	}

	public static void addNewItem(User user, ShoppingCart cart, List<Document> list) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		ShoppingCart cart1 = new ShoppingCart();
		try {
			tx = session.getTransaction();
			tx.begin();
			Iterator ite = list.iterator();
			cart1.setUser(user);
			cart1.setTotal(list.size());
			for(int i = 0;i<list.size();i++){
				CartItem item = new CartItem();
				String name =  list.get(i).getDocumentName();
				cart1.setCartItems(list);
				item.setItemName(name);
				item.setQuantity(1);
				item.setShoppingCart(cart1);
				session.save(item);
			}
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		}
	}

	public static List<Event> getEvents(String filter,String month,String year){
		
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session s=factory.getCurrentSession();
		Transaction tx=s.beginTransaction();
		
		List<Event> events=null;
		try{
			if(filter.equals("all")){
				String query="FROM Event e Order By MONTH(e.eventStartDate) ASC";
				Query q=s.createQuery(query);
				events=q.list();
				
			}
			else if(filter.equals("month")){
				
				String query="FROM Event e WHERE (e.eventStartDate LIKE :pattern1 and e.eventStartDate LIKE :pattern2) or (e.eventEndDate LIKE :pattern1 and e.eventEndDate LIKE :pattern2)";
				events=s.createQuery(query)
						.setString("pattern1","%-"+month.trim()+"-%")
						.setString("pattern2", year.trim()+"-%"+"-%")
						.list();
						
			}
			
		}catch(Exception e){
//			if(tx!=null){
//				tx.rollback();
//			}
			e.printStackTrace();
		}finally{
			s.close();
		}
		
		return events;
	}


public static String registerUserToEvent(String eventId,User user,String type){
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		Transaction tx=null;
		String result=null;
		long registration=0;
		
		if(type.equals("register")){
			try{
				tx=session.getTransaction();
				tx.begin();
				Query query=(Query)session.createQuery("FROM Event as event WHERE event.eventId='"+eventId+"'");
				Event events=(Event) query.uniqueResult();
				Category category=events.getCategory();
				String eventName=events.getEventName();
			
				Registration reg=new Registration();
				reg.setCategory(category);
				reg.setEventtName(eventName);
				reg.setDate(new Date());
				reg.setEvent(events);
				reg.setUser(user);
				registration=(long) session.save(reg);
				tx.commit();
				if(registration==0){
					result="failed to register";
				}
				else{
					result="registered";
				}
				
			}catch(Exception e){
//				if (tx != null) {
//					tx.rollback();
//				}
				e.printStackTrace();		
			}finally{
				
			}
		}
		
		else{
			try{
				tx=session.getTransaction();
				tx.begin();
				Query query=(Query)session.createQuery("DELETE Registration as reg WHERE reg.event.eventId='"+eventId+"'and reg.user.id='"+user.getId()+"'");
				registration=query.executeUpdate();
				tx.commit();
				if(registration==0){
					result="failed to unregister";
				}
				else{
					result="unregistered";
				}
				
			}catch(Exception e){
//				if (tx != null) {
//					tx.rollback();
//				}
				e.printStackTrace();		
			}finally{
				
			}
			
		}
		
		
		return result;
	}


	public static List<CartItem> getCartInfo(User user, ShoppingCart cart) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		List<CartItem> items = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			long id = cart.getId();
			Query q = session.createQuery("From CartItem as item where item.shoppingCart.id = '" + id + "'" );
			items =  q.list();
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		}
		return items;
	}

	public static void addDownloadDocument(String document, ShoppingCart cart,
			User user, Date date) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		Download download = new Download();
		Document doc = new Document();
		try {
			tx = session.getTransaction();
			tx.begin();
			download.setDocumentName(document);
			Query q = session.createQuery("From Document as doc where doc.documentName = '" + document + "'");
			doc = (Document) q.uniqueResult();
			Category category  = doc.getCategory();
			long id = doc.getId();
			download.setDocument(doc);
			download.setCategory(category);
			download.setUser(user);
			download.setDate(date);
			session.save(download);
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void addFile(String fname, String filename) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		Document doc = new Document();
		try {
			tx = session.getTransaction();
			tx.begin();
			doc.setDocumentName(fname);
			doc.setFile(filename);
			session.save(doc);
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();

		}	}

	public static File[] getFiles(String folderName) {
		// TODO Auto-generated method stub
		ResourceBundle s=ResourceBundle.getBundle("/cart");
		String path = s.getString("file-upload");
		if(folderName!=null)
			path=path+"\\"+folderName;
		File f = new File(path); 
		File[] files = f.listFiles();
		return files;  
	}

	public static Document getDocumentName(String fileName) {
		// TODO Auto-generated method stub
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx = null;
		Document doc = new Document();
		try {
			tx = session.getTransaction();
			tx.begin();
			Query q= session.createQuery("From Document as doc where doc.file = '" + fileName + "'");
			doc = (Document) q.uniqueResult();
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();

		}
		return doc;	}

}