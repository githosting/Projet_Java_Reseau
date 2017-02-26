
package puissance4;


//import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.scene.Node;


public class RUN_Emission implements Runnable 
{
    private final PrintWriter out;
    public static String message;
    private Scanner scanner;

    public RUN_Emission(PrintWriter out) 
    {
        this.out = out;
    }

    @Override
    public void run() 
    {
        while(true)
        {
            if(message != null && message.length() > 0)
            {
                out.println(message);
                out.flush();
            }
            
            // On fait une pause
            try { Thread.sleep(1000); }
            catch (Exception e) {  }
        }
    }
}
