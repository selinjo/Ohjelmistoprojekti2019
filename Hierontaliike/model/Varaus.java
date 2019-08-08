package model;

import java.util.Date;

public class Varaus {
	private int varaus_id, hieronta_id, asiakas_id, hieroja_id;
	private int hinta;
	private String lisatietoja, hierontatyyppi, kello;
	private String hierojaenimi, hierojasnimi, asiakasenimi, asiakassnimi;
	private Date ajankohta;
	
	
	public Varaus() {
		super();
	}

	public Varaus(int varaus_id, int hieronta_id, int asiakas_id, int hieroja_id, int kesto, int hinta,
			Date ajankohta, String lisatietoja, String kello) {
		super();
		this.varaus_id = varaus_id;
		this.hieronta_id = hieronta_id;
		this.asiakas_id = asiakas_id;
		this.hieroja_id = hieroja_id;
		this.hinta = hinta;
		this.ajankohta = ajankohta;
		this.kello = kello;
		this.lisatietoja = lisatietoja;
	}

	public int getVaraus_id() {
		return varaus_id;
	}

	public void setVaraus_id(int varaus_id) {
		this.varaus_id = varaus_id;
	}

	public int getHieronta_id() {
		return hieronta_id;
	}

	public void setHieronta_id(int hieronta_id) {
		this.hieronta_id = hieronta_id;
	}

	public int getAsiakas_id() {
		return asiakas_id;
	}

	public void setAsiakas_id(int asiakas_id) {
		this.asiakas_id = asiakas_id;
	}

	public int getHieroja_id() {
		return hieroja_id;
	}

	public void setHieroja_id(int hieroja_id) {
		this.hieroja_id = hieroja_id;
	}

	public int getHinta() {
		return hinta;
	}

	public void setHinta(int hinta) {
		this.hinta = hinta;
	}

	public Date getAjankohta() {
		return ajankohta;
	}

	public void setAjankohta(Date ajankohta) {
		this.ajankohta = ajankohta;
	}

	public String getLisatietoja() {
		return lisatietoja;
	}

	public void setLisatietoja(String lisatietoja) {
		this.lisatietoja = lisatietoja;
	}

	public String getHierontatyyppi() {
		return hierontatyyppi;
	}

	public void setHierontatyyppi(String hierontatyyppi) {
		this.hierontatyyppi = hierontatyyppi;
	}

	public String getHierojaenimi() {
		return hierojaenimi;
	}

	public void setHierojaenimi(String hierojaenimi) {
		this.hierojaenimi = hierojaenimi;
	}

	public String getHierojasnimi() {
		return hierojasnimi;
	}

	public void setHierojasnimi(String hierojasnimi) {
		this.hierojasnimi = hierojasnimi;
	}

	public String getAsiakasenimi() {
		return asiakasenimi;
	}

	public void setAsiakasenimi(String asiakasenimi) {
		this.asiakasenimi = asiakasenimi;
	}

	public String getAsiakassnimi() {
		return asiakassnimi;
	}

	public void setAsiakassnimi(String asiakassnimi) {
		this.asiakassnimi = asiakassnimi;
	}

	public String getKello() {
		return kello;
	}

	public void setKello(String kello) {
		this.kello = kello;
	}

	@Override
	public String toString() {
		return "Varaus [varaus_id=" + varaus_id + ", hieronta_id=" + hieronta_id + ", asiakas_id=" + asiakas_id
				+ ", hieroja_id=" + hieroja_id + ", hinta=" + hinta + ", ajankohta=" + ajankohta
				+ ", lisatietoja=" + lisatietoja + "]";
	}
	
}
