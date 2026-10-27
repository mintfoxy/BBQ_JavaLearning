package bbq.objekte;

public class BBQObject {

    protected int id;   //  alle klassen haben eine id, also können wir dafür eine Mutterklasse verwenden
    protected String bezeichnung;

    public String getBezeichnung() {
        return bezeichnung;
    }



    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public BBQObject(int id) {
		this.id = id;
	} 	

}
