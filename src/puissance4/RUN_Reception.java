
package puissance4;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.SocketException;
import javafx.application.Platform;


/**
    * Class implementing Runnable and managing message receptions from the network.
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
        * Constructor for RUN_Reception class.
        * @author Bettinger
        * @version 2.0
        * @param BufferedReader in
        *       Class attribute to receive content from the network.
        * @return A RUN_Reception instance
        */
    public RUN_Reception(BufferedReader in)
    {
        this.in = in;
        message = new Message();
    }

    
    /**
        * Start the RUN_Reception runnable.
        * @author Bettinger
        * @version 3.0
        * @param No Parameters
        * @return void
        */
    @Override
    public void run() 
    {          
        // While the game is running.
        while (CONTROLLER_Jeu.game_over == false)
        {
            try 
            {
                // Listen the network and get Message string from it.
                message_JSON_string = in.readLine();
                
                // Deserialisation of the string Message into a proper Message object.
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                message = objectMapper.readValue(message_JSON_string, Message.class);
            } 
            catch (SocketException e) 
            {
                // Means that the other player has gone offline so we restart the application.
                try {Process process = Runtime.getRuntime().exec("java -jar ./dist/Puissance4.jar");} 
                catch (IOException ex) {String io_Exception = "IO Exception";}
                Platform.exit();
                System.exit(0);
            }
            catch (IOException ex) { Logger.getLogger(RUN_Reception.class.getName()).log(Level.SEVERE, null, ex); }
            
            System.out.println(message);
            
            // Make a brief pause.
            try { Thread.sleep(100); }
            catch (Exception e) {  }
        }
    }
}
