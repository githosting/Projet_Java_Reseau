
package puissance4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Puissance4 extends Application 
{
    public static final String MAIN_SCREEN = "main"; 
    public static final String MAIN_SCREEN_FXML = "main.fxml"; 
    public static final String SERVER_CONNECT_SCREEN = "serverconnect"; 
    public static final String SERVER_CONNECT_SCREEN_FXML = "ServerConnect.fxml";
    public static final String CLIENT_CONNECT_SCREEN = "clientconnect"; 
    public static final String CLIENT_CONNECT_SCREEN_FXML = "ClientConnect.fxml";
    public static final String GAME_SCREEN = "jeu"; 
    public static final String GAME_SCREEN_FXML = "jeu.fxml"; 
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();*/
        
        ScreensController mainContainer = new ScreensController(); 
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
        Scene scene = new Scene(root2); 
        stage.setTitle("Puissance 4 Net");
        stage.setScene(scene); 
        stage.show(); 
        /*
        Scene scene = new Scene(root);
        stage.setTitle("Puissance 4 Net");
        stage.setScene(scene);
        stage.show();
        
        controller.getLabelInfo().setText("Connectez-vous en tant que serveur ou client !");
        */
        // Lorsque le user quitte l'application on stop tout.
        stage.setOnCloseRequest((WindowEvent we) -> {
            System.out.println("Closing...");
            Platform.exit();
            System.exit(0);
        });
    }

    
    // @param args the command line arguments
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
