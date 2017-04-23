
package puissance4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
    * Class acting as a controller for view VUE_Accueil events.
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
        * Execute code when the view is displayed.
        * @author Kessler, Araba
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
        * Redirect to the client player connection interface.
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
        * Redirect to the history interface.
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
