
package puissance4.bean;

import java.io.*;
import java.net.*;
import puissance4.controller.CONTROLLER_Jeu;


/**
    * Class implementing Runnable and managing the "wait for a client connection" step for the server.
    * @version 1.0
*/
public class RUN_Connexion implements Runnable
{
    // Attributes 
    public final ServerSocket server_socket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public Thread thread_attente_reception;
    public Thread thread_attente_Emission;


    /**
        * Constructor for RUN_Connexion class.
        * @version 1.0
        * @param ss The server player's network socket.
    */
    public RUN_Connexion(ServerSocket ss)
    {
        server_socket = ss;
    }

    
    /**
        * Start the RUN_Connexion runnable.
        * @version 1.0
        * 
    */
    @Override
    public void run() 
    {	
        try 
        {
            // While no client connected, wait for a client connection to launch the game. 
            while (!CONTROLLER_Jeu.game.isClient_connecte())
            {
                // Listen to connection requests. While no connection, code blocks here.
                socket = server_socket.accept();
                System.out.println("En attente d'une connexion...  ");
                CONTROLLER_Jeu.game.setClient_connecte(true);
                
                // Variables for emitting and receiving messages.
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Launch broadcast and reception Threads.
                thread_attente_reception = new Thread(new RUN_Reception(in));
                thread_attente_reception.start();
                thread_attente_Emission = new Thread(new RUN_Emission(out));
                thread_attente_Emission.start();
            }
        } 
        catch (IOException e) { System.err.println(e.getMessage()); }	
    }
}