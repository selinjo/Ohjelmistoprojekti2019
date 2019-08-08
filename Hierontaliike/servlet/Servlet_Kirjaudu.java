package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao_Asiakas;
import dao.Dao_Hieroja;
import model.Asiakas;
import model.Hieroja;

@WebServlet("/Servlet_Kirjaudu")
public class Servlet_Kirjaudu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet_Kirjaudu() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_Kirjaudu.doGet()");		
		if(request.getParameter("out")!=null){
			HttpSession session = request.getSession(true);
			session.removeAttribute("Kirjautunut_id");
			session.removeAttribute("Kirjautunut_nimi");
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_Kirjaudu.doPost()");
		String tunnus = request.getParameter("tunnus");	
		String salasana = request.getParameter("salasana");
		Dao_Asiakas dao = new Dao_Asiakas();
		try {
			Asiakas asiakas = dao.haeAsiakas(tunnus, salasana);
			HttpSession session = request.getSession(true);
			if(asiakas!=null){				
				session.setAttribute("Kirjautunut_id", asiakas.getAsiakas_id());	
				session.setAttribute("Kirjautunut_nimi", asiakas.getEtunimi() + " " + asiakas.getSukunimi());
				session.setAttribute("Kirjautunut_status", asiakas.getStatus());
			}else { // Jos kirjautuja ei ole asiakas
				Dao_Hieroja daoHieroja = new Dao_Hieroja();
				Hieroja hieroja = daoHieroja.haeHieroja(tunnus, salasana);
				if(hieroja!=null) {
					session.setAttribute("Kirjautunut_id", hieroja.getHieroja_id());	
					session.setAttribute("Kirjautunut_nimi", hieroja.getEtunimi() + " " + hieroja.getSukunimi());
					session.setAttribute("Kirjautunut_status", hieroja.getStatus());
				}
			}
			response.sendRedirect("hierojat.jsp");
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}
