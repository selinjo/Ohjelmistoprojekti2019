package model;

public class Hieroja {
	private int hieroja_id;
	private String etunimi, sukunimi, status, kuva_id;
	private String sahkoposti, salasana, puhelin, lisatiedot;
	public Hieroja() {
		super();
	}
	
	public Hieroja(int hieroja_id, String kuva_id, String status, String etunimi, String sukunimi, String sahkoposti, String salasana,
			String puhelin, String lisatiedot) {
		super();
		this.hieroja_id = hieroja_id;
		this.kuva_id = kuva_id;
		this.status = status;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.sahkoposti = sahkoposti;
		this.salasana = salasana;
		this.puhelin = puhelin;
		this.lisatiedot = lisatiedot;
	}
	
	public int getHieroja_id() {
		return hieroja_id;
	}
	public void setHieroja_id(int hieroja_id) {
		this.hieroja_id = hieroja_id;
	}
	
	public String getKuva_id() {
		return kuva_id;
	}

	public void setKuva_id(String kuva_id) {
		this.kuva_id = kuva_id;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEtunimi() {
		return etunimi;
	}
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	public String getSukunimi() {
		return sukunimi;
	}
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	public String getSahkoposti() {
		return sahkoposti;
	}
	public void setSahkoposti(String sahkoposti) {
		this.sahkoposti = sahkoposti;
	}
	public String getSalasana() {
		return salasana;
	}
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}
	public String getPuhelin() {
		return puhelin;
	}
	public void setPuhelin(String puhelin) {
		this.puhelin = puhelin;
	}
	public String getLisatiedot() {
		return lisatiedot;
	}
	public void setLisatiedot(String lisatiedot) {
		this.lisatiedot = lisatiedot;
	}
	@Override
	public String toString() {
		return "Hieroja [hieroja_id=" + hieroja_id + ", status=" + status + ", etunimi=" + etunimi + ", sukunimi="
				+ sukunimi + ", sahkoposti=" + sahkoposti + ", salasana=" + salasana + ", puhelin=" + puhelin
				+ ", lisatiedot=" + lisatiedot + "]";
	}

}
