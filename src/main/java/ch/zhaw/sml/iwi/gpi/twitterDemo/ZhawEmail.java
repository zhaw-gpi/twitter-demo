package ch.zhaw.sml.iwi.gpi.twitterDemo;

import org.apache.commons.mail.SimpleEmail;

/**
 * Ermöglicht den Versand von Emails aus dem ZHAW-Netzwerk heraus mit dem 
 * ZHAW Mail-Server
 * 
 * @author scep
 */
public class ZhawEmail extends SimpleEmail{

    /**
     * Constructor-Methode, in welcher zwei erforderliche Default-Werte
     * (Zeichensatz und Host) geändert werden. Alle übrigen Default-Werte sind
     * bereits passend.
     */
    public ZhawEmail() {
        // Zeichensatz auf UTF-8 setzen
        this.setCharset("utf-8");
        
        // Mailserver der ZHAW setzen
        this.setHostName("smtp.zhaw.ch");
    }
    
}
