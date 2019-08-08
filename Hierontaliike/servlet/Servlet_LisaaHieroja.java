package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Hieroja;
import model.Hieroja;

@WebServlet("/Servlet_LisaaHieroja")
public class Servlet_LisaaHieroja extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet_LisaaHieroja() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_LisaaHieroja.doPost()");
		
		Hieroja hieroja = new Hieroja();
		hieroja.setEtunimi(request.getParameter("etunimi"));
		hieroja.setSukunimi(request.getParameter("sukunimi"));
		hieroja.setSahkoposti(request.getParameter("sahkoposti"));
		hieroja.setSalasana(request.getParameter("salasana"));
		hieroja.setPuhelin(request.getParameter("puhelin"));
		hieroja.setLisatiedot(request.getParameter("lisatiedot"));
		
		Dao_Hieroja dao = new Dao_Hieroja();
		dao.lisaaHieroja(hieroja);
		response.sendRedirect("hierojat.jsp");
	}

}
