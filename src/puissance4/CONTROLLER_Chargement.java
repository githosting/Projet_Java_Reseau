/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

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
import static puissance4.CONTROLLER_Connexion_Server.player_type;
import static puissance4.CONTROLLER_Connexion_Server.server_socket;
import static puissance4.CONTROLLER_Connexion_Server.thread_attente_connexion;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class CONTROLLER_Chargement implements Initializable,INTERFACE_Screen  {
    CONTROLLER_Super mycontroller;
    public static Boolean charger = false;
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
    private Label label_pseudo_moi;
    @FXML
    private Label label_pseudo_adversaire;
    @FXML
    private Label label_victoire_defaite;
    @FXML
    private ListView<?> listview;
    @FXML
    private GridPane gp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void setScreenParent(CONTROLLER_Super screenParent) {
        mycontroller = screenParent;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
               
                Partie.gameChargement.setMy_turn(true);
                      
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

    @FXML
    private void cellClick(MouseEvent event) {
    }

    @FXML
    private void goToHome(ActionEvent event) {
         mycontroller.setScreen(Puissance4.MAIN_SCREEN);
    }
    @FXML
    private void goToGame(ActionEvent event) {
        if(Partie.gameChargement.isCharger()){
         this.mycontroller.loadScreen(Puissance4.GAME_SCREEN, Puissance4.GAME_SCREEN_FXML); 
         mycontroller.setScreen(Puissance4.GAME_SCREEN);
        }
    }
    
}
