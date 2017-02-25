
package puissance4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class FXMLDocumentController implements Initializable {
    
    public static Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner sc;
    private String message;
    private Scanner scanner;
    public static ServerSocket server_socket;
    public static Thread thread_attente_connexion;
    public static Thread thread_emission;
    public static Thread thread_reception;
    public String player_color = "";
    public boolean my_turn = false;
    
        
    @FXML
    private TextField textfield_pseudo;
    @FXML
    private TextField textfield_adresse; 
    @FXML
    private TextField textfield_port; 
    @FXML
    public Label label_info;
    @FXML
    private GridPane gp;
    
    // FXML Getters
    public Label getLabelInfo() { return label_info; }
    public GridPane getGridPane() { return gp; }

    
    @FXML
    public void refreshGrid(String message) 
    {
        label_info.setText(label_info.getText()+" KIKOU !");
    }
    
    
    @FXML
    private void btn_connect(ActionEvent event) 
    {
        player_color = "green";
        try 
        {
            label_info.setText("Demande de connexion...");
            
            String adresse_serveur = textfield_adresse.getText();
            int port_serveur = Integer.parseInt(textfield_port.getText());
            
            socket = new Socket(adresse_serveur,port_serveur);

            // Si ce message s'affiche c'est que je suis connecté
            label_info.setText("Connecté au serveur.");

            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	

            thread_emission = new Thread(new RUN_Emission(out));
            thread_emission.start();
            thread_reception = new Thread(new RUN_Reception(in));
            thread_reception.start();
        } 
        catch (UnknownHostException e) { System.err.println("BUG : Connexion impossible à l'adresse "+socket.getLocalAddress()); } 
        catch (IOException e) { System.err.println("BUG : Pas de serveur à l'écoute du port "+socket.getLocalPort()); }
        
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
    private void btn_server(ActionEvent event) 
    {
        player_color = "orange";
        my_turn = true;
        try 
        {
            int port_serveur = Integer.parseInt(textfield_port.getText());
            server_socket = new ServerSocket(port_serveur);
            label_info.setText("Port "+server_socket.getLocalPort()+" écouté...");

            thread_attente_connexion = new Thread(new RUN_Connexion(server_socket));
            thread_attente_connexion.start();     
        } 
        catch (IOException e) { System.err.println("BUG : Port "+server_socket.getLocalPort()+" déjà utilisé."); }
        
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
        
        RUN_Emission.message = "CLICK from server";
    }
    
    @FXML 
    public void cellClick(MouseEvent event)
    {
        Node source = (Node)event.getSource() ;
        
        Platform.runLater(() -> {
            
            if(source.getStyle().length() == 0)
                source.setStyle("-fx-background-color: green;");
            else
                source.setStyle("");
            
        });
        
        
        
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
        
        // On update le message à envoyer.
        RUN_Emission.message = TABLEAU DES DONNEES;
    }
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // LE CODE ICI S'EXECUTE AVANT LE SHOW DE L'INTERFACE GRAPHIQUE.
 
    }    
    
}
