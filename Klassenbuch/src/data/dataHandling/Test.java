package data.dataHandling;

public class Test {

	public static void main(String[] args) {
		
		Aufgabe a = new Aufgabe();
		a.setId(1);
		a.setNummer(12);
		a.setThema("thema");
		a.setText("text text");
	
		HashMap<String, Object> map = AObjectFactory.toHashMap(a);
		IO.println(map);
		
		
		a = AObjectFactory.createFromMap(map);
		
		IO.println("created");
		IO.println(a);
		
		
//		FileDataManager manager = new FileDataManager();
//		List<Aufgabe> aufgaben = manager.ladeAlleAufgaben();
//		
//		for (Aufgabe aufgabe : aufgaben) {
//			IO.println(aufgabe);
//		}
	}

}