
package puissance4;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RUN_Reception implements Runnable 
{
    private final BufferedReader in;
    private String message;

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
        }
    }
}
