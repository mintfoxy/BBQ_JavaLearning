package bbq.utils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Aufgabe 7
 * Schreibe eine Klasse, die Strings in eine Datei schreibt.
 */

public class FileIO {
	
	private static final String NL = "\n";
	private static final String SUCCESS_MSG = "gespeichert";
	
	/**
	 * 
	 * @param fileName	ist Pfad der Datei
	 * @param data
	 * @return
	 */
	public static String writeIntoFile(String fileName, String data) {
		
		try (FileWriter fileWriter = new FileWriter(fileName, true)){
					
			fileWriter.write(data + NL);
			return SUCCESS_MSG;
			
		} catch (IOException e) {			
			return e.getLocalizedMessage();
		}
		
	}
	
//	public static void main(String[] args) {
//		
//		String msg;
//		msg = writeIntoFile("./data/Test", "aaaaa");
//		IO.println(msg);
//		
//		IO.println(writeIntoFile("./data/Test", "bbbbb"));
//		IO.println(writeIntoFile("./data/Test", "ccccc"));
//	  }

}