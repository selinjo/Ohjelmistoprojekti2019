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


@WebServlet("/Servlet_MuokkaaHierojaTiedot")
public class Servlet_MuokkaaHierojaTiedot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Servlet_MuokkaaHierojaTiedot() {
        super();
        System.out.println("Servlet_MuokkaaHierojaTiedot.Servlet_MuokkaaHierojaTiedot()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaHierojaTiedot.doGet()");
		int id = (int) request.getSession(false).getAttribute("Kirjautunut_id");
		Dao_Hieroja dao = new Dao_Hieroja();
		try {
			Hieroja hieroja = dao.haeHieroja(id);
			request.setAttribute("hieroja", hieroja);		
			String jsp = "/omathieroja.jsp"; 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaHierojaTiedot.doPost()");
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
		response.sendRedirect("omathieroja.jsp");
	}

}
