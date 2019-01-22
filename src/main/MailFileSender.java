package main;
public class MailFileSender {
 
    public static void main(String[] args) {
         
        try {
            //Mailprogramm öffnen mit "Empfänger", "Betreff", "Nachricht", "Anhang"
            composeEmail("test@mail.com",
                         "Betreff",
                         "Mail Text",
                         "D:/test.txt" //TODO importiert file nicht in MAil system
                         );
        }
        catch (Exception err) {
          err.printStackTrace();
        }
        System.out.println("Done!");
    }
     
    public static void composeEmail(String receiver, String subject, String body, String attachment) throws Exception {
        //WIndows File Path konvertieren
    	attachment = attachment.replace("\\", "/");
    	
    	//Mailto-URI zusammensetzen. Betreff und Body müssen encodiert werden      
        String mailto = "mailto:" + receiver;       
        mailto += "?subject=" + uriEncode(subject);
        mailto += "&body=" + uriEncode(body);
        mailto += "&attachment=" + uriEncode(attachment);
         
        //Run-Befehl je nach Betriebssystem erzeugen
        String cmd = "";
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            cmd = "cmd.exe /c start \"\" \"" + mailto + "\"";
        }
        else if (os.contains("osx")){
            cmd = "open " + mailto;
        }      
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            cmd = "xdg-open " + mailto;
        }
        //Mail-Client mit Parametern starten
        Runtime.getRuntime().exec(cmd);
      
    }
     
    private static String uriEncode(String in) {
        String out = new String();
        for (char ch : in.toCharArray()) {
            out += Character.isLetterOrDigit(ch) ? ch : String.format("%%%02X", (int)ch);
        }
        return out;
    }  
}