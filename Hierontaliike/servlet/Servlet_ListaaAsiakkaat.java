package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Asiakas;
import model.Asiakas;

@WebServlet("/Servlet_ListaaAsiakkaat")
public class Servlet_ListaaAsiakkaat extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet_ListaaAsiakkaat() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_ListaaAsiakkaat.doGet()");
		Dao_Asiakas dao = new Dao_Asiakas();
		try {
			ArrayList<Asiakas> asiakkaat = dao.haeAsiakkaat();
			request.setAttribute("asiakkaat", asiakkaat);
			String jsp = "/asiakkaat.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_ListaaAsiakkaat.doPost()");
	}

}