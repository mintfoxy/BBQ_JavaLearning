package bbq.objekte;

public class Azubi extends BBQObject {

	private String name;
	String vorname;
	private Spezialisierung spezialisierung;
	private Gruppe gruppe;

	public Azubi(int id, String name, String vorname) {

		super(id);
		this.name = name;
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Spezialisierung getSpezialisierung() {
		return spezialisierung;
	}

	public void setSpezialisierung(Spezialisierung spezialisierung) {
		this.spezialisierung = spezialisierung;
	}

	public Gruppe getGruppe() {
		return gruppe;
	}

	public void setGruppe(Gruppe gruppe) {
		this.gruppe = gruppe;
	}

	@Override
	public String toString() {
		return name + " " + vorname + " id " + id + "\n";
	}

}