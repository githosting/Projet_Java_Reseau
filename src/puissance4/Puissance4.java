
package puissance4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
    * Classe étendant Application et constituant le point d'entrée de l'application.
    * @author Bettinger, Araba, Kessler
    * @version 2.0
    */
public class Puissance4 extends Application 
{
    // Écran Principal.
    public static final String MAIN_SCREEN = "main"; 
    public static final String MAIN_SCREEN_FXML = "VUE_Accueil.fxml";
    
    // Écran pour la connection en tant que serveur.
    public static final String SERVER_CONNECT_SCREEN = "serverconnect"; 
    public static final String SERVER_CONNECT_SCREEN_FXML = "VUE_Connexion_Server.fxml";

    // Écran pour la connexion à un serveur en tant que client.
    public static final String CLIENT_CONNECT_SCREEN = "clientconnect"; 
    public static final String CLIENT_CONNECT_SCREEN_FXML = "VUE_Connexion_Client.fxml";
    
    // Écran de jeu. 
    public static final String GAME_SCREEN = "jeu"; 
    public static final String GAME_SCREEN_FXML = "VUE_Jeu.fxml";
    
    // Écran pour la consultation de l'historique des parties. 
    public static final String HISTORIQUE_SCREEN = "historique"; 
    public static final String HISTORIQUE_SCREEN_FXML = "VUE_Historique.fxml"; 
    
    
    /**
        * Démarre l'application.
        * @author Bettinger, Araba, Kessler
        * @version 2.0
        * @param Stage stage
        *       L'interface principale de l'application.
        * @return void
        * @throws Exception
        */
    @Override
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
        mainContainer.loadScreen(Puissance4.HISTORIQUE_SCREEN, 
                                 Puissance4.HISTORIQUE_SCREEN_FXML);
        mainContainer.setScreen(Puissance4.MAIN_SCREEN); 

        Group root2 = new Group(); 
        root2.getChildren().addAll(mainContainer); 
        Scene scene = new Scene(root2, 560, 530);
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


    /**
    * Lance le démarrage de l'application.
    * @author Automatic Generation
    * @version 1.0
    * @param args 
    * 		The command line arguments.
    * @return void
    */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
