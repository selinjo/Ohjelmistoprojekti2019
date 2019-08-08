package dao;
import java.util.ArrayList;

import model.Postinumero;

public class Dao_Postinumero extends Dao {
	
	public ArrayList<Postinumero> listaaKaikki() throws Exception{
		ArrayList<Postinumero> postinumerot = new ArrayList<Postinumero>();
		sql = "SELECT * FROM OP_Postinumerot"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);        		
    		rs = stmtPrep.executeQuery();   
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					Postinumero postinumero = new Postinumero();
					postinumero.setPostinumero(rs.getString("Postinumero"));
					postinumero.setPostitoimipaikka(rs.getString("Postitoimipaikka"));
					postinumerot.add(postinumero);
				}					
			}
			con.close();
		}			
		return postinumerot;
	}
	
	public Postinumero etsiPostitoimi(String postinumero) throws Exception{
		Postinumero numero = null;
		sql = "SELECT * FROM OP_Postinumerot WHERE Postinumero=?";		
		con=yhdista();
		if(con!=null){ 
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, postinumero);
    		rs = stmtPrep.executeQuery();  
    		if(rs.isBeforeFirst()){
    			rs.next();
    			numero = new Postinumero();  
    			numero.setPostinumero(rs.getString("Postinumero"));
    			numero.setPostitoimipaikka(rs.getString("Postitoimipaikka"));      			       			
			}	
    		con.close(); 
		}			
		return numero;		
	}
}
