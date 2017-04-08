package puissance4;

//importations
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import puissance4.ScreensController;

// super controlleur 
// controlleur de la vue main.fxml
public class MainScreen implements Initializable,ControlledScreen {
    
    // attributs 
    ScreensController mycontroller;
    
    // methodes
    
    // methode d'initialisation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

   /*
       changer de screen
        @return void
        @params ScreensController screenParent
    */
    @Override
    public void setScreenParent(ScreensController screenParent) {
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
}
