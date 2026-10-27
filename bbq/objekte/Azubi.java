package bbq.objekte;

public class Azubi {

	private int id;
	private String name;
	String vorname;
	private Spezialisierung spezialisierung;
	private Gruppe gruppe;

	public Azubi() {
		super();
	}

	public Azubi(int id, String name, String vorname, Spezialisierung spezialisierung, Gruppe gruppe) {

		super();
		this.id = id;
		this.name = name;
		this.vorname = vorname;
		this.spezialisierung = spezialisierung;
		this.gruppe = gruppe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		String str = name + vorname + "\n";
		str += "Gruppe " + gruppe.getId() + "\n";
		str += "Spezialisierung" + spezialisierung.getBezeichnung();
		return str;
	}

}