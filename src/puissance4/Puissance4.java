
package puissance4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
    * Class extending Application and being the application entry point.
    * @author Bettinger, Araba, Kessler
    * @version 2.0
    */
public class Puissance4 extends Application 
{
    // Main screen.
    public static final String MAIN_SCREEN = "main"; 
    public static final String MAIN_SCREEN_FXML = "VUE_Accueil.fxml";
    
    // Server player connection screen.
    public static final String SERVER_CONNECT_SCREEN = "serverconnect"; 
    public static final String SERVER_CONNECT_SCREEN_FXML = "VUE_Connexion_Server.fxml";

    // Client player connection screen.
    public static final String CLIENT_CONNECT_SCREEN = "clientconnect"; 
    public static final String CLIENT_CONNECT_SCREEN_FXML = "VUE_Connexion_Client.fxml";
    
    // Game screen. 
    public static final String GAME_SCREEN = "jeu"; 
    public static final String GAME_SCREEN_FXML = "VUE_Jeu.fxml";
    
    // Historic screen. 
    public static final String HISTORIQUE_SCREEN = "historique"; 
    public static final String HISTORIQUE_SCREEN_FXML = "VUE_Historique.fxml"; 
    
    
    /**
        * Start the application.
        * @author Bettinger, Araba, Kessler
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
        mainContainer.loadScreen(Puissance4.GAME_SCREEN, 
                                 Puissance4.GAME_SCREEN_FXML); 
        mainContainer.loadScreen(Puissance4.SERVER_CONNECT_SCREEN, 
                                 Puissance4.SERVER_CONNECT_SCREEN_FXML); 
        mainContainer.loadScreen(Puissance4.CLIENT_CONNECT_SCREEN, 
                                 Puissance4.CLIENT_CONNECT_SCREEN_FXML);
        mainContainer.loadScreen(Puissance4.HISTORIQUE_SCREEN, 
                                 Puissance4.HISTORIQUE_SCREEN_FXML);
        mainContainer.setScreen(Puissance4.MAIN_SCREEN); 

        // Preparing main interface.
        Group root2 = new Group(); 
        root2.getChildren().addAll(mainContainer); 
        Scene scene = new Scene(root2, 560, 530);
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
