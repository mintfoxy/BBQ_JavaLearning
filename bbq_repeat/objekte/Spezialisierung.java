package bbq_repeat.objekte;

public class Spezialisierung {

    private int id;
    private String bezeichnung;
    public int id_fach;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getBezeichnung(){
        return bezeichnung;

    }

    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

    public Spezialisierung(){

    }

    public Spezialisierung(int id, String bezeichnung, int id_fach){
        super();
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.id_fach = id_fach;
    }

    @Override
    public String toString(){
        String str = "Spezialisierung " + bezeichnung + " id " + id;
        return str;
    }

}
