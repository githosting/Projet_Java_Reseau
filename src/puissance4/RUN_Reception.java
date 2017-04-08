// package 
package puissance4;

//imporations
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


public class RUN_Reception implements Runnable 
{
    private final BufferedReader in;
    public String message_JSON_string;
    public static Message message;
    
    @FXML
    private Label label_info;

    public RUN_Reception(BufferedReader in)
    {
        this.in = in;
        message = new Message();
    }

    @Override
    public void run() 
    {          
        //while(FXMLDocumentController.game_over == false)
        while (GameScreen.game_over == false)
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
            catch (IOException ex) { Logger.getLogger(RUN_Reception.class.getName()).log(Level.SEVERE, null, ex); }
            
            System.out.println(message);
            
            // On fait une pause
            try { Thread.sleep(1000); }
            catch (Exception e) {  }
        }
    }
}
