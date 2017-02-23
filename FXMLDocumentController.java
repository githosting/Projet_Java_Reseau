
package puissance4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class FXMLDocumentController implements Initializable {
    
    public static Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;
    public static Thread thread_emission;
    public static Thread thread_reception;
        
        
    @FXML
    private Label label_info;

    @FXML
    private GridPane gp;
    
    // FXML Getters
    public Label getLabelInfo() { return label_info; }
    public GridPane getGridPane() { return gp; }
    
    @FXML
    public void fillGridWithLabels() 
    {
        for (int i = 0 ; i < 30 ; i++) 
        {
            for (int j = 0; j < 30; j++) 
            {
                Label label = new Label("-");
                gp.add(label, i, j);
            }
        }
    }
    
    @FXML
    private void connectClick(ActionEvent event) 
    {
        System.out.println("CLICK");
        
        try 
        {
            System.out.println("Demande de connexion");
            socket = new Socket("127.0.0.1",2013); // Sur le port 2013

            // Si ce message s'affiche c'est que je suis connecté
            System.out.println("Connexion établie avec le serveur."); 

            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
            sc = new Scanner(System.in);

            thread_emission = new Thread(new RUN_Emission(out));
            thread_emission.start();
            thread_reception = new Thread(new RUN_Reception(in));
            thread_reception.start();
        } 
        catch (UnknownHostException e) { System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress()); } 
        catch (IOException e) { System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort()); }
        
        /*
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());

        out.println("Entrez votre login :");
        out.flush();
        login = in.readLine();

        out.println("connecte");
        System.out.println(login +" vient de se connecter ");
        out.flush();
        */
    }
    
    @FXML 
    public void label_infoClick(MouseEvent event)
    {
        System.out.println("CLICK");
    }
    
    @FXML 
    public void gpClick(MouseEvent event)
    {
        System.out.println("CLICK");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // LE CODE ICI S'EXECUTE AVANT LE SHOW DE L'INTERFACE GRAPHIQUE.
 
    }    
    
}
