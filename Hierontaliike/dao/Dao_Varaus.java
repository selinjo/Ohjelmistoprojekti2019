package dao;

import java.util.ArrayList;

import model.Varaus;

public class Dao_Varaus extends Dao {

	public ArrayList<Varaus> haeHieronnat(int hieroja_id) throws Exception{		
		ArrayList<Varaus> hieronnat = new ArrayList<Varaus>();
		sql = "SELECT * FROM OP_Varaukset, OP_Asiakkaat, OP_Hierojat, OP_Hierontatyypit ";
		sql+= "WHERE OP_Varaukset.Asiakas_id = OP_Asiakkaat.Asiakas_id ";
		sql+= "AND OP_Varaukset.Hieronta_id = OP_Hierontatyypit.Hieronta_id ";
		sql+= "AND OP_Varaukset.Hieroja_id = OP_Hierojat.Hieroja_id ";
		sql+= "AND OP_Varaukset.Hieroja_id=? AND OP_Varaukset.Varaus=1";
		con=yhdista();
		if(con!=null){
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1, hieroja_id);
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){									
				while(rs.next()){
					Varaus hieronta = new Varaus();
					hieronta.setVaraus_id(rs.getInt("varaus_id"));
					hieronta.setAjankohta(rs.getDate("ajankohta"));
					hieronta.setLisatietoja(rs.getString("lisatietoja"));
					hieronta.setHierontatyyppi(rs.getString("hierontatyyppi"));
					hieronta.setAsiakasenimi(rs.getString("etunimi"));
					hieronta.setAsiakassnimi(rs.getString("sukunimi"));
					hieronnat.add(hieronta);
				}					
			}
			con.close();
		}			
		return hieronnat;
	}
	
	public Varaus haeVaraus(int varaus_id) throws Exception{
		Varaus varaus=null;		
		sql = "SELECT * FROM OP_Varaukset, OP_Hierojat ";
		sql+= "WHERE OP_Varaukset.Hieroja_id = OP_Hierojat.Hieroja_id ";
		sql+= "AND OP_Varaukset.Varaus_id=?"; 		
		con=yhdista();
		if(con!=null){
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, varaus_id);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){								
				while(rs.next()){
					varaus = new Varaus();
					varaus.setVaraus_id(rs.getInt("varaus_id"));
					varaus.setHierojaenimi(rs.getString("etunimi"));
					varaus.setHierojasnimi(rs.getString("sukunimi"));
					varaus.setAjankohta(rs.getDate("ajankohta"));
					varaus.setKello(rs.getString("kello"));
				}					
			}
			con.close();
		}			
		return varaus;
	}
	
	public Varaus haeVaraukset() throws Exception{
		Varaus varaus=null;		
		sql = "SELECT * FROM OP_Varaukset"; 		
		con=yhdista();
		if(con!=null){
			stmtPrep = con.prepareStatement(sql); 			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){								
				while(rs.next()){
					varaus = new Varaus();
					varaus.setVaraus_id(rs.getInt("varaus_id"));
					varaus.setAjankohta(rs.getDate("ajankohta"));
					varaus.setKello(rs.getString("kello"));
				}					
			}
			con.close();
		}			
		return varaus;
	}
	
	public boolean teeVaraus(Varaus varaus){
		boolean paluuArvo=true;
			sql="UPDATE OP_Varaukset SET Hieronta_id=?, Asiakas_id=?, Hinta=?, Lisatietoja=?, Varaus=1 WHERE Varaus_id=?";
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setInt(1, varaus.getHieronta_id());
			stmtPrep.setInt(2, varaus.getAsiakas_id());
			stmtPrep.setInt(3, varaus.getHinta());
			stmtPrep.setString(4, varaus.getLisatietoja());
			stmtPrep.setInt(5, varaus.getVaraus_id());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public boolean lisaaVaraus(Varaus varaus){
		boolean paluuArvo=true;		
		sql="INSERT INTO OP_Varaukset(Hieroja_id, Ajankohta, Kello) VALUES(?,?,?);";						  
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setInt(1, varaus.getHieroja_id());
			stmtPrep.setDate(2, new java.sql.Date(varaus.getAjankohta().getTime()));
			stmtPrep.setString(3, varaus.getKello());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public boolean peruutaAika(Varaus varaus){
		boolean paluuArvo=true;
			sql="UPDATE OP_Varaukset SET Poistettu=1 WHERE Varaus_id=?";
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setInt(1, varaus.getVaraus_id());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public boolean hyvaksyAika(Varaus varaus){
		boolean paluuArvo=true;
			sql="UPDATE OP_Varaukset SET Toteutunut=1 WHERE Varaus_id=?";
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setInt(1, varaus.getVaraus_id());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public boolean peruutaVaraus(Varaus varaus){
		boolean paluuArvo=true;
			sql="UPDATE OP_Varaukset SET Hieronta_id=3, Asiakas_id=10, Hinta=0, Lisatietoja=' ', Varaus=0 WHERE Varaus_id=?";
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setInt(1, varaus.getVaraus_id());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
}
