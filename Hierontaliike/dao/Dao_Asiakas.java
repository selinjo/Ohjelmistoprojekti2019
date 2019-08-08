package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import model.Asiakas;

public class Dao_Asiakas extends Dao {
	
	private String suolaa(String pwd){
		String paluuArvo="";
		String suola="INNOSTUNUTsonni";
		String suolattuPwd=pwd+suola;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			 byte[] messageDigest = md.digest(suolattuPwd.getBytes());
			 BigInteger number = new BigInteger(1, messageDigest);
			 paluuArvo = number.toString(16);
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
		return paluuArvo;
	}
	
	public Asiakas haeAsiakas(String tunnus, String salasana) throws Exception{
		Asiakas asiakas=null;		
		sql = "SELECT * FROM OP_Asiakkaat WHERE sahkoposti=? AND salasana=? AND Poistettu=0"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, tunnus);	
			stmtPrep.setString(2, suolaa(salasana));	
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					asiakas = new Asiakas();
					asiakas.setAsiakas_id(rs.getInt("asiakas_id"));
					asiakas.setEtunimi(rs.getString("etunimi"));
					asiakas.setSukunimi(rs.getString("sukunimi"));
					asiakas.setKatuosoite(rs.getString("katuosoite"));
					asiakas.setPostinumero(rs.getString("postinumero"));
					asiakas.setSahkoposti(rs.getString("sahkoposti"));
					asiakas.setSalasana(rs.getString("salasana"));
					asiakas.setPuhelin(rs.getString("puhelin"));
					asiakas.setLisatiedot(rs.getString("lisatiedot"));
					asiakas.setStatus(rs.getString("status"));
				}					
			}
			con.close();
		}			
		return asiakas;
	}
	
	public ArrayList<Asiakas> haeAsiakkaat() throws Exception{		
		ArrayList<Asiakas> asiakkaat = new ArrayList<Asiakas>();
		sql = "SELECT * FROM OP_Asiakkaat WHERE poistettu=0"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					Asiakas asiakas = new Asiakas();
					asiakas.setAsiakas_id(rs.getInt("asiakas_id"));
					asiakas.setEtunimi(rs.getString("etunimi"));
					asiakas.setSukunimi(rs.getString("sukunimi"));
					asiakas.setSahkoposti(rs.getString("sahkoposti"));
					asiakas.setPuhelin(rs.getString("puhelin"));
					asiakas.setLisatiedot(rs.getString("lisatiedot"));
					asiakkaat.add(asiakas);
				}					
			}
			con.close();
		}			
		return asiakkaat;
	}
	
	public ArrayList<Asiakas> haeAsiakkaat(String hakusana) throws Exception{		
		ArrayList<Asiakas> asiakkaat = new ArrayList<Asiakas>();
		sql = "SELECT * FROM OP_Asiakkaat a JOIN OP_Postinumerot p ON a.Postinumero = p.Postinumero WHERE (Etunimi LIKE ? OR Sukunimi LIKE ?) AND Poistettu=0 AND Status='asiakas' ORDER BY 2"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, "%"+hakusana+"%");
			stmtPrep.setString(2, "%"+hakusana+"%");
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					Asiakas asiakas = new Asiakas();
					asiakas.setAsiakas_id(rs.getInt("Asiakas_id"));
					asiakas.setEtunimi(rs.getString("Etunimi"));
					asiakas.setSukunimi(rs.getString("Sukunimi"));
					asiakas.setKatuosoite(rs.getString("Katuosoite"));
					asiakas.setPostinumero(rs.getString("Postinumero"));
					asiakas.setPostitoimipaikka(rs.getString("Postitoimipaikka"));
					asiakas.setSahkoposti(rs.getString("Sahkoposti"));
					asiakas.setPuhelin(rs.getString("Puhelin"));
					asiakas.setLisatiedot(rs.getString("Lisatiedot"));
					asiakkaat.add(asiakas);
				}					
			}
			con.close();
		}			
		return asiakkaat;
	}
	
	public Asiakas haeAsiakas(int asiakas_id) throws Exception{
		Asiakas asiakas=null;		
		sql = "SELECT * FROM OP_Asiakkaat WHERE Asiakas_id=?"; 		
		con=yhdista();
		if(con!=null){
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, asiakas_id);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){								
				while(rs.next()){
					asiakas = new Asiakas();
					asiakas.setAsiakas_id(rs.getInt("Asiakas_id"));
					asiakas.setEtunimi(rs.getString("Etunimi"));
					asiakas.setSukunimi(rs.getString("Sukunimi"));
					asiakas.setKatuosoite(rs.getString("Katuosoite"));
					asiakas.setPostinumero(rs.getString("Postinumero"));
					asiakas.setSahkoposti(rs.getString("Sahkoposti"));
					asiakas.setSalasana(rs.getString("Salasana"));
					asiakas.setPuhelin(rs.getString("Puhelin"));
					asiakas.setLisatiedot(rs.getString("Lisatiedot"));
				}					
			}
			con.close();
		}			
		return asiakas;
	}
	
	public boolean lisaaAsiakas(Asiakas asiakas){
		boolean paluuArvo=true;
		sql="INSERT INTO OP_Asiakkaat(etunimi, sukunimi, katuosoite, postinumero, sahkoposti, salasana, puhelin, lisatiedot, status) VALUES(?,?,?,?,?,?,?,?,?)";						  
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, asiakas.getEtunimi());
			stmtPrep.setString(2, asiakas.getSukunimi());
			stmtPrep.setString(3, asiakas.getKatuosoite());
			stmtPrep.setString(4, asiakas.getPostinumero());
			stmtPrep.setString(5, asiakas.getSahkoposti());
			stmtPrep.setString(6, suolaa(asiakas.getSalasana()));
			stmtPrep.setString(7, asiakas.getPuhelin());
			stmtPrep.setString(8, asiakas.getLisatiedot());
			stmtPrep.setString(9, "asiakas");
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public boolean muutaAsiakas(Asiakas asiakas){
		boolean paluuArvo=true;
		if(asiakas.getSalasana().length()>0) {
			sql="UPDATE OP_Asiakkaat SET Etunimi=?, Sukunimi=?, Katuosoite=?, Postinumero=?, Sahkoposti=?, Puhelin=?, Lisatiedot=?, Status=?, Salasana=? WHERE Asiakas_id=?";						  
		}else {
			sql="UPDATE OP_Asiakkaat SET Etunimi=?, Sukunimi=?, Katuosoite=?, Postinumero=?, Sahkoposti=?, Puhelin=?, Lisatiedot=?, Status=? WHERE Asiakas_id=?";						  
		}
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, asiakas.getEtunimi());
			stmtPrep.setString(2, asiakas.getSukunimi());
			stmtPrep.setString(3, asiakas.getKatuosoite());
			stmtPrep.setString(4, asiakas.getPostinumero());
			stmtPrep.setString(5, asiakas.getSahkoposti());			
			stmtPrep.setString(6, asiakas.getPuhelin());
			stmtPrep.setString(7, asiakas.getLisatiedot());
			stmtPrep.setString(8, "asiakas");
			if(asiakas.getSalasana().length()>0) {
				stmtPrep.setString(9, suolaa(asiakas.getSalasana()));
				stmtPrep.setInt(10, asiakas.getAsiakas_id());
			}else {
				stmtPrep.setInt(9, asiakas.getAsiakas_id());
			}
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public void poistaAsiakas(int Asiakas_id) throws Exception{		
		sql = "UPDATE OP_Asiakkaat SET Poistettu=1 WHERE Asiakas_id=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, Asiakas_id);			
			stmtPrep.executeUpdate();			
			con.close();
		}		
	}

}