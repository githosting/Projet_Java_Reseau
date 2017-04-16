// Contrôleur de la vue VUE_Accueil

package puissance4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import puissance4.CONTROLLER_Super;


public class CONTROLLER_Main implements Initializable,INTERFACE_Screen {
    
    // attributs 
    CONTROLLER_Super mycontroller;
    
    // methodes
    
    // methode d'initialisation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

   /*
       changer de screen
        @return void
        @params CONTROLLER_Super screenParent
    */
    @Override
    public void setScreenParent(CONTROLLER_Super screenParent) {
        mycontroller = screenParent;
    }
    
    /*
        change d'ecran on est redirige vers l'ecran de connexion du serveur 
        @return void
        @params ActionEvent event lorsque l'on appuie sur un bouton
    */
    @FXML
    private void goToConnectScreenServer(ActionEvent event){
        mycontroller.setScreen(Puissance4.SERVER_CONNECT_SCREEN);
    }
    
    /*
        change d'ecran on est redirige vers l'ecran de connexion du client
        @return void
        @params ActionEvent event lorsque l'on appuie sur un bouton
    */
    @FXML
    private void goToConnectScreenClient (ActionEvent event){
        mycontroller.setScreen(Puissance4.CLIENT_CONNECT_SCREEN);
    } 
    
    /*
        Redirige vers l'écran historique des parties.
        @return void
        @params ActionEvent event lorsque l'on appuie sur un bouton
    */
    @FXML
    private void goToHistorique (ActionEvent event){
        mycontroller.setScreen(Puissance4.HISTORIQUE_SCREEN);
        System.out.println("KIKOU");
        
    } 
}
