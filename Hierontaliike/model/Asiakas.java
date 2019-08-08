package model;

public class Asiakas {
	private int asiakas_id;
	private String etunimi, sukunimi, katuosoite, postinumero, postitoimipaikka;
	private String sahkoposti, salasana, puhelin, lisatiedot, status;
	
	public Asiakas() {
		super();
	}

	public Asiakas(int asiakas_id, String status, String etunimi, String sukunimi, String katuosoite, String postinumero,
			String postitoimipaikka, String sahkoposti, String salasana, String puhelin, String lisatiedot) {
		super();
		this.asiakas_id = asiakas_id;
		this.status = status;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.katuosoite = katuosoite;
		this.postinumero = postinumero;
		this.postitoimipaikka = postitoimipaikka;
		this.sahkoposti = sahkoposti;
		this.salasana = salasana;
		this.puhelin = puhelin;
		this.lisatiedot = lisatiedot;
	}

	public int getAsiakas_id() {
		return asiakas_id;
	}

	public void setAsiakas_id(int asiakas_id) {
		this.asiakas_id = asiakas_id;
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

	public String getKatuosoite() {
		return katuosoite;
	}

	public void setKatuosoite(String katuosoite) {
		this.katuosoite = katuosoite;
	}

	public String getPostinumero() {
		return postinumero;
	}

	public void setPostinumero(String postinumero) {
		this.postinumero = postinumero;
	}
	
	public String getPostitoimipaikka() {
		return postitoimipaikka;
	}

	public void setPostitoimipaikka(String postitoimipaikka) {
		this.postitoimipaikka = postitoimipaikka;
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
		return "Asiakas [asiakas_id=" + asiakas_id + ", status=" + status + ", etunimi=" + etunimi + ", sukunimi="
				+ sukunimi + ", katuosoite=" + katuosoite + ", postinumero=" + postinumero + ", sahkoposti="
				+ sahkoposti + ", salasana=" + salasana + ", puhelin=" + puhelin + ", lisatiedot=" + lisatiedot + "]";
	}
	
}
