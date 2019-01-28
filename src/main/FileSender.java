package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileSender {
	
	private static String fileAttachment = "C:\\Users\\amkor\\Desktop\\test.txt";

	public static void main(String[] args) {

		try {
			// File in .tmp umwandeln und in Anhang anfügen
			createTemporaryFile();
			composeEmail(fileAttachment);
		} catch (Exception err) {
			err.printStackTrace();
		}
		System.out.println("Done!");
	}

	public static void composeEmail(String attachment) throws Exception {

		boolean thunderbird = false;

		// Run-Befehl je nach verwendetem Mail Programm erzeugen
		String cmd = ""; // TODO Über Input Stream
		String os = System.getProperty("os.name").toLowerCase(); //
		if (os.contains("win") && thunderbird == true) { //
			cmd = "cmd /c start thunderbird.exe -compose attachment='" + "file:///" + attachment + "'"; // Thunderbird
																										// Support
																										// //TODO Create
																										// Temp file as
																										// attachment
		} //
		else if (os.contains("win") && thunderbird == false) { //
			cmd = "cmd /c start outlook.exe /a " + attachment; // Outlook Support //TODO Create Temp file as attachment
		} else {
			System.out.println("Wrong Operation System");
		}

		// Mail-Client mit Parametern starten
		Runtime.getRuntime().exec(cmd);

	}

	public static File createTemporaryFile() throws IOException {
		String[] input = fileAttachment.split("\\\\");
		String filePrefix = input[(input.length - 1)];
		String fileSuffix = ".tmp";
		System.out.println(filePrefix);
		
		try {
			InputStream fileInput = new FileInputStream(fileAttachment);
			File tmpFile = File.createTempFile(filePrefix, fileSuffix, fileAttachment);
		} catch (FileNotFoundException e) {
			System.out.println("Could not convert " + fileAttachment);
			e.printStackTrace();
		}
		
		return null;

	}

	public static final String PREFIX = "stream2file";
	public static final String SUFFIX = ".tmp";

	public File stream2file(InputStream in) throws IOException {
		final File tempFile = File.createTempFile(PREFIX, SUFFIX);
		tempFile.deleteOnExit();
		try (FileOutputStream out = new FileOutputStream(tempFile)) {
			IOUtils.copy(in, out);
		}
		return tempFile;
	}
}