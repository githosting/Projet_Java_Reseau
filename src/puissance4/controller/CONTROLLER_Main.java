
package puissance4.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

// import fxml
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

// import programme
import puissance4.bean.INTERFACE_Screen;
import puissance4.bean.Puissance4;

// import alert
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
    * Class acting as a controller for view VUE_Accueil events.
    * @version 1.0
*/
public class CONTROLLER_Main implements Initializable,INTERFACE_Screen {
    
    // Attributes 
    CONTROLLER_Super mycontroller;
    
    // Accessors
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
    public void initialize(URL location, ResourceBundle resources) 
    {     
    }
    
    /**
        * Redirect to the server player connection interface.
        * @version 1.0
        * @param ActionEvent event
        * @return void
    */
    @FXML
    private void goToConnectScreenServer(ActionEvent event){
        mycontroller.setScreen(Puissance4.SERVER_CONNECT_SCREEN);
    }
    
    /**
        * Redirect to the client player connection interface.
        * @version 1.0
        * @param ActionEvent event
        * @return void
    */
    @FXML
    private void goToConnectScreenClient (ActionEvent event){
        mycontroller.setScreen(Puissance4.CLIENT_CONNECT_SCREEN);
    } 
    
    /**
        * Redirect to the history interface.
        * @version 1.0
        * @param ActionEvent event
        * @return void
    */
    @FXML
    private void goToHistorique (ActionEvent event){
        mycontroller.setScreen(Puissance4.HISTORIQUE_SCREEN);        
    }
    
    
     @FXML
    private void goToChargement (ActionEvent event){
        mycontroller.setScreen(Puissance4.CHARGEMENT_SCREEN);        
    }
    /**
     * Save game
     * @param event 
     * @return void
     */
     @FXML
     private void goToSauvegarde (ActionEvent event){
        mycontroller.setScreen(Puissance4.CHARGEMENT_SCREEN);        
    }
     
    /**
     * Redirect to the history interface. 
     * @version 1.0 
     * @param ActionEvent event 
     * @return void 
     */ 
    @FXML private void goToRegle (ActionEvent event){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message d'information");
        alert.setContentText("Le but du jeu est d'aligner 4 pions sur une grille comptant 6 rangées et 7 colonnes. \n\n Chaque joueur dispose de 21 pions d'une couleur (par convention, en général jaune ou rouge). \nTour à tour les deux joueurs placent un pion dans la colonne de leur choix, le pion coulisse alors jusqu'à la position la plus basse possible dans la dite colonne à la suite de quoi c'est à l'adversaire de jouer. \n\n Le vainqueur est le joueur qui réalise le premier un alignement (horizontal, vertical ou diagonal) d'au moins quatre pions de sa couleur.\n\n Si, alors que toutes les cases de la grille de jeu sont remplies, aucun des deux joueurs n'a réalisé un tel alignement, la partie est déclarée nulle."); 
        alert.showAndWait(); 
    }
}
