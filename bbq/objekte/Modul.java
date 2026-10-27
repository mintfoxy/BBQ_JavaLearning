package bbq.objekte;

public class Modul {

	private int id;
	private String bezeichnung;
	private Spezialisierung spezialisierung;

	public Modul() {
		super();
	}

	public Modul(int id, String bezeichnung, Spezialisierung spezialisierung) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.spezialisierung = spezialisierung;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Spezialisierung getSpezialisierung() {
		return spezialisierung;
	}

	public void setSpezialisierung(Spezialisierung spezialisierung) {
		this.spezialisierung = spezialisierung;
	}

	@Override
	public String toString() {
		return bezeichnung + " id " + id + " spezialisierung " + spezialisierung.getBezeichnung();
	}

}