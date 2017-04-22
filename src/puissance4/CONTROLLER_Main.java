
package puissance4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import puissance4.CONTROLLER_Super;


/**
    * Classe agissant comme controller des événements de la vue VUE_Accueil.
    * @author Kessler, Araba
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
        * Execute du code à l'initialisation de la vue.
        * @author Kessler, Araba
        * @version 1.0
        * @param URL location
        * @param ResourceBundle resources
        * @return void
        */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    
    /**
        * Redirige vers l'interface de connexion pour le joueur serveur.
        * @author Kessler, Araba
        * @version 1.0
        * @param ActionEvent event
        * @return void
        */
    @FXML
    private void goToConnectScreenServer(ActionEvent event){
        mycontroller.setScreen(Puissance4.SERVER_CONNECT_SCREEN);
    }
    
    
    /**
        * Redirige vers l'interface de connexion pour le joueur client.
        * @author Kessler, Araba
        * @version 1.0
        * @param ActionEvent event
        * @return void
        */
    @FXML
    private void goToConnectScreenClient (ActionEvent event){
        mycontroller.setScreen(Puissance4.CLIENT_CONNECT_SCREEN);
    } 
    
    
    /**
        * Redirige vers l'interface de consultation de l'historique des parties.
        * @author Bettinger
        * @version 1.0
        * @param ActionEvent event
        * @return void
        */
    @FXML
    private void goToHistorique (ActionEvent event){
        mycontroller.setScreen(Puissance4.HISTORIQUE_SCREEN);        
    } 
}
