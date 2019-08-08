package model;

public class Hierontatyyppi {
	private int hieronta_id, hinta;
	private String hierontatyyppi, lisatietoja;
	
	public Hierontatyyppi() {
		super();
	}

	public Hierontatyyppi(int hieronta_id, int hinta, String hierontatyyppi, String lisatietoja) {
		super();
		this.hieronta_id = hieronta_id;
		this.hinta = hinta;
		this.hierontatyyppi = hierontatyyppi;
		this.lisatietoja = lisatietoja;
	}

	public int getHieronta_id() {
		return hieronta_id;
	}

	public void setHieronta_id(int hieronta_id) {
		this.hieronta_id = hieronta_id;
	}

	public int getHinta() {
		return hinta;
	}

	public void setHinta(int hinta) {
		this.hinta = hinta;
	}

	public String getHierontatyyppi() {
		return hierontatyyppi;
	}

	public void setHierontatyyppi(String hierontatyyppi) {
		this.hierontatyyppi = hierontatyyppi;
	}

	public String getLisatietoja() {
		return lisatietoja;
	}

	public void setLisatietoja(String lisatietoja) {
		this.lisatietoja = lisatietoja;
	}

	@Override
	public String toString() {
		return "Hierontatyyppi [hieronta_id=" + hieronta_id + ", hinta=" + hinta + ", hierontatyyppi=" + hierontatyyppi
				+ ", lisatietoja=" + lisatietoja + "]";
	}
	
}
