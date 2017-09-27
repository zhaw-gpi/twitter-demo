package ch.zhaw.sml.iwi.gpi.twitterDemo;

import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * JavaDelegate-Klasse, welche einen Tweet auf https://twitter.com/camunda_demo
 * veröffentlicht
 *
 * @author scep
 */
@Named("sendTweetAdapter")
public class SendTweetDelegate implements JavaDelegate {

    /**
     * Wird aufgerufen bei Aufgabe 'Tweet senden'. Nutzt die Twitter API, um 
     * einen neuen Tweet zu veröffentlichen
     *
     * @param execution Zugriff auf das DelegateExecution-Objekt mit z.B. dem
     * aktuellen Variablen-Zustand
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Zu sendenden Inhalt festlegen
        String content = "Grüsse von der ZHAW: scep";
        
        // Twitter-Library instanzieren
        Twitter twitter = new TwitterFactory().getInstance();
        
        // Bei Twitter AUTHENTIFIZIEREN mit Consumer Key und Consumer Secret
        twitter.setOAuthConsumer("lRhS80iIXXQtm6LM03awjvrvk", "gabtxwW8lnSL9yQUNdzAfgBOgIMSRqh7MegQs79GlKVWF36qLS");
        
        // Bei Twitter für das Senden von Tweets AUTHORISIEREN mit entsprechendem Access Token
        AccessToken accessToken = new AccessToken("220324559-jet1dkzhSOeDWdaclI48z5txJRFLCnLOK45qStvo", "B28Ze8VDucBdiE38aVQqTxOyPc7eHunxBVv7XgGim4say");
        twitter.setOAuthAccessToken(accessToken);
        
        // Sendet den Content an Twitter (bei Twitter heisst dies, dass der
        // Status aktualisiert wird, ausser wenn er bereits gleich ist)
        twitter.updateStatus(content);
        
    }

}
