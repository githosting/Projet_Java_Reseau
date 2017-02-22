
package puissance4;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class RUN_Emission implements Runnable 
{
    private PrintWriter out;
    private String message = null;
    private Scanner scanner = null;

    public RUN_Emission(PrintWriter out) 
    {
        this.out = out;
    }

    public void run() 
    {
        scanner = new Scanner(System.in);

        while(true)
        {
            System.out.println("Votre message :");
            message = scanner.nextLine();
            out.println(message);
            out.flush();
        }
    }
}
