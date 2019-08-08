package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Asiakas;
import model.Asiakas;

@WebServlet("/Servlet_LisaaAsiakas")
public class Servlet_LisaaAsiakas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet_LisaaAsiakas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_UusiAsiakas.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_LisaaAsiakas.doPost()");
		
		Asiakas asiakas = new Asiakas();
		asiakas.setEtunimi(request.getParameter("etunimi"));
		asiakas.setSukunimi(request.getParameter("sukunimi"));
		asiakas.setKatuosoite(request.getParameter("katuosoite"));
		asiakas.setPostinumero(request.getParameter("postinumero"));
		asiakas.setSahkoposti(request.getParameter("sahkoposti"));
		asiakas.setSalasana(request.getParameter("salasana"));
		asiakas.setPuhelin(request.getParameter("puhelin"));
		asiakas.setLisatiedot(request.getParameter("lisatiedot"));
		
		Dao_Asiakas dao = new Dao_Asiakas();
		if(dao.lisaaAsiakas(asiakas)){
			response.sendRedirect("index.jsp?ok=1");
		}else{
			response.sendRedirect("rekisterointi.jsp?ok=0");
		}
	}

}
