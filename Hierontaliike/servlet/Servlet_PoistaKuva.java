package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Hieroja;


@WebServlet("/Servlet_PoistaKuva")
public class Servlet_PoistaKuva extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    public Servlet_PoistaKuva() {
        super();
        System.out.println("Servlet_PoistaKuva.Servlet_PoistaKuva()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaKuva.doGet()");
		String poistettava = request.getParameter("kuva_id");
		Dao_Hieroja dao = new Dao_Hieroja();
		try {
			dao.poistaKuva(poistettava);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaKuva.doPost()");
	}

}
