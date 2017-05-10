
package puissance4.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import puissance4.bean.Validation;
import puissance4.bean.INTERFACE_Screen;
import puissance4.bean.Puissance4;
import puissance4.bean.RUN_Connexion;
import puissance4.bean.RUN_Emission;
import puissance4.bean.RUN_Reception;


/**
    * Class acting as a controller for view VUE_Connexion_Client events.
    * @version 1.0
    */
public class CONTROLLER_Connexion_Client implements Initializable,INTERFACE_Screen {
    
    // Attributes 
    public static Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public static ServerSocket server_socket;
    public static Thread thread_attente_connexion;
    public static Thread thread_emission;
    public static Thread thread_reception;
    public static RUN_Connexion runnable_connexion;
    public static RUN_Emission runnable_emission;
    public static RUN_Reception runnable_reception;
    
    // indicate the type of player server or client
    public static String player_type;
    
    // boolean true if its the player is playing
    public boolean my_turn = false;
    
    // pseudo of client player
    public String pseudo;
    public boolean game_start = false;
    CONTROLLER_Super mycontroller;
    
    // FXML Attributes
    @FXML
    // input the pseudo of client player
    private TextField textfield_pseudo;
    
    @FXML
    // input adresse server connection
    private TextField textfield_adresse;
    
    @FXML
    // input port of server
    private TextField textfield_port;
    
    @FXML
    // launch connection for the player client
    private Button button_connect;
    
    @FXML
    // title of page 
    public Label label_info;
 
    @FXML
    // grid of game
    private GridPane gp;

    // Accessors
    /**
     * @params null
     * @return Label
     */
    public Label getLabelInfo() { 
        return label_info; 
    }
    
    /**
     * getter the gridpane
     * @return GridPane
     */
    public GridPane getGridPane() {
        return gp;
    }
    
    /**
     * 
     * set screen parent.
     * @version 1.0
     * @param screenParent 
     * @return void
    */
    @Override
    public void setScreenParent(CONTROLLER_Super screenParent) {
        mycontroller = screenParent;
    }
    
    /**
        * Execute code when the view is displayed.
        * @version 1.0
        * @param URL location
        * @param ResourceBundle resources
        * @return void
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textfield_adresse.setText("127.0.0.1");
        textfield_port.setText("4200");
    }

    /**
        * Redirect to the main interface.
        * @version 1.0
        * @param ActionEvent event
        * @return void
    */
    @FXML
    private void goToHome (ActionEvent event){
        mycontroller.setScreen(Puissance4.MAIN_SCREEN);
    }
        
    /**
        * Manage a client connection to a game.
        * @version 2.0
        * @param ActionEvent event
        * @return void
        * @throws MalformedURLException
    */
    @FXML
    private void btn_connect(ActionEvent event) throws MalformedURLException 
    {
        // Checking user inputs.
        Validation.validationPseudo(textfield_pseudo.getText());
        Validation.validationIp(textfield_adresse.getText());
        Validation.validationPort(textfield_port.getText());
        // If no errors detected.
        if(Validation.afficheErreur().isEmpty()){
            pseudo = textfield_pseudo.getText();
            player_type = "client";
            CONTROLLER_Jeu.game.setInformationPlayer(player_type, pseudo);
        
            try 
            {
                label_info.setText("Demande de connexion...");
                String adresse_serveur = textfield_adresse.getText();
                int port_serveur = Integer.parseInt(textfield_port.getText());
                socket = new Socket(adresse_serveur, port_serveur);
                // If this message is displayed, then the client is connected to the server.
                label_info.setText("Connecté au serveur.");
                game_start = true;
                CONTROLLER_Jeu.game.setGame_start(game_start);
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	

                // Launching broadcast and reception Threads.
                thread_emission = new Thread(new RUN_Emission(out));
                thread_emission.start();
                thread_reception = new Thread(new RUN_Reception(in));
                thread_reception.start();
            } 
            catch (UnknownHostException e) { 
                System.err.println("BUG : Connexion impossible à l'adresse "+socket.getLocalAddress()); 
            } 
            catch (IOException e) { 
                System.err.println("BUG : Pas de serveur à l'écoute du port "+socket.getLocalPort()); 
            }
            
            // Redirect the player to the game screen. 
            this.mycontroller.loadScreen(Puissance4.GAME_SCREEN, Puissance4.GAME_SCREEN_FXML); 
            this.mycontroller.setScreen(Puissance4.GAME_SCREEN);
        }
    }
}
