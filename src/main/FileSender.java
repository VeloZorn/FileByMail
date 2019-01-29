package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class FileSender {

	// TODO Mehrere Files auf einmal versenden und einlesen || Thunderbird Supported --- Outlook + Windows 10 Mail nicht
	// TODO GUI mit Namensgebung

	private static List<String> fileInputPath = new ArrayList<String>(); // = "C:\\Users\\amkor\\Desktop\\test.txt";
	private static List<String> fileOutputPath = new ArrayList<String>(); // = "C:\\Users\\amkor\\Desktop\\demo.txt";
	private static boolean send;

	public static void main(String[] args) {

		fileInputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Input\\test1.txt");
		fileInputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Input\\test2.txt");
		fileInputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Input\\test3.txt");
		fileInputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Input\\test4.txt");
		fileInputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Input\\test5.txt");
		fileOutputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Output\\demo1.txt");
		fileOutputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Output\\demo2.txt");
		fileOutputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Output\\demo3.txt");
		fileOutputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Output\\demo4.txt");
		fileOutputPath.add("C:\\Users\\amkor\\Desktop\\Test Files\\Output\\demo5.txt");

		try {
			// File in .tmp umwandeln und in Anhang anfügen
			createTemporaryFile();
			composeEmail();
		} catch (Exception err) {
			err.printStackTrace();
		}
		System.out.println("Done!");
	}

	public static void composeEmail() throws Exception {

		boolean thunderbird = false;
		boolean outlook = true;
		String completeAttachment = "";
		send = false;

		// Run-Befehl je nach verwendetem Mail Programm erzeugen
		String cmd = ""; // TODO Über Input Stream
		String os = System.getProperty("os.name").toLowerCase();
		// Thunderbird
		if (os.contains("win") && thunderbird == true && outlook == false) {
			for (int i = 0; i < fileInputPath.size(); i++) {
				if (i < (fileInputPath.size() - 1)) {
					completeAttachment = completeAttachment + "file:///" + fileOutputPath.get(i) + ",";
				} else {
					completeAttachment = completeAttachment + "file:///" + fileOutputPath.get(i);
					cmd = "cmd /c start thunderbird.exe -compose attachment='" + completeAttachment + "'";
					System.out.println(completeAttachment);
				}
			}
		}
		//TODO Outlook || Outlook cmd line switch akzeptiert nicht mehrere Attachments || Mögliche Lösung Zip Archiv
		else if (os.contains("win") && thunderbird == false && outlook == true) {
			String dokumente = "";
			for(String path : fileOutputPath) {
				dokumente += path + ";";
			}
			cmd = "cmd /c start outlook.exe /a " + dokumente;
		}
		else if(os.contains("win") && thunderbird == false && outlook == false){
			
		}

		// Mail-Client mit Parametern starten
		Runtime.getRuntime().exec(cmd);

	}

	public static void createTemporaryFile() throws IOException {
		for (int i = 0; i < fileInputPath.size(); i++) {
			try {
				InputStream fileInput = new FileInputStream(fileInputPath.get(i));
				FileOutputStream tmpFile = new FileOutputStream(new File(fileOutputPath.get(i)));
				int byteRead;
				while ((byteRead = fileInput.read()) != -1) {
					tmpFile.write(byteRead);
				}

			} catch (FileNotFoundException e) {
				System.out.println("Could not convert " + fileInputPath);
				e.printStackTrace();
				send = false;
				break;
			}
			send = true;
		}
	}
}