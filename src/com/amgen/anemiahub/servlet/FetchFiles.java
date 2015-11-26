package com.amgen.anemiahub.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import com.amgen.anemiahub.bean.Document;

public class FetchFiles {
	public static File[] getFiles(String fldname) { 
		
		ResourceBundle s=ResourceBundle.getBundle("/cart");
		
		String path = s.getString("file-upload");
		if(fldname!=null)
		path=path+"\\"+fldname;
		
		File f = new File(path); // current directory
	
		File[] files = f.listFiles();
		System.out.println("root path------->"+f.getPath());
		System.out.println("starting file list length------->"+f.length());
		return files;
	}
}
