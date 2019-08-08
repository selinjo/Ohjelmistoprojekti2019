package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Varaus;
import model.Varaus;

@WebServlet("/Servlet_HyvaksyAika")
public class Servlet_HyvaksyAika extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet_HyvaksyAika() {
        super();
        System.out.println("Servlet_HyvaksyAika.Servlet_HyvaksyAika()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HyvaksyAika.doGet()");
		String Strid = request.getParameter("Varaus_id");
		Strid=Strid.replace("hyvaksy_", "");
		int id=Integer.parseInt(Strid);
		Varaus varaus = new Varaus();
		varaus.setVaraus_id(id);
		Dao_Varaus dao = new Dao_Varaus();
		dao.hyvaksyAika(varaus);
		response.sendRedirect("hieronnat.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HyvaksyAika.doPost()");
	}

}
