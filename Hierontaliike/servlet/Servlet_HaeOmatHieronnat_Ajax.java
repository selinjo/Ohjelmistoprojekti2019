package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

@WebServlet("/Servlet_HaeOmatHieronnat_Ajax")
public class Servlet_HaeOmatHieronnat_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet_HaeOmatHieronnat_Ajax() {
        super();
       System.out.println("Servlet_HaeOmatHieronnat_Ajax.Servlet_HaeOmatHieronnat_Ajax()");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeOmatHieronnat_Ajax.doGet()");
		Dao dao = new Dao();
		int hieroja_id = (int) request.getSession(false).getAttribute("Kirjautunut_id");
		String id = Integer.toString(hieroja_id);
		try {
			String[] sarakkeet={"Varaus_id,Ajankohta,Kello,Etunimi,Sukunimi,Hierontatyyppi,Lisatietoja"};
			String strJSON = dao.haeTiedotJSON(sarakkeet,"OP_Varaukset INNER JOIN OP_Asiakkaat ON OP_Varaukset.Asiakas_id=OP_Asiakkaat.Asiakas_id INNER JOIN OP_Hierontatyypit ON OP_Varaukset.Hieronta_id=OP_Hierontatyypit.Hieronta_id","Hieroja_id",id,"OP_Varaukset.Poistettu","0","OP_Asiakkaat.Poistettu","0","Toteutunut","0",2,3);
			PrintWriter out = response.getWriter(  );
		    response.setContentType("text/html"); 
		    out.println(strJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeOmatHieronnat_Ajax.doPost()");
	}

}
