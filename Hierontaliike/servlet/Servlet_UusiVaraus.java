package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Varaus;
import model.Varaus;

@WebServlet("/Servlet_UusiVaraus")
public class Servlet_UusiVaraus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet_UusiVaraus() {
        super();
        System.out.println("Servlet_UusiVaraus.Servlet_UusiVaraus()");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_UusiVaraus.doGet()");
		String id = request.getParameter("Varaus_id");
		id=id.replace("varaus_", "");
		Dao_Varaus dao = new Dao_Varaus();
		try {
			Varaus varaus = dao.haeVaraus(Integer.parseInt(id));
			request.setAttribute("varaus", varaus);		
			String jsp = "/varaahieronta.jsp"; 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_UusiVaraus.doPost()");
		int asiakas_id = (int) request.getSession(false).getAttribute("Kirjautunut_id");
		Varaus varaus = new Varaus();
		varaus.setVaraus_id(Integer.parseInt(request.getParameter("varaus_id")));
		varaus.setHieronta_id(Integer.parseInt(request.getParameter("hierontatyypit")));
		varaus.setAsiakas_id(asiakas_id);
		varaus.setLisatietoja(request.getParameter("lisatietoja"));
		varaus.setHinta(Integer.parseInt(request.getParameter("inputHinta")));
		
		Dao_Varaus dao = new Dao_Varaus();
		dao.teeVaraus(varaus);
		response.sendRedirect("asiakkaanhieronnat.jsp");
	}

}
