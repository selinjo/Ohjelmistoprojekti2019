package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Hieroja;


@WebServlet("/Servlet_PoistaHieroja")
public class Servlet_PoistaHieroja extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Servlet_PoistaHieroja() {
        super();
        System.out.println("Servlet_PoistaHieroja.Servlet_PoistaHieroja()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaHieroja.doGet()");
		String id = request.getParameter("Hieroja_id");
		id=id.replace("poista_", "");
		Dao_Hieroja dao = new Dao_Hieroja();
		try {
			dao.poistaHieroja(Integer.parseInt(id));
			String jsp = "/hierojat.jsp"; 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaHieroja.doPost()");
	}

}
