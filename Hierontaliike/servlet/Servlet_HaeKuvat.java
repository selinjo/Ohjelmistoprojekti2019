package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Dao_Hieroja;

@WebServlet("/Servlet_HaeKuvat")
public class Servlet_HaeKuvat extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Servlet_HaeKuvat() {
        super();
        System.out.println("Servlet_HaeKuvat.Servlet_HaeKuvat()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeKuvat.doGet()");
		String id = request.getParameter("hieroja_id");		
		Dao_Hieroja dao = new Dao_Hieroja();
		try {			
			ArrayList<String> kuvat = dao.haeKuvat(Integer.parseInt(id));			
			request.setAttribute("kuvat", kuvat);				
			String jsp = "/uploader.jsp?hieroja_id="+id; 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeKuvat.doPost()");
	}

}
