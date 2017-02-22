
package puissance4;

import java.io.*;
import java.net.*;


public class RUN_Connexion implements Runnable
{
    private ServerSocket server_socket = null;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    public Thread thread_attente_reception;
    public Thread thread_attente_Emission;

    public RUN_Connexion(ServerSocket ss)
    {
        server_socket = ss;
    }

    public void run() 
    {	
        try 
        {
            while(true)
            {
                socket = server_socket.accept();
                System.out.println("En attente d'une connexion...  ");

                // Dès qu'une demande de connexion arrive, on lance les Threads d'écoute et de réception.
                thread_attente_reception = new Thread(new RUN_Reception(in));
                thread_attente_reception.start();
                thread_attente_Emission = new Thread(new RUN_Emission(out));
                thread_attente_Emission.start();
            }
        } 
        catch (IOException e) { System.err.println("Erreur serveur"); }	
    }
}
