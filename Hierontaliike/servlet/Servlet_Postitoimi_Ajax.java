package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Postinumero;

@WebServlet("/Servlet_Postitoimi_Ajax")
public class Servlet_Postitoimi_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Servlet_Postitoimi_Ajax() {
       super();
       System.out.println("Servlet_Postitoimi_Ajax.Servlet_Postitoimi_Ajax()");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_Postitoimi_Ajax.doGet()");
		String postinumero = request.getParameter("postinumero");
		Dao_Postinumero dao = new Dao_Postinumero();
		try {
			String postitoimi = dao.etsiPostitoimi(postinumero).getPostitoimipaikka();
			PrintWriter out = response.getWriter();
		    response.setContentType("text/html"); 		
		    out.println(postitoimi);		    
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_Postitoimi_Ajax.doPost()");
	}

}
