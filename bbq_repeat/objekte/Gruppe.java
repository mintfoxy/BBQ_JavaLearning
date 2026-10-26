package bbq_repeat.objekte;

public class Gruppe {

    private int id;
    private String gruppe_name;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getGruppe_name(){
        return gruppe_name;

    }

    public void setGruppe_Name(String gruppe_name){
        this.gruppe_name = gruppe_name;
    }

    public Gruppe(){

    }

    public Gruppe(int id, String gruppe_name){
        super();
        this.id = id;
        this.gruppe_name = gruppe_name;
    }

    @Override
    public String toString(){
        String str = "Gruppe " + gruppe_name + " id " + id;
        return str;
    }

}
