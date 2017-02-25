
package puissance4;


//import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class RUN_Emission implements Runnable 
{
    private final PrintWriter out;
    public static String message = "truc";
    private Scanner scanner;

    public RUN_Emission(PrintWriter out) 
    {
        this.out = out;
    }

    @Override
    public void run() 
    {
        scanner = new Scanner(System.in);

        while(true)
        {
            //System.out.println("Votre message :");
            //message = scanner.nextLine();
            out.println(message);
            out.flush();
            
            // On fait une pause
                try { Thread.sleep(1000); }
                catch (Exception e) {  }
        }
    }
}
