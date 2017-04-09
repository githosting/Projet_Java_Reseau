// Runnable gérant l'envoi de messages sur le réseau

package puissance4;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RUN_Emission implements Runnable 
{
    private final PrintWriter out;
    public String message_JSON_string;
    public static Message message;

    public RUN_Emission(PrintWriter out) 
    {
        this.out = out;
        message = new Message();
    }

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
