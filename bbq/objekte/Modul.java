package bbq.objekte;

public class Modul {

    private int id;
    private String modul_name;
    public int id_spezialisierung;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getModul_Name(){
        return modul_name;

    }

    public void setModul_Name(String modul_name){
        this.modul_name = modul_name;
    }

    public Modul(){

    }

    public Modul(int id, String bezeichnung, int id_spezialisierung){
        super();
        this.id = id;
        this.modul_name = bezeichnung;
        this.id_spezialisierung = id_spezialisierung;
    }

    @Override
    public String toString(){
        String str = "Modul " + modul_name + " id " + id;
        return str;
    }

}
