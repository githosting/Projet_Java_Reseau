
package puissance4;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;


public class RUN_Reception implements Runnable 
{
    private final BufferedReader in;
    public static String message;
    
    
    @FXML
    private Label label_info;

    public RUN_Reception(BufferedReader in)
    {
        this.in = in;
    }

    @Override
    public void run() 
    {        
       
        while(true)
        {
            try 
            {
                message = in.readLine();
            } 
            catch (IOException ex) { Logger.getLogger(RUN_Reception.class.getName()).log(Level.SEVERE, null, ex); }
            System.out.println(message);
            
            // On fait une pause
                try { Thread.sleep(1000); }
                catch (Exception e) {  }
        }
    }
}
