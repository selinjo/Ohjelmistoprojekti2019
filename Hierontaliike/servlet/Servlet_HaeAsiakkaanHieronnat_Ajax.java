package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

@WebServlet("/Servlet_HaeAsiakkaanHieronnat_Ajax")
public class Servlet_HaeAsiakkaanHieronnat_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet_HaeAsiakkaanHieronnat_Ajax() {
        super();
       System.out.println("Servlet_HaeAsiakkaanHieronnat_Ajax.Servlet_HaeAsiakkaanHieronnat_Ajax()");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeAsiakkaanHieronnat_Ajax.doGet()");
		Dao dao = new Dao();
		int asiakas_id = (int) request.getSession(false).getAttribute("Kirjautunut_id");
		String id = Integer.toString(asiakas_id);
		try {
			String[] sarakkeet={"Varaus_id,Ajankohta,Kello,Etunimi,Sukunimi,Hierontatyyppi,Lisatietoja"};
			String strJSON = dao.haeTiedotJSON(sarakkeet,"OP_Varaukset INNER JOIN OP_Hierojat ON OP_Varaukset.Hieroja_id=OP_Hierojat.Hieroja_id INNER JOIN OP_Hierontatyypit ON OP_Varaukset.Hieronta_id=OP_Hierontatyypit.Hieronta_id","Asiakas_id",id,"OP_Varaukset.Poistettu","0","OP_Varaukset.Varaus","1","","",2,3);
			PrintWriter out = response.getWriter(  );
		    response.setContentType("text/html"); 
		    out.println(strJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeAsiakkaanHieronnat_Ajax.doPost()");
	}

}
