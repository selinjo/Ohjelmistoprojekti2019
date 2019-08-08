package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Hieroja;

@WebServlet("/Servlet_JarjestaKuvat")
public class Servlet_JarjestaKuvat extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    public Servlet_JarjestaKuvat() {
        super();
        System.out.println("Servlet_JarjestaKuvat.Servlet_JarjestaKuvat()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_JarjestaKuvat.doGet()");
		String kuvat = request.getParameter("kuvat");
		Dao_Hieroja dao = new Dao_Hieroja();
		try {
			dao.jarjestaKuvat(kuvat);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_JarjestaKuvat.doPost()");
	}

}
