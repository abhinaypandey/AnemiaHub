package com.amgen.anemiahub.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class NewItem
 */
@WebServlet("/NewItem")
public class NewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int maxFileSize = 50000000 * 1024;
	private File file ;
	private boolean isMultipart;
	private String filePath;
    private int maxMemSize = 4000 * 1024;
    private ServletFileUpload uploader = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewItem() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init( ){
		// Get the file location where it would be stored.
		ResourceBundle s=ResourceBundle.getBundle("/cart");
		filePath = s.getString("file-upload");
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
	    File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
	    fileFactory.setRepository(filesDir);
	    this.uploader = new ServletFileUpload(fileFactory);

		//getServletContext().getInitParameter("file-upload"); 
	}

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName = request.getParameter("fileName");
        if(fileName == null || fileName.equals("")){
            throw new ServletException("File Name can't be null or empty");
        }
        System.out.println("filepath....+++++++"  + filePath);
        File file = new File(filePath+File.separator+fileName);
        if(!file.exists()){
            throw new ServletException("File doesn't exists on server.");
        }
        if(file.isDirectory()) {
     	   response.sendRedirect("index1.jsp?msg="+fileName);
        }
        System.out.println("File location on server::"+file.getAbsolutePath());
        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());
        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ServletOutputStream os = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read=0;
        while((read = fis.read(bufferData))!= -1){
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        System.out.println("File downloaded at client successfully");
    }
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter( );
		if( !isMultipart ){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");  
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>"); 
			out.println("</body>");
			out.println("</html>");
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("c:\\temp"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax( maxFileSize );
		String fname=null;
		String fileName =null;
		try{ 
		
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			while ( i.hasNext () ) 
			{
				FileItem fi = (FileItem)i.next();
				if ( !fi.isFormField () )	
				{
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					
					 fileName = fi.getName();
					System.out.println("filename is,..." + fileName);
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// Write the file
					if( fileName.lastIndexOf("\\") >= 0 ){
						file = new File( filePath + 
								fileName.substring( fileName.lastIndexOf("\\"))) ;
					}else{
						file = new File( filePath + 
								fileName.substring(fileName.lastIndexOf("\\")+1)) ;
					}
					fi.write( file ) ;
					fileName =fileName.substring(fileName.lastIndexOf("\\") + 1);
					System.out.println("Uploaded Filename: " + fileName+ "<br>");
				
				}else{
				 fname = (String)fi.getString();
					System.out.println("field name....+++++++++++++" + fname);
				}
			}
			UtilityService.addFile(fname, fileName);
			//	      out.println("</body>");
			//	      out.println("</html>");
			response.sendRedirect("confirmation.jsp?msg=successup");
		}catch(Exception ex) {
			response.sendRedirect("confirmation.jsp?msg=error");
			System.out.println(ex);
		}
	}


/*
		try{ 
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			Iterator it = fileItems.iterator();
			while ( it.hasNext () ) 
			{
				FileItem fi = (FileItem)it.next();
				if ( fi.isFormField () )	
				{
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
//					 fileName = fi.getName();
//					//if(fi.isFormField()){ 
//  			        fname = (String)fi.getString(); 
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					FileItem fileitem = (FileItem)it.next();
					if(fileitem.isFormField()){ 
					//category =(String)fileitem.getString(); 
							fname = (String)fileitem.getString(); 
					}
					else{
					fileName = fileitem.getName();
						int index=fileName.lastIndexOf("\\");
						fileName=(fileName.substring(index +1));
					}
					System.out.println("nj---" + fname) ;
					// Write the file
					if( fileName.lastIndexOf("\\") >= 0 ){
						file = new File( filePath + 
								fileName.substring( fileName.lastIndexOf("\\"))) ;
					}else{
						file = new File( filePath + 
								fileName.substring(fileName.lastIndexOf("\\")+1)) ;
					}
					fi.write( file ) ;
					out.println("Uploaded Filename: " + fileName + "<br>");
				}UtilityService.addFile(fname, fileName);
			}
			response.sendRedirect("confirmation.jsp?msg=successup");
				}catch(Exception ex) {
			response.sendRedirect("confirmation.jsp?msg=error");
			System.out.println(ex);
		}
	
		
//		if (isMultipart) {
//			
//			upload.setSizeMax( maxFileSize );
//			String fname = null;
//			String filename = null;
//			String category = null;
//			try {
//				List items = upload.parseRequest(request);
//				Iterator it = items.iterator();
//				System.out.println("it..."  + items.size());
//				while (it.hasNext()) 
//				{
//					FileItem fileitem = (FileItem)it.next();
//					if(fileitem.isFormField()){ 
//						fname = (String)fileitem.getString(); 
//						//category =(String)fileitem.getString(); 
//					}
//					else{
//						String fileName = fileitem.getName();
//						int index=fileName.lastIndexOf("\\");
//						filename=(fileName.substring(index +1));
//					}
//					
//				}
//				//UtilityService.addFile(fname, filename);
//			} catch (FileUploadException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			RequestDispatcher dd = request.getRequestDispatcher("success.jsp");
//			dd.forward(request, response);
//		}
	}
}
*/





}
