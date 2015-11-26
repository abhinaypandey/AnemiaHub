package com.amgen.anemiahub.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	static SessionFactory factory;

	static{
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg = (AnnotationConfiguration) cfg.configure();
		factory = cfg.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory(){
		return factory;

	}
	public static void closeSession() {
		// TODO Auto-generated method stub

	}




}
