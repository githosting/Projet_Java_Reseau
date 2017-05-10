
package puissance4.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
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
    * Class acting as a controller for view VUE_Connexion_Server events.
    * @version 1.0
*/
public class CONTROLLER_Connexion_Server implements Initializable,INTERFACE_Screen {
    
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
    
    // input the port of server game
    public static String port_serveur;
    
    // type of player client or server
    public static String player_type;
    
    // image of one player
    public String image_path;
    
    // image of adversaire
    public String image_path_adversaire;
    
    // pseudo of player server
    public String pseudo;
    
    // color of player
    public String player_color = "";
    
    // boolean true if its is playing
    public boolean my_turn = false;
    CONTROLLER_Super mycontroller;
    
    // FXML Attributes
    @FXML
    // this textField represent the pseudo of server
    private TextField textfield_pseudo;
    
    @FXML
    // this textField represent the adresse of server
    private TextField textfield_adresse; 
    
    @FXML
    // this textField represent the port of game
    private TextField textfield_port;
    
    @FXML
    // button to launch game
    private Button button_server;
    
    @FXML
    // title of page
    public Label label_info;
    
    @FXML
    // grid of game
    private GridPane gp;
    

    // Accessors
    public Label getLabelInfo() { 
        return label_info; 
    }

    public GridPane getGridPane() {
        return gp;
    }
    
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
        // fill the input of connexion server
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
        * Manage a game creation by a server player.
        * @version 2.0
        * @param ActionEvent event
        * @return void
        * @throws MalformedURLException
    */
    @FXML
    private void btn_server(ActionEvent event) throws MalformedURLException 
    {
        // Check user inputs.
        Validation.validationPseudo(textfield_pseudo.getText());
        Validation.validationIp(textfield_adresse.getText());
        Validation.validationPort(textfield_port.getText());
        // If no errors detected.
        if(Validation.afficheErreur().isEmpty()){   
           pseudo = textfield_pseudo.getText();
           player_type = "server";
           my_turn = true;
          // CONTROLLER_Jeu.setTurn(my_turn);
          // CONTROLLER_Jeu.setInformationPlayer("server", pseudo);
             CONTROLLER_Jeu.game.setMy_turn(my_turn);
             CONTROLLER_Jeu.game.setInformationPlayer("server", pseudo);
           try 
            {
                int port_serveur = Integer.parseInt(textfield_port.getText());
                CONTROLLER_Connexion_Server.port_serveur = textfield_port.getText();
                server_socket = new ServerSocket(port_serveur);
                label_info.setText("Port "+server_socket.getLocalPort()+" écouté...");

                // Launch broadcast and reception Threads.
                thread_attente_connexion = new Thread(new RUN_Connexion(server_socket));
                thread_attente_connexion.start(); 
                CONTROLLER_Jeu.game.setInformationPlayer("server", pseudo);
            }
            catch (IOException e) { 
                System.err.println("BUG : Port "+server_socket.getLocalPort()+" déjà utilisé."); 
            }
            // Redirect the player to the game screen. 
            this.mycontroller.loadScreen(Puissance4.GAME_SCREEN, Puissance4.GAME_SCREEN_FXML); 
            this.mycontroller.setScreen(Puissance4.GAME_SCREEN);
        } 
    }
}
