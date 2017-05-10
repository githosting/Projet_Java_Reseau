
package puissance4.bean;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import puissance4.controller.CONTROLLER_Super;


/**
    * Class extending Application and being the application entry point.
    * @author Bettinger, Araba, Kessler
    * @version 2.0
    */
public class Puissance4 extends Application 
{
    // attributs
    public static String stage;
    // Main screen.
    public static final String MAIN_SCREEN = "main"; 
    public static final String MAIN_SCREEN_FXML = "/puissance4/vue/VUE_Accueil.fxml";
    
    // Server player connection screen.
    public static final String SERVER_CONNECT_SCREEN = "serverconnect"; 
    public static final String SERVER_CONNECT_SCREEN_FXML = "/puissance4/vue/VUE_Connexion_Server.fxml";

    // Client player connection screen.
    public static final String CLIENT_CONNECT_SCREEN = "clientconnect"; 
    public static final String CLIENT_CONNECT_SCREEN_FXML = "/puissance4/vue/VUE_Connexion_Client.fxml";
    public static final String title_client_screen = "client connexion";
    
    // Game screen. 
    public static final String GAME_SCREEN = "jeu"; 
    public static final String GAME_SCREEN_FXML = "/puissance4/vue/VUE_Jeu.fxml";
    
    // Historic screen. 
    public static final String HISTORIQUE_SCREEN = "historique"; 
    public static final String HISTORIQUE_SCREEN_FXML = "/puissance4/vue/VUE_Historique.fxml";
    
    // Sauvegarde screen screen. 
    public static final String CHARGEMENT_SCREEN = "Chargement"; 
    public static final String CHARGEMENT_SCREEN_FXML = "/puissance4/vue/VUE_Chargement.fxml"; 
    
    
    /**
        * Start the application.
        * @version 2.0
        * @param Stage stage
        *       The main application interface.
        * @return void
        * @throws Exception
        */
    @Override
    public void start(Stage stage) throws Exception 
    {
        CONTROLLER_Super mainContainer = new CONTROLLER_Super(); 
        
        // Loading all screens on memory.
        mainContainer.loadScreen(Puissance4.MAIN_SCREEN, 
                                 Puissance4.MAIN_SCREEN_FXML); 
      //  mainContainer.loadScreen(Puissance4.GAME_SCREEN, 
       //                          Puissance4.GAME_SCREEN_FXML); 
        mainContainer.loadScreen(Puissance4.SERVER_CONNECT_SCREEN, 
                                 Puissance4.SERVER_CONNECT_SCREEN_FXML); 
        mainContainer.loadScreen(Puissance4.CLIENT_CONNECT_SCREEN, 
                                 Puissance4.CLIENT_CONNECT_SCREEN_FXML);
    
        mainContainer.loadScreen(Puissance4.HISTORIQUE_SCREEN, 
                                 Puissance4.HISTORIQUE_SCREEN_FXML);
        
        mainContainer.loadScreen(Puissance4.CHARGEMENT_SCREEN, 
                                 Puissance4.CHARGEMENT_SCREEN_FXML);
        
        mainContainer.setScreen(Puissance4.MAIN_SCREEN); 

        // Preparing main interface.
        Group root2 = new Group(); 
        root2.getChildren().addAll(mainContainer); 
        Scene scene = new Scene(root2, 600, 580); 
        stage.getIcons().add(new Image("https://lh3.ggpht.com/V4FL3iqgEauIClIKQdJd_B2ar89SDMBsVD3Q_qKJc-pKqJfE-ftLLoyZG4YGXLrD21uU=w70"));
        stage.setTitle("Puissance 4 Net");
        stage.setScene(scene); 
        stage.show();
        
        // When the user leaves the application, stop everything.
        stage.setOnCloseRequest((WindowEvent we) -> {
            System.out.println("Closing...");
            Platform.exit();
            System.exit(0);
        });
    }


    /**
    * Launch the application.
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
