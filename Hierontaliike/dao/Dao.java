package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Dao { 
	public Connection con=null;
	public ResultSet rs = null;
	public PreparedStatement stmtPrep=null; 
	public String sql;
	
	public Connection yhdista() throws Exception{
    	Connection con = null;    	        	
    	String JDBCAjuri = "org.mariadb.jdbc.Driver";
    	String url = "jdbc:mariadb://localhost:15001/a1800266";  //15001      	
   	    Class.forName(JDBCAjuri);
	    con = DriverManager.getConnection(url,"a1800266", "xeSIvl27u");	        
	    return con;	    
	}
	
	public void sulje() throws Exception{
		if(con!=null){			
			con.close();			
		}		
	}	
	
	public String haeArvo(String sarake, String taulu, String hakusarake, String hakuarvo) throws Exception{
		String paluu="";		
		sql = "SELECT "+sarake+" FROM "+taulu+" WHERE "+hakusarake+"=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, hakuarvo);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					paluu = rs.getString(1);		
				}					
			}
			con.close();
		}			
		return paluu;
	}
	public String haeArvo(String sarake, String taulu, String hakusarake, int hakuarvo) throws Exception{
		String paluu="";		
		sql = "SELECT "+sarake+" FROM "+taulu+" WHERE "+hakusarake+"=?"; 		
		System.out.println(sql + " " + hakuarvo);
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, hakuarvo);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					paluu = rs.getString(1);		
				}					
			}
			con.close();
		}			
		return paluu;
	}
	
	//Yksinkertainen tapa hakea tiedot taulusta ja muuttaa se JSON:iksi
	//Parametrit: 1)mitk‰ sarakkeet haetaan 2)mist‰ taulusta 3-4)mill‰ ehdolla 5-6)toinen ehto 7-8)kolmas ehto 9)sorttaussarake
	public String haeTiedotJSON(String[] sarakkeet, String taulu, String ehtoSarake, String ehtoArvo, String ehtoSarake2, String ehtoArvo2, String ehtoSarake3, String ehtoArvo3, String ehtoSarake4, String ehtoArvo4, int sort, int sort2) throws Exception{		
		String palautusJSON="";	
		String sarStr="";
		for(int i=0;i<sarakkeet.length;i++){
			sarStr += sarakkeet[i] +",";
		}		
		sarStr = sarStr.substring(0,sarStr.length()-1); //poistetaan viimeinen pilkku				
		sql = "SELECT "+sarStr+" FROM "+taulu;
		if(ehtoSarake.length()>0){
			sql += " WHERE "+ehtoSarake+"=?";
		}
		if(ehtoSarake2.length()>0) { //Jos toinen ehto
			sql += " AND "+ehtoSarake2+"=?";
		}
		if(ehtoSarake3.length()>0) { //Jos kolmas ehto
			sql += " AND "+ehtoSarake3+"=?";
		}
		if(ehtoSarake4.length()>0) { //Jos nelj‰s ehto
			sql += " AND "+ehtoSarake4+"=?";
		}
		if(sort>0){
			sql += " ORDER BY " + sort;
		}
		if(sort2>0) {
			sql += ", " + sort2;
		}
		
		System.out.println(sql);
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
			if(ehtoSarake.length()>0){
				stmtPrep.setString(1, ehtoArvo);
			}
			if(ehtoSarake2.length()>0){
				stmtPrep.setString(2, ehtoArvo2);
			}
			if(ehtoSarake3.length()>0){
				stmtPrep.setString(3, ehtoArvo3);
			}
			if(ehtoSarake4.length()>0){
				stmtPrep.setString(4, ehtoArvo4);
			}
    		rs = stmtPrep.executeQuery();  
    		ResultSetMetaData rsmd = rs.getMetaData();
			if(rs!=null){ //jos kysely onnistui		
				int numColumns = rsmd.getColumnCount();
				palautusJSON += "[";
				while(rs.next()){//K‰yd‰‰n tietueet l‰pi	
					palautusJSON += "{";
					for (int i=1; i<numColumns+1; i++) {//K‰yd‰‰n sarakkeet l‰pi
						palautusJSON += "\"";
						palautusJSON += rsmd.getColumnName(i);
						palautusJSON += "\":";
						palautusJSON += "\"";
						try {
							palautusJSON += rs.getString(i).trim();
						} catch (Exception e) {
							e.printStackTrace();
						}						
						palautusJSON += "\"";
						if(i<numColumns){
							palautusJSON += ",";
						}						
					}	
					palautusJSON += "}";					
					palautusJSON += ",";					
				}	
				palautusJSON += "]";
			}
			con.close();
		}
		//Siivotaan viimeinen pilkku pois
		palautusJSON = palautusJSON.substring(0, palautusJSON.length()-2) + "]";
		if(palautusJSON.length()==1){			
			palautusJSON="{}";
		}
		return palautusJSON;
	}	
}
