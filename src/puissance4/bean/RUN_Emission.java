
package puissance4.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import puissance4.controller.CONTROLLER_Jeu;


/**
    * Class implementing Runnable and managing message broadcasts on the network.
    * @version 3.0
*/
public class RUN_Emission implements Runnable 
{
    // Attributes
    private final PrintWriter out;
    public String message_JSON_string;
    public static Message message;

    /**
        * Constructor for RUN_Emission class.
        * @version 2.0
        * @param out Class attribute to broadcast content on the network.
    */
    public RUN_Emission(PrintWriter out) 
    {
        this.out = out;
        message = new Message();
    }

    
    /**
        * Start the RUN_Emission runnable.
        * @version 3.0
        * 
    */
    @Override
    public void run() 
    {
        // While game is running.
        while (!CONTROLLER_Jeu.game.isGame_over())
        {
            if(message.pseudo != null)
            {
                try {
                    // Serialisation of the Message object into a JSON_string.
                    ObjectMapper mapper = new ObjectMapper();
                    message_JSON_string = mapper.writeValueAsString(message);
                    
                    // Sending the serialised Message object.
                    out.println(message_JSON_string);
                    out.flush();
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(RUN_Emission.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Make a brief pause.
            try { Thread.sleep(100); }
            catch (Exception e) {  }
        }
    }
}
