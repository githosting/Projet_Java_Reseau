// Point d'entrée de l'application

package puissance4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Puissance4 extends Application 
{
    // Écran principal
    public static final String MAIN_SCREEN = "main"; 
    public static final String MAIN_SCREEN_FXML = "VUE_Accueil.fxml";
    
    // ecran de connection pour le serveur
    public static final String SERVER_CONNECT_SCREEN = "serverconnect"; 
    public static final String SERVER_CONNECT_SCREEN_FXML = "VUE_Connexion_Server.fxml";

    // ecran de connexion pour le client
    public static final String CLIENT_CONNECT_SCREEN = "clientconnect"; 
    public static final String CLIENT_CONNECT_SCREEN_FXML = "VUE_Connexion_Client.fxml";
    
    // ecran pour le deroulement du jeu 
    public static final String GAME_SCREEN = "jeu"; 
    public static final String GAME_SCREEN_FXML = "VUE_Jeu.fxml"; 
    
    @Override
    /*
    * @return void
    * @params Stage stage
    * throws exception 
    * lors du lancement on lance start pour charger les interfaces et démarrer les sessions
    */
    public void start(Stage stage) throws Exception 
    {
    
        CONTROLLER_Super mainContainer = new CONTROLLER_Super(); 
        // nous chargeons les différents ecrans en mémoire 
        mainContainer.loadScreen(Puissance4.MAIN_SCREEN, 
                                 Puissance4.MAIN_SCREEN_FXML); 
        mainContainer.loadScreen(Puissance4.GAME_SCREEN, 
                                 Puissance4.GAME_SCREEN_FXML); 
        mainContainer.loadScreen(Puissance4.SERVER_CONNECT_SCREEN, 
                                 Puissance4.SERVER_CONNECT_SCREEN_FXML); 
        mainContainer.loadScreen(Puissance4.CLIENT_CONNECT_SCREEN, 
                                 Puissance4.CLIENT_CONNECT_SCREEN_FXML); 
        mainContainer.setScreen(Puissance4.MAIN_SCREEN); 

        Group root2 = new Group(); 
        root2.getChildren().addAll(mainContainer); 
        Scene scene = new Scene(root2, 600, 570);
        //Scene scene = new Scene(root2);
        
        // ajout du titre sur la fenetre
       
        stage.setTitle("Puissance 4 Net");
        stage.setScene(scene); 
        stage.show(); 
        // Lorsque le user quitte l'application on stop tout.
        stage.setOnCloseRequest((WindowEvent we) -> {
            System.out.println("Closing...");
            Platform.exit();
            System.exit(0);
        });
    }

    /*
    *@params args the command line arguments
    *@return void
    * lancement du jeu 
    */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
