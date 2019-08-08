package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

@WebServlet("/Servlet_HaeVapaatHieronnat_Ajax")
public class Servlet_HaeVapaatHieronnat_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet_HaeVapaatHieronnat_Ajax() {
        super();
       System.out.println("Servlet_HaeVapaatHieronnat_Ajax.Servlet_HaeVapaatHieronnat_Ajax()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeVapaatHieronnat_Ajax.doGet()");
		String id = request.getParameter("Hieroja_id");
		id=id.replace("Hieroja_id=", "");
		Dao dao = new Dao();
		try {
			String[] sarakkeet={"Varaus_id,Ajankohta,Kello"};
			String strJSON = dao.haeTiedotJSON(sarakkeet,"OP_Varaukset","Hieroja_id",id,"Varaus","0","Poistettu","0","Toteutunut","0",2,3);
			PrintWriter out = response.getWriter(  );
		    response.setContentType("text/html"); 
		    out.println(strJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeVapaatHieronnat_Ajax()");
	}

}
