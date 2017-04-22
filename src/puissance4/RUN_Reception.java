
package puissance4;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import static puissance4.RUN_Emission.message;
import java.net.SocketException;
import javafx.application.Platform;


/**
    * Classe implémentant Runnable et gérant la réception de messages en provenance du réseau.
    * @author Bettinger
    * @version 3.0
    */
public class RUN_Reception implements Runnable 
{
    private final BufferedReader in;
    public String message_JSON_string;
    public static Message message;
    
    @FXML
    private Label label_info;

    /**
        * Constructeur de la classe RUN_Reception.
        * @author Bettinger
        * @version 2.0
        * @param BufferedReader in
        *       Attribut de la classe pour la réception de contenu en provenance du réseau.
        * @return Une instance de RUN_Reception
        */
    public RUN_Reception(BufferedReader in)
    {
        this.in = in;
        message = new Message();
    }

    
    /**
        * Démarre le runnable RUN_Reception.
        * @author Bettinger
        * @version 3.0
        * @param No Parameters
        * @return void
        */
    @Override
    public void run() 
    {          
        //while(FXMLDocumentController.game_over == false)
        while (CONTROLLER_Jeu.game_over == false)
        {
            try 
            {
                message_JSON_string = in.readLine();
                
                // Désérialisation du message en un objet Message.
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                message = objectMapper.readValue(message_JSON_string, Message.class);

                
                
                //System.out.println(mapper.readValue(message_JSON_string, Message.class));
                
                //message = A FAIRE
            } 
            catch (SocketException e) 
            {
                try {Process process = Runtime.getRuntime().exec("java -jar ./dist/Puissance4.jar");} 
                catch (IOException ex) {String io_Exception = "IO Exception";}
                Platform.exit();
                System.exit(0);
            }
            catch (IOException ex) { Logger.getLogger(RUN_Reception.class.getName()).log(Level.SEVERE, null, ex); }
            
            System.out.println(message);
            
            // On fait une pause
            try { Thread.sleep(100); }
            catch (Exception e) {  }
        }
    }
}
