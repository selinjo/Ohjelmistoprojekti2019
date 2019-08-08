package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Varaus;
import model.Varaus;

@WebServlet("/Servlet_LisaaHieronta")
public class Servlet_LisaaHieronta extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Servlet_LisaaHieronta() {
        super();
        System.out.println("Servlet_LisaaHieronta.Servlet_LisaaHieronta()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_LisaaHieronta.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_LisaaHieronta.doPost()");
		int hieroja_id = (int) request.getSession(false).getAttribute("Kirjautunut_id");
		String pvm = request.getParameter("ajankohta");
		String haeklo = request.getParameter("inputKello");
		String klo = haeklo.substring(0,2) + ":" + haeklo.substring(2,4) + ":" + "00"; 
		Varaus varaus = new Varaus();
		varaus.setHieroja_id(hieroja_id);
		Date hPvm = null;
		SimpleDateFormat fdate=new SimpleDateFormat("dd.MM.yyyy");
		try {
			hPvm = fdate.parse(pvm);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		varaus.setAjankohta(hPvm);
		varaus.setKello(klo);
		Dao_Varaus dao = new Dao_Varaus();
		dao.lisaaVaraus(varaus);
		response.sendRedirect("hieronnat.jsp");
	}

}
