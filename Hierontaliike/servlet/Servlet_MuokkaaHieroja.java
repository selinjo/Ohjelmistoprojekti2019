package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hieroja;
import dao.Dao_Hieroja;


@WebServlet("/Servlet_MuokkaaHieroja")
public class Servlet_MuokkaaHieroja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Servlet_MuokkaaHieroja() {
        super();
        System.out.println("Servlet_MuokkaaHieroja.Servlet_MuokkaaHieroja()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaHieroja.doGet()");
		String id = request.getParameter("Hieroja_id");
		id=id.replace("muokkaa_", "");
		Dao_Hieroja dao = new Dao_Hieroja();
		try {
			Hieroja hieroja = dao.haeHieroja(Integer.parseInt(id));
			request.setAttribute("hieroja", hieroja);		
			String jsp = "/muokkaahieroja.jsp"; 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaHieroja.doPost()");
		Hieroja hieroja = new Hieroja();
		hieroja.setHieroja_id(Integer.parseInt(request.getParameter("Hieroja_id")));
		hieroja.setEtunimi(request.getParameter("etunimi"));
		hieroja.setSukunimi(request.getParameter("sukunimi"));
		hieroja.setSahkoposti(request.getParameter("sahkoposti"));
		hieroja.setSalasana(request.getParameter("salasana"));
		hieroja.setPuhelin(request.getParameter("puhelin"));
		hieroja.setLisatiedot(request.getParameter("lisatiedot"));
		
		Dao_Hieroja dao = new Dao_Hieroja();
		dao.muutaHieroja(hieroja);
		response.sendRedirect("hierojat.jsp");
	}

}
