// Contrôleur de la vue VUE_Connexion_Server

package puissance4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import puissance4.INTERFACE_Screen;
import puissance4.CONTROLLER_Super;


public class CONTROLLER_Connexion_Server implements Initializable,INTERFACE_Screen {
    
    
    public static Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public static String player_type;
    public static ServerSocket server_socket;
    public static Thread thread_attente_connexion;
    public static Thread thread_emission;
    public static Thread thread_reception;
    public static RUN_Connexion runnable_connexion;
    public static RUN_Emission runnable_emission;
    public static RUN_Reception runnable_reception;
    
     // FXML Controls
    // FXML du textfield qui recupere le pseudo du serveur
    @FXML
    private TextField textfield_pseudo;
    // FXML textfield qui recupere l'adresse ip du serveur
    @FXML
    private TextField textfield_adresse; 
    // FXML textField qui recupere le port ou va se deroule la partie 
    @FXML
    private TextField textfield_port;
    /**
     * emplacement pour l'affichage des erreurs
     */
    @FXML
    protected  Label label_erreurs;
    // FXML on recuepere le bouton pour lancer la connexion
    @FXML
    private Button button_server; 
    @FXML
    // FXML label qui informe des donnees du jeu 
    public Label label_info;
    //FXML la grille de jeu 
    @FXML
    private GridPane gp;
    
    public String image_path;
    public String image_path_adversaire;
    // public string cest le pseudo du joueur serveur 
    public String pseudo;
    // public string cest la couleur du joueur serveur 
    public String player_color = "";
    // boolean qui indique qui doit jouer ici false donc ce nest pas au joueur serveur a jouer
    public boolean my_turn = false;
    
    CONTROLLER_Super mycontroller;
    
    // FXML Getters
    public Label getLabelInfo() { return label_info; }
    public GridPane getGridPane() { return gp; }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textfield_adresse.setText("127.0.0.1");
        textfield_port.setText("4200");
       
    }

    @Override
    public void setScreenParent(CONTROLLER_Super screenParent) {
        mycontroller = screenParent;
    }
    
    // retourne a l'ecran de démarrage lors d'un action event 
    @FXML
    private void goToHome (ActionEvent event){
        mycontroller.setScreen(Puissance4.MAIN_SCREEN);
    }
    
    
    @FXML
    private void btn_server(ActionEvent event) throws MalformedURLException 
    {
        //on teste les inputs renseignés par l'utilisateur,
        Validation.validationPseudo(textfield_pseudo.getText());
        Validation.validationIp(textfield_adresse.getText());
        Validation.validationPort(textfield_port.getText());
        //s'il n'y a pas d'erreur
        if(Validation.afficheErreur().isEmpty()){   
           // on indique le pseudo du joueur serveur 
           pseudo = textfield_pseudo.getText();
           //on indique le type de joueur 
           player_type = "server";
           // on indique ici que cest au joueur serveur de jouer en premier 
           my_turn = true;
           // ici on indique au jeu que cest au joueur serveur a jouer le premier 
           CONTROLLER_Jeu.setTurn(my_turn);
           // ici on transmet au jeu le pseudo et le type de joueur 
           CONTROLLER_Jeu.setInformationPlayer("server", pseudo);
        
           try 
            {
                int port_serveur = Integer.parseInt(textfield_port.getText());
                server_socket = new ServerSocket(port_serveur);
                label_info.setText("Port "+server_socket.getLocalPort()+" écouté...");

                thread_attente_connexion = new Thread(new RUN_Connexion(server_socket));
                thread_attente_connexion.start(); 
                CONTROLLER_Jeu.setInformationPlayer("server", pseudo);
            }
            catch (IOException e) { 
                System.err.println("BUG : Port "+server_socket.getLocalPort()+" déjà utilisé."); 
            }
            // on redirige ée joueur serveur vers le gamescreen 
            this.mycontroller.setScreen(Puissance4.GAME_SCREEN);
        
        } else{
            //on affiche les erreurs de saisies
            label_erreurs.setText(Validation.afficheErreur());
      } 
    }
}
