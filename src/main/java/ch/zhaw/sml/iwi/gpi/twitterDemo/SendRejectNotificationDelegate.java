package ch.zhaw.sml.iwi.gpi.twitterDemo;

import javax.inject.Named;
import org.apache.commons.mail.EmailException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * JavaDelegate-Klasse, welche eine Ablehnung per Mail an den Autor eines 
 * abgelehnten Tweets sendet
 * 
 * @author scep
 */
@Named("sendRejectNotificationAdapter")
public class SendRejectNotificationDelegate implements JavaDelegate {

    /**
     * Wird aufgerufen bei Aufgabe 'Ablehnung an Autor senden'. Nutzt die
     * SimpleEmail-Library, um eine Nachricht per Mail zu versenden.
     * 
     * @param execution
     * @throws Exception 
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Tweet auslesen
        String tweetContent = (String) execution.getVariable("tweetContent");
        
        // Ablehnungsgrund auslesen
        String rejectNotification = (String) execution.getVariable("rejectNotification");
        
        // Mail-Text zusammenbauen
        String mailContent = "Hallo\n\n" +
                "Sie möchten folgenden Tweet veröffentlichen: " + tweetContent +
                "\n\nDies kann aus dem folgenden Grund nicht bewilligt werden: "
                + rejectNotification + "\n\nVersuchen Sie es erneut mit einem "
                + "besseren Text.\n\nIhre Kommunikationsabteilung";
        
        // E-Mail-Adresse auslesen
        String email = (String) execution.getVariable("email");
        
        // Neue Zhaw-Mail-Instanz erstellen (Konstruktur-Methode aufrufen)
        ZhawEmail zhawEmail = new ZhawEmail();
        
        try{
            // Empfänger setzen
            zhawEmail.addTo(email);
            
            // Absender setzen
            zhawEmail.setFrom("scep@zhaw.ch");
            
            // Subjekt setzen
            zhawEmail.setSubject("Ablehnung für Tweet-Veröffentlichung");
            
            // Inhalt setzen
            zhawEmail.setMsg(mailContent);
            
            // Mail senden
            zhawEmail.send();
        } catch (EmailException e) {
            // Fehler in Konsole ausgeben
            System.out.println(e.getMessage());
        }
    }
    
}
