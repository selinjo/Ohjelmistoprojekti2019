package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Asiakas;
import dao.Dao_Asiakas;


@WebServlet("/Servlet_MuokkaaAsiakasTiedot")
public class Servlet_MuokkaaAsiakasTiedot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Servlet_MuokkaaAsiakasTiedot() {
        super();
        System.out.println("Servlet_MuokkaaAsiakasTiedot.Servlet_MuokkaaAsiakasTiedot()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaAsiakasTiedot.doGet()");
		int id = (int) request.getSession(false).getAttribute("Kirjautunut_id");
		Dao_Asiakas dao = new Dao_Asiakas();
		try {
			Asiakas asiakas = dao.haeAsiakas(id);
			request.setAttribute("asiakas", asiakas);		
			String jsp = "/omatasiakas.jsp"; 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaAsiakasTiedot.doPost()");
		Asiakas asiakas = new Asiakas();
		asiakas.setAsiakas_id(Integer.parseInt(request.getParameter("Asiakas_id")));
		asiakas.setEtunimi(request.getParameter("etunimi"));
		asiakas.setSukunimi(request.getParameter("sukunimi"));
		asiakas.setKatuosoite(request.getParameter("katuosoite"));
		asiakas.setPostinumero(request.getParameter("postinumero"));
		asiakas.setSahkoposti(request.getParameter("sahkoposti"));
		asiakas.setSalasana(request.getParameter("salasana"));
		asiakas.setPuhelin(request.getParameter("puhelin"));
		asiakas.setLisatiedot(request.getParameter("lisatiedot"));
		
		Dao_Asiakas dao = new Dao_Asiakas();
		dao.muutaAsiakas(asiakas);
		response.sendRedirect("omatasiakas.jsp");
	}

}
