
package puissance4;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
    * Classe implémentant Runnable et gérant l'émission de messages sur le réseau.
    * @author Bettinger
    * @version 3.0
    */
public class RUN_Emission implements Runnable 
{
    private final PrintWriter out;
    public String message_JSON_string;
    public static Message message;

    /**
        * Constructeur de la classe RUN_Emission.
        * @author Bettinger
        * @version 2.0
        * @param PrintWriter out
        *       Attribut de la classe pour l'émission de contenu sur le réseau.
        * @return Une instance de RUN_Emission
        */
    public RUN_Emission(PrintWriter out) 
    {
        this.out = out;
        message = new Message();
    }

    
    /**
        * Démarre le runnable RUN_Emission.
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
            if(message.pseudo != null)
            {
                // Sérialisation de l'objet message en JSON_string.
                ObjectMapper mapper = new ObjectMapper();
                try 
                {
                    message_JSON_string = mapper.writeValueAsString(message);
                } 
                catch (JsonProcessingException ex) { Logger.getLogger(RUN_Emission.class.getName()).log(Level.SEVERE, null, ex); }
                
                // Envoi du message.
                out.println(message_JSON_string);
                out.flush();
            }
            
            // On fait une pause
            try { Thread.sleep(100); }
            catch (Exception e) {  }
        }
    }
}
