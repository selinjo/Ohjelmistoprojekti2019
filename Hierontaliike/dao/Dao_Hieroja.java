package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import model.Hieroja;

public class Dao_Hieroja extends Dao {
	
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

	public Hieroja haeHieroja(String tunnus, String salasana) throws Exception{
		Hieroja hieroja=null;		
		sql = "SELECT * FROM OP_Hierojat WHERE sahkoposti=? AND salasana=? AND Poistettu=0"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, tunnus);	
			stmtPrep.setString(2, suolaa(salasana));	
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					hieroja = new Hieroja();
					hieroja.setHieroja_id(rs.getInt("hieroja_id"));
					hieroja.setEtunimi(rs.getString("etunimi"));
					hieroja.setSukunimi(rs.getString("sukunimi"));
					hieroja.setSahkoposti(rs.getString("sahkoposti"));
					hieroja.setSalasana(rs.getString("salasana"));
					hieroja.setPuhelin(rs.getString("puhelin"));
					hieroja.setLisatiedot(rs.getString("lisatiedot"));
					hieroja.setStatus(rs.getString("status"));
				}					
			}
			con.close();
		}			
		return hieroja;
	}
	
	public Hieroja haeHieroja(int hieroja_id) throws Exception{
		Hieroja hieroja=null;		
		sql = "SELECT * FROM OP_Hierojat RIGHT JOIN OP_Kuvat ON OP_Kuvat.Hieroja_id = OP_Hierojat.Hieroja_id WHERE OP_Hierojat.Hieroja_id=? AND Prioriteetti=0"; 		
		con=yhdista();
		if(con!=null){
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, hieroja_id);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){								
				while(rs.next()){
					hieroja = new Hieroja();
					hieroja.setHieroja_id(rs.getInt("Hieroja_id"));
					hieroja.setKuva_id(rs.getString("Kuva_id"));
					hieroja.setEtunimi(rs.getString("Etunimi"));
					hieroja.setSukunimi(rs.getString("Sukunimi"));
					hieroja.setSahkoposti(rs.getString("Sahkoposti"));
					hieroja.setSalasana(rs.getString("Salasana"));
					hieroja.setPuhelin(rs.getString("Puhelin"));
					hieroja.setLisatiedot(rs.getString("Lisatiedot"));
				}					
			}
			con.close();
		}			
		return hieroja;
	}
	
	public ArrayList<Hieroja> haeHierojat() throws Exception{		
		ArrayList<Hieroja> hierojat = new ArrayList<Hieroja>();
		sql = "SELECT * FROM OP_Hierojat RIGHT JOIN OP_Kuvat ON OP_Kuvat.Hieroja_id = OP_Hierojat.Hieroja_id WHERE poistettu=0 AND status='hieroja' AND prioriteetti=0"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					Hieroja hieroja = new Hieroja();
					hieroja.setHieroja_id(rs.getInt("hieroja_id"));
					hieroja.setKuva_id(rs.getString("kuva_id"));
					hieroja.setEtunimi(rs.getString("etunimi"));
					hieroja.setSukunimi(rs.getString("sukunimi"));
					hieroja.setSahkoposti(rs.getString("sahkoposti"));
					hieroja.setPuhelin(rs.getString("puhelin"));
					hieroja.setLisatiedot(rs.getString("lisatiedot"));
					hierojat.add(hieroja);
				}					
			}
			con.close();
		}			
		return hierojat;
	}
	
	public boolean lisaaHieroja(Hieroja hieroja){
		boolean paluuArvo=true;
		sql="INSERT INTO OP_Hierojat(etunimi, sukunimi, sahkoposti, salasana, puhelin, lisatiedot, status) VALUES(?,?,?,?,?,?,?)";						  
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, hieroja.getEtunimi());
			stmtPrep.setString(2, hieroja.getSukunimi());
			stmtPrep.setString(3, hieroja.getSahkoposti());
			stmtPrep.setString(4, suolaa(hieroja.getSalasana()));
			stmtPrep.setString(5, hieroja.getPuhelin());
			stmtPrep.setString(6, hieroja.getLisatiedot());
			stmtPrep.setString(7, "hieroja");
			stmtPrep.executeUpdate();
			
			rs = stmtPrep.getGeneratedKeys();
			int last_inserted_id = -1;
			if(rs.next()){
			   last_inserted_id = rs.getInt(1);
			}
			sql="INSERT INTO OP_Kuvat(kuva_id, hieroja_id, prioriteetti) VALUES(?,"+last_inserted_id+",0)";
			stmtPrep=con.prepareStatement(sql);
			String profiilikuva = "hieroja_"+last_inserted_id+"profile.png";
			stmtPrep.setString(1, profiilikuva);
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public boolean muutaHieroja(Hieroja hieroja){
		boolean paluuArvo=true;
		if(hieroja.getSalasana().length()>0) {
			sql="UPDATE OP_Hierojat SET Etunimi=?, Sukunimi=?, Sahkoposti=?, Puhelin=?, Lisatiedot=?, Status=?, Salasana=? WHERE Hieroja_id=?";						  
		}else {
			sql="UPDATE OP_Hierojat SET Etunimi=?, Sukunimi=?, Sahkoposti=?, Puhelin=?, Lisatiedot=?, Status=? WHERE Hieroja_id=?";						  
		}
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, hieroja.getEtunimi());
			stmtPrep.setString(2, hieroja.getSukunimi());
			stmtPrep.setString(3, hieroja.getSahkoposti());			
			stmtPrep.setString(4, hieroja.getPuhelin());
			stmtPrep.setString(5, hieroja.getLisatiedot());
			stmtPrep.setString(6, "hieroja");
			if(hieroja.getSalasana().length()>0) {
				stmtPrep.setString(7, suolaa(hieroja.getSalasana()));
				stmtPrep.setInt(8, hieroja.getHieroja_id());
			}else {
				stmtPrep.setInt(7, hieroja.getHieroja_id());
			}
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public ArrayList<String> haeKuvat(int hieroja_id) throws Exception{
		ArrayList<String> kuvat = null;
		sql= "SELECT * FROM OP_Kuvat WHERE Hieroja_id=? ORDER BY Prioriteetti";	
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, hieroja_id);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				kuvat = new ArrayList<String>();
				while(rs.next()){
					kuvat.add(rs.getString("Kuva_id"));
				}					
			}
			con.close();
		}			
		return kuvat;		
	}
	
	public void lisaaKuva(String kuva, int hieroja_id) throws Exception{		
		sql = "INSERT INTO OP_Kuvat(Kuva_id, Hieroja_id, Prioriteetti) VALUES(?,?,?)"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, kuva);
			stmtPrep.setInt(2, hieroja_id);
			stmtPrep.setInt(3, 0);
			stmtPrep.executeUpdate();			
			con.close();
		}		
	}
	
	public void poistaKuva(String kuva_id) throws Exception{		
		sql = "DELETE FROM OP_Kuvat WHERE Kuva_id=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, kuva_id);			
			stmtPrep.executeUpdate();			
			con.close();
		}		
	}
	
	public void jarjestaKuvat(String kuvatStr) throws Exception{
		con=yhdista();
		if(con!=null){ 
			String [] kuvat = kuvatStr.split(";");
			for(int i=0;i<kuvat.length;i++){
				sql = "Update OP_Kuvat SET Prioriteetti=? WHERE Kuva_id=?"; 	
				stmtPrep = con.prepareStatement(sql); 
				stmtPrep.setInt(1, i);
				stmtPrep.setString(2, kuvat[i]);
				stmtPrep.executeUpdate();	
			}					
			con.close();
		}		
	}
	
	public void poistaHieroja(int Hieroja_id) throws Exception{		
		sql = "UPDATE OP_Hierojat SET Poistettu=1 WHERE Hieroja_id=?"; 		
		con=yhdista();
		if(con!=null){
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, Hieroja_id);			
			stmtPrep.executeUpdate();			
			con.close();
		}		
	}
	
	public String haePaaKuva(int hieroja_id) throws Exception{
		String kuva=null;
		sql= "SELECT * FROM OP_Kuvat WHERE Vene_id=? ORDER BY Prioriteetti LIMIT 1";	
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, hieroja_id);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					kuva=rs.getString("Kuva_id");
				}					
			}
			con.close();
		}		
		return kuva;
	}
}
