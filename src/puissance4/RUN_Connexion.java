// Runnable gérant l'attente de connexion d'un client au serveur

package puissance4;

import java.io.*;
import java.net.*;


public class RUN_Connexion implements Runnable
{
  
// attributs 
    /*
    * attribut private
    * l'attribut est final 
    * elle définit le ServerSocket de la connexion
    */
    private final ServerSocket server_socket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    // attribut public il indique le thread qui est en attente de la connexion
    public Thread thread_attente_reception;
    // attribut public il indique le thread serveur qui emet
    public Thread thread_attente_Emission;

    /*
        * Constructeur
        * @params ServerSocket ss
        * insert the socket server in run connexion
         
    */
    public RUN_Connexion(ServerSocket ss)
    {
        server_socket = ss;
    }

    @Override
    /*
    *   @return void
    *   The run method is used to start the server connection
    */
    public void run() 
    {	
        try 
        {
            // tant que le client n est pas connecte on ne lance pas la partie 
            while (CONTROLLER_Jeu.client_connecte == false)
            {
                socket = server_socket.accept();
                System.out.println("En attente d'une connexion...  ");
                //FXMLDocumentController.client_connecte = true;
                CONTROLLER_Jeu.client_connecte =true;
                
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Dès qu'une demande de connexion arrive, on lance les Threads d'écoute et de réception.
                thread_attente_reception = new Thread(new RUN_Reception(in));
                thread_attente_reception.start();
                thread_attente_Emission = new Thread(new RUN_Emission(out));
                thread_attente_Emission.start();
            }
        } 
        catch (IOException e) { 
            // erreur on ne retourve pas le serveur 
            System.err.println("Erreur serveur");
        }	
    }
}
