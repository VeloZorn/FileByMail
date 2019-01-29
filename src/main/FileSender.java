package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

public class FileSender {

	//TODO Datei muss nach Senden gelöscht werden 
	
	private static List<String> fileInputPath = new ArrayList<String>();
	private static List<String> fileOutputPath = new ArrayList<String>();
	private static String zipName = "Zip";
	private static String zipOut = "C:\\Users\\amkor\\Desktop\\Test Files\\Output\\" + zipName + ".zip";

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
			//createTemporaryFile();
			zipOutput();
			composeEmail();
			File deleteFile = new File(zipOut);
			//Thread.sleep(10000);
			//deleteFile.delete();
		} catch (Exception err) {
			err.printStackTrace();
		}
		System.out.println("Done!");
	}

	public static void composeEmail() throws Exception {

		boolean thunderbird = true;
		boolean outlook = false;
		String completeAttachment = "";

		// Run-Befehl je nach verwendetem Mail Programm erzeugen
		String cmd = ""; // TODO Über Input Stream
		String os = System.getProperty("os.name").toLowerCase();
		// Thunderbird
		if (os.contains("win") && thunderbird == true && outlook == false) {
			cmd = "cmd /c start thunderbird.exe -compose attachment='" + "file:///" + zipOut + "'";
		}
		else if (os.contains("win") && thunderbird == false && outlook == true) {
			cmd = "cmd /c start outlook.exe /a " + '"' + zipOut + '"';
		} else if (os.contains("win") && thunderbird == false && outlook == false) {

		}

		// Mail-Client mit Parametern starten
		Runtime.getRuntime().exec(cmd);

	}

	/*
	 * public static void createTemporaryFile() throws IOException { for (int i = 0;
	 * i < fileInputPath.size(); i++) { try { InputStream fileInput = new
	 * FileInputStream(fileInputPath.get(i)); FileOutputStream tmpFile = new
	 * FileOutputStream(new File(fileOutputPath.get(i))); int byteRead; while
	 * ((byteRead = fileInput.read()) != -1) { tmpFile.write(byteRead); }
	 * 
	 * } catch (FileNotFoundException e) { System.out.println("Could not convert " +
	 * fileInputPath); e.printStackTrace(); break; } } }
	 */

	public static void zipOutput() {

		try {

			byte[] buffer = new byte[1024];

			FileOutputStream outFile = new FileOutputStream(zipOut);
			ZipOutputStream outZip = new ZipOutputStream(outFile);

			for (int i = 0; i < fileInputPath.size(); i++) {

				File srcFile = new File(fileInputPath.get(i));
				FileInputStream inFile = new FileInputStream(srcFile);
				
				outZip.putNextEntry(new ZipEntry(srcFile.getName()));
				
				int j;
				while((j = inFile.read(buffer)) > 0) {
					outZip.write(buffer, 0, j);
				}
				outZip.closeEntry();
				inFile.close();
			}
			outZip.close();
			
			
		} catch (IOException e) {
			System.out.println("Fehler");
		}
	}
}