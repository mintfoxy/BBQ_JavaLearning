package bbq.objekte;

public class Azubi {

    int id;
    String name;
    String vorname;
    int id_spezialisierung;
    int id_fach;
    int id_gruppe;

    public Azubi(){

    }

    public Azubi(int id, String name, String vorname, int id_fach, int id_spezialisierung, int id_gruppe){
        super();
        this.id = id;
        this.name = name;
        this.vorname = vorname;
        this.id_fach = id_fach;
        this.id_spezialisierung = id_spezialisierung;
        this.id_gruppe = id_gruppe;
    }

    @Override
    public String toString(){
        String str = "Azubi " + vorname + name + "\n";
        return str;
    }


}
