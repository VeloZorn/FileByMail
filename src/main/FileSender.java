package main;
public class FileSender {


	public static void main(String[] args) {
         
        try {
            //Mailprogramm öffnen mit "Empfänger", "Betreff", "Nachricht"
            composeEmail("C:\\Users\\amkor\\Desktop\\test.txt"); //Daten in Binaries umwandeln
        }
        catch (Exception err) {
          err.printStackTrace();
        }
        System.out.println("Done!");
    }
     
    public static void composeEmail(String attachment) throws Exception {
        
    	boolean thunderbird = false;
    	
        //Run-Befehl je nach Betriebssystem erzeugen
        String cmd = "";
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win") && thunderbird == true){
            cmd = "cmd /c start thunderbird.exe -compose attachment='" + "file:///" + attachment + "'"; //Thunderbird Support
        }
        else if(os.contains("win") && thunderbird == false) {
        	cmd = "cmd /c start outlook.exe /a " + attachment; //Outlook Support
        }
        else {
        	System.out.println("Wrong Operation System");
        }
        
        //Mail-Client mit Parametern starten
        Runtime.getRuntime().exec(cmd);
      
    }
     
    //Derzeit nicht benutzt
    private static String uriEncode(String in) {
        String out = new String();
        for (char ch : in.toCharArray()) {
            out += Character.isLetterOrDigit(ch) ? ch : String.format("%%%02X", (int)ch);
        }
        return out;
    }  
}