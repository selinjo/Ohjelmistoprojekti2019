package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

@WebServlet("/Servlet_Hierojat_Ajax")
public class Servlet_Hierojat_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet_Hierojat_Ajax() {
        super();
        System.out.println("Servlet_Hierojat_Ajax.Servlet_Hierojat_Ajax()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_Hierojat_Ajax.doGet()");
		Dao dao = new Dao();
		try {
			String[] sarakkeet={"Hieroja_id", "Etunimi", "Sukunimi"};
			String strJSON = dao.haeTiedotJSON(sarakkeet,"OP_Hierojat","Status","hieroja","","","","","","",3,0);
			PrintWriter out = response.getWriter(  );
		    response.setContentType("text/html"); 
		    out.println(strJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_Hierojat_Ajax.doPost()");
	}

}
