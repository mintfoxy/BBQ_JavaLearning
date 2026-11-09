package data.objekte;

import data.io.FileIO;

/**
 * Mutterklasse aller Objekte in diesem Proekt
 */
public abstract class AObject{

	public static final String KLASSENBUCH = "Klassenbuch.csv";
	private static final String SAVED_MSG = "Der Eintrag wurde gespeichert\n";;
	private int id = -1;


	/**
	 * Jede Tochterklasse muss die Daten, die<br>
	 * gespeichert werden, selbst als String "verpacken"
	 * 
	 * @return daten
	 */
	protected abstract String[] getObjectData();
	
//	public String sqlStatement() {
//		
//		String sql = "insert into " + this.getClass().getName();
//		sql += "(";
//		
////		INSERT INTO `bbq`.`fach` (`bezeichnung`) VALUES ('FI');
//		
//		Field[] fields = this.getClass().getDeclaredFields();
//		for (Field field : fields) {
//			sql += field.getName();
//			
//			
//		}
//		sql += ")";
//		sql += "VALUES";
//		sql += "(";
//		sql += "%s, %s";
//		sql += ")";
//		return sql;
//	}

	/**
	 * Speichert ein Objekt
	 * 
	 * @return Fehlermeldung, wenn das Speichern <br>
	 *         nicht gelingt, sonst null
	 */
	public String save() {

		if (id < 1)
			id = FileIO.getLineCount(KLASSENBUCH) + 1;
		String err = FileIO.writeIntoFile(KLASSENBUCH, getObjectData());
		if (err == null)
			return SAVED_MSG;
		return err;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}