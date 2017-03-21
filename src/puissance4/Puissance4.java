
package puissance4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Puissance4 extends Application 
{
    @Override
    public void start(Stage stage) throws Exception 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();
        
        Scene scene = new Scene(root);
        stage.setTitle("Puissance 4 Net");
        stage.setScene(scene);
        stage.show();
        
        controller.getLabelInfo().setText("Connectez-vous en tant que serveur ou client !");
        
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
