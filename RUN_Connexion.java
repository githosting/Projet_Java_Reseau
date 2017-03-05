
package puissance4;

import java.io.*;
import java.net.*;


public class RUN_Connexion implements Runnable
{
    private final ServerSocket server_socket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public Thread thread_attente_reception;
    public Thread thread_attente_Emission;

    public RUN_Connexion(ServerSocket ss)
    {
        server_socket = ss;
    }

    @Override
    public void run() 
    {	
        try 
        {
            while(FXMLDocumentController.client_connecte == false)
            {
                socket = server_socket.accept();
                System.out.println("En attente d'une connexion...  ");
                FXMLDocumentController.client_connecte = true;
                
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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
