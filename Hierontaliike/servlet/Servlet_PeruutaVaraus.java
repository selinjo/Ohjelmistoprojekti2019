package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Varaus;
import model.Varaus;

@WebServlet("/Servlet_PeruutaVaraus")
public class Servlet_PeruutaVaraus extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet_PeruutaVaraus() {
        super();
        System.out.println("Servlet_PeruutaVaraus.Servlet_PeruutaVaraus()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PeruutaVaraus.doGet()");
		String Strid = request.getParameter("Varaus_id");
		Strid=Strid.replace("poista_", "");
		int id=Integer.parseInt(Strid);
		Varaus varaus = new Varaus();
		varaus.setVaraus_id(id);
		Dao_Varaus dao = new Dao_Varaus();
		dao.peruutaVaraus(varaus);
		response.sendRedirect("asiakkaanhieronnat.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PeruutaVaraus.doPost()");
	}

}
