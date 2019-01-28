package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileSender {
	
	//TODO	Mehrere Files auf einmal versenden und einlesen
	//TODO	GUI mit Namensgebung
	
	private static String fileInputPath = "C:\\Users\\amkor\\Desktop\\test.txt";
	private static String fileOutputPath = "C:\\Users\\amkor\\Desktop\\demo.txt";

	public static void main(String[] args) {

		try {
			// File in .tmp umwandeln und in Anhang anfügen
			createTemporaryFile();
			composeEmail(fileOutputPath);
		} catch (Exception err) {
			err.printStackTrace();
		}
		System.out.println("Done!");
	}

	public static void composeEmail(String attachment) throws Exception {

		boolean thunderbird = false;

		// Run-Befehl je nach verwendetem Mail Programm erzeugen
		String cmd = ""; // TODO Über Input Stream
		String os = System.getProperty("os.name").toLowerCase(); 
		//Thunderbird
		if (os.contains("win") && thunderbird == true) { 
			cmd = "cmd /c start thunderbird.exe -compose attachment='" + "file:///" + attachment + "'";																			// attachment
		} 
		//Outlook
		else if (os.contains("win") && thunderbird == false) { 
			cmd = "cmd /c start outlook.exe /a " + attachment;
		} else {
			System.out.println("Wrong Operation System");
		}

		// Mail-Client mit Parametern starten
		Runtime.getRuntime().exec(cmd);

	}

	public static void createTemporaryFile() throws IOException {
		try {
			InputStream fileInput = new FileInputStream(fileInputPath);
			FileOutputStream tmpFile = new FileOutputStream(new File(fileOutputPath));
			int byteRead;
			while((byteRead = fileInput.read()) != -1) {
				tmpFile.write(byteRead);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not convert " + fileInputPath);
			e.printStackTrace();
		}

	}
}