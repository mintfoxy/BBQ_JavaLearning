package bbq.objekte;

public class Modul extends BBQObject{

	private String bezeichnung;
	private Spezialisierung spezialisierung;

	public Modul(int id, String bezeichnung) {
		super(id);
		this.id = id;
		this.bezeichnung = bezeichnung;
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