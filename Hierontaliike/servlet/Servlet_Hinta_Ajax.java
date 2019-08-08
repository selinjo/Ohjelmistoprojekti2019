package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

@WebServlet("/Servlet_Hinta_Ajax")
public class Servlet_Hinta_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet_Hinta_Ajax() {
        super();
        System.out.println("Servlet_Hinta_Ajax.Servlet_Hinta_Ajax()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_Hinta_Ajax.doGet()");
		String id = request.getParameter("Hieronta_id");
		id=id.replace("Hieronta_id=", "");
		Dao dao = new Dao();
		try {
			String[] sarakkeet={"Hinta"};
			String strJSON = dao.haeTiedotJSON(sarakkeet,"OP_Hierontatyypit","Hieronta_id",id,"","","","","","",1,0);
			PrintWriter out = response.getWriter(  );
		    response.setContentType("text/html"); 
		    out.println(strJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_Hinta_Ajax.doPost()");
	}

}
