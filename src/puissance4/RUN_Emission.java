
package puissance4;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
    * Class implementing Runnable and managing message broadcasts on the network.
    * @author Bettinger
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
        * @author Bettinger
        * @version 2.0
        * @param PrintWriter out
        *       Class attribute to broadcast content on the network.
        * @return A RUN_Emission instance
        */
    public RUN_Emission(PrintWriter out) 
    {
        this.out = out;
        message = new Message();
    }

    
    /**
        * Start the RUN_Emission runnable.
        * @author Bettinger
        * @version 3.0
        * @param No Parameters
        * @return void
        */
    @Override
    public void run() 
    {
        // While game is running.
        while (CONTROLLER_Jeu.game_over == false)
        {
            if(message.pseudo != null)
            {
                // Serialisation of the Message object into a JSON_string.
                ObjectMapper mapper = new ObjectMapper();
                try 
                {
                    message_JSON_string = mapper.writeValueAsString(message);
                } 
                catch (JsonProcessingException ex) { Logger.getLogger(RUN_Emission.class.getName()).log(Level.SEVERE, null, ex); }
                
                // Sending the serialised Message object.
                out.println(message_JSON_string);
                out.flush();
            }
            
            // Make a brief pause.
            try { Thread.sleep(100); }
            catch (Exception e) {  }
        }
    }
}
