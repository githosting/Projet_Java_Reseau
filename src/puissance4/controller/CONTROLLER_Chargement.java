
package puissance4.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import puissance4.bean.Partie_Save;
import puissance4.bean.INTERFACE_Screen;
import puissance4.bean.Partie;
import puissance4.bean.Puissance4;
import puissance4.bean.RUN_Connexion;
import static puissance4.controller.CONTROLLER_Connexion_Server.player_type;
import static puissance4.controller.CONTROLLER_Connexion_Server.thread_attente_connexion;
import static puissance4.controller.CONTROLLER_Connexion_Server.thread_attente_connexion;

/**
 * FXML Controller class
 *
 */
public class CONTROLLER_Chargement implements Initializable,INTERFACE_Screen  {
    CONTROLLER_Super mycontroller;
    
    // true if the party is loading
    public static Boolean charger = false;
    
    // initilize the grid of game
     public static String grille_de_jeu [][] =   
        {
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"}
        };
     
    @FXML
    private Label label_info;
    
    @FXML
    // label indicate the pseudo 
    private Label label_pseudo_moi;
    
    @FXML
    //label indicate pseudo adversaire
    private Label label_pseudo_adversaire;
    
    @FXML
    // label indicate type of result game
    private Label label_victoire_defaite;
    
    @FXML
    private ListView<?> listview;
    
    @FXML
    // the grid of game
    private GridPane gp;

    /**
     * Initializes the controller class.
     * @params screenParent
     * @return void
     */
    @Override
    public void setScreenParent(CONTROLLER_Super screenParent) {
        mycontroller = screenParent;
    }
    
    /**
     * inintialize the chargement page
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList data = FXCollections.observableArrayList();
        
            File dossier = new File("./games"); 
            if(dossier.exists()){
            File[] les_files = dossier.listFiles();  
            for (File f : les_files)   
                data.add(f.getName());
            }else{
                dossier.mkdir();
            }
            listview.setItems(data);
    }    

    /**
     * 
     * @param event
     * @throws URISyntaxException 
     */
    @FXML
    private void handleMouseClick(MouseEvent event) throws URISyntaxException {
        
         String file_selected = listview.getSelectionModel().getSelectedItem().toString();     
        // Open the associated file.
        Path path = Paths.get("./games/" + file_selected);     
        // If no errors detected.
        try 
        {
            //  Get the JSON string that the file contains.
                List<String> les_lignes = Files.readAllLines(path, StandardCharsets.UTF_8);
                String JSON_string = les_lignes.get(0);
            
            // Deserialisation of the JSON string into a proper Partie_History object.
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Partie_Save partie = new Partie_Save();
                partie = objectMapper.readValue(JSON_string, Partie_Save.class);
            
            // Display Partie_History object information on the view.
            

                ObservableList<Node> childrens = gp.getChildren();
                for (int i = 0 ; i < 6 ; i++) 
                {
                    for (int j = 0; j < 7; j++) 
                    {
                        CONTROLLER_Chargement.grille_de_jeu[i][j] = partie.grille_de_jeu[i][j];
                        for (Node node : childrens) {
                            if(gp.getRowIndex(node) == i && gp.getColumnIndex(node) == j) {
                                String img = partie.grille_de_jeu[i][j];
                                node.setStyle("-fx-background-image: url(" + getClass().getResource("/resources/"+img + ".png").toExternalForm() + ");");
                                break;
                            }
                        }
                       
                    }
                }
                Partie.gameChargement.setGrille_de_jeu(partie.grille_de_jeu);
                label_pseudo_moi.setText("Moi : " + partie.pseudo_moi);
                label_pseudo_adversaire.setText("Adversaire : " + partie.pseudo_adversaire);
                label_victoire_defaite.setText("Résultat : " + partie.victoire_defaite);
                
                player_type = "server";
               
                Partie.gameChargement.setMy_turn(partie.myTurn);
                      
                ServerSocket socket = new ServerSocket(Integer.parseInt( partie.textfield_port));
         
                label_info.setText("Port "+socket.getLocalPort()+" écouté...");
                 Partie.gameChargement.setClient_connecte(false);
                // Launch broadcast and reception Threads.
                thread_attente_connexion = new Thread(new RUN_Connexion(socket));
                thread_attente_connexion.start(); 
                
                
                Partie.gameChargement.setInformationPlayer(player_type, partie.pseudo_moi);
                Partie.gameChargement.setCharger(true);
            
        
        } catch (IOException ex) {Logger.getLogger(CONTROLLER_Historique.class.getName()).log(Level.SEVERE, null, ex);}
    }

    /**
     * action when you click on cell
     * @param event 
     */
    @FXML
    private void cellClick(MouseEvent event) {
    }

    /**
     * method when you click on return button
     * @param event
     * @return void
     */
    @FXML
    private void goToHome(ActionEvent event) {
         mycontroller.setScreen(Puissance4.MAIN_SCREEN);
    }
    
    /**
     * Method to launch game 
     * @param event 
     * @return void
     */
    @FXML
    private void goToGame(ActionEvent event) {
        if(Partie.gameChargement.isCharger()){
         this.mycontroller.loadScreen(Puissance4.GAME_SCREEN, Puissance4.GAME_SCREEN_FXML); 
         mycontroller.setScreen(Puissance4.GAME_SCREEN);
        }
    }
}
