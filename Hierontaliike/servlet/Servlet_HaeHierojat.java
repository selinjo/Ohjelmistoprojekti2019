package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Hieroja;
import model.Hieroja;

@WebServlet("/Servlet_HaeHierojat")
public class Servlet_HaeHierojat extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet_HaeHierojat() {
        super();
       System.out.println("Servlet_HaeHierojat.Servlet_HaeHierojat()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeHierojat.doGet()");
		Dao_Hieroja dao = new Dao_Hieroja();
		try {
			ArrayList<Hieroja> hierojat = dao.haeHierojat();
			request.setAttribute("hierojat", hierojat);
			String jsp = "/hierojat.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeHierojat.doPost()");
	}

}
