package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Asiakas;
import dao.Dao_Asiakas;


@WebServlet("/Servlet_HaeAsiakkaat")
public class Servlet_HaeAsiakkaat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Servlet_HaeAsiakkaat() {
        super();
       System.out.println("Servlet_HaeAsiakkaat.Servlet_HaeAsiakkaat()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeAsiakkaat.doGet()");
		String hakusana = request.getParameter("hakusana");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("hakusanaAsiakas", hakusana);	
				
		Dao_Asiakas dao = new Dao_Asiakas();
		try {
			ArrayList<Asiakas> asiakkaat = dao.haeAsiakkaat(hakusana);
			request.setAttribute("asiakkaat", asiakkaat);		
			String jsp = "/asiakkaat.jsp"; 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeAsiakkaat.doPost()");
	}
}
