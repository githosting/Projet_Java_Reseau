// Contrôleur de la vue VUE_Connexion_Client

package puissance4;

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

// Importations qui concernent le jeu et les informations nécessaires
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import puissance4.INTERFACE_Screen;
import puissance4.CONTROLLER_Super;


public class CONTROLLER_Connexion_Client implements Initializable,INTERFACE_Screen {
    
    // attributs 
    public static Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    // public indique le type du joueur 
    public static String player_type;
    public static ServerSocket server_socket;
    public static Thread thread_attente_connexion;
    public static Thread thread_emission;
    public static Thread thread_reception;
    public static RUN_Connexion runnable_connexion;
    public static RUN_Emission runnable_emission;
    public static RUN_Reception runnable_reception;
    
    // attributs FXML
    // on creer un attribut qui va nous renseigner sur le pseudo coter client
    @FXML
    private TextField textfield_pseudo;
    
    @FXML
    // on a un attribut private qui nous renseigne sur l'adresse du serveur 
    private TextField textfield_adresse; 
    
    @FXML
    // on a un attribut private pour recuperer le port du serveur 
    private TextField textfield_port;
    
    // on recupere avec cet attribut private le bouton connecte cote client
    @FXML
    private Button button_connect;
    
    
    // cet attribut public nopus renseigne des informations du jeu 
    // le tour du joueur 
    // quel joueur a gangne
    @FXML
    public Label label_info;
    /**
     * emplacement pour l'affichage des erreurs
     */
    @FXML
    protected  Label label_erreurs;
    // dans cet attribut private on recupere la grille de jeu du puissance 4
    @FXML
    private GridPane gp;

    // attribut qui indique la couleur de jeu du joueur client
    public boolean my_turn = false;
    // attribut qui indique le pseudo du joueur client
    public String pseudo;
    // lorsque le client est connecte a la partie on demarre le jeu avec game_start
    public boolean game_start = false;
    
    CONTROLLER_Super mycontroller;
    
        // FXML Getters
    // ono recupere le label info 
    public Label getLabelInfo() { return label_info; }
    // on nous retourne la grille de jeu
    public GridPane getGridPane() { return gp; }
    
    // methode d'initialisation
    //@params Url de serveur
    //@params  ResourceBundle resources
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textfield_adresse.setText("127.0.0.1");
        textfield_port.setText("4200");
    }

    // setScreenParent
    //@return void
    // dans cette methode on redirige notre ecran 
    @Override
    public void setScreenParent(CONTROLLER_Super screenParent) {
        mycontroller = screenParent;
    }
  
    // goToHome
    // @return void 
    // @params event lorsque l'on appuie sur un bouton ici
    // on retourne vers le screen de lancement 
    @FXML
    private void goToHome (ActionEvent event){
        mycontroller.setScreen(Puissance4.MAIN_SCREEN);
    }
    
    // btn_connect 
    //@return void 
    //@ params event si on clique sur le bouton connecte la procedure de lancemenmt se met en place 
    //throws si l'url n'est pas valide 
     @FXML
    private void btn_connect(ActionEvent event) throws MalformedURLException 
    {
        
        //on teste les inputs renseignés par l'utilisateur,
        Validation.validationPseudo(textfield_pseudo.getText());
        Validation.validationIp(textfield_adresse.getText());
        Validation.validationPort(textfield_port.getText());
        //s'il n'y a pas d'erreur
        if(Validation.afficheErreur().isEmpty()){
            // on recupere le pseudo
            pseudo = textfield_pseudo.getText();
            // on indique que le joueur est de type serveur
            player_type = "client";
            // on appelle le gamescreen pour lui transmettre le type de joueur et le pseudo que le joueur est 
            CONTROLLER_Jeu.setInformationPlayer(player_type, pseudo);
        
            try 
            {
                label_info.setText("Demande de connexion...");

                String adresse_serveur = textfield_adresse.getText();
                int port_serveur = Integer.parseInt(textfield_port.getText());

                socket = new Socket(adresse_serveur, port_serveur);

                // Si ce message s'affiche c'est que je suis connecté
                label_info.setText("Connecté au serveur.");
                //label_info.setText("TOUR ADVERSE");
                game_start = true;
                CONTROLLER_Jeu.setGame(game_start);
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	

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
            this.mycontroller.setScreen(Puissance4.GAME_SCREEN);
        }else{
            //on affiche les erreurs de saisies
            label_erreurs.setText(Validation.afficheErreur());
        }
      
}
    
}