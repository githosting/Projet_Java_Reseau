
package puissance4;

import java.io.Console;
import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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

        //Application.setUserAgentStylesheet(null);
        //scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());

        //controller.fillGridWithLabels();
        
        /*
        for (Node child : controller.getGridPane().getChildren()) 
        {
            child.setStyle("-fx-background-color: white;"); 
            if(child.getStyle() == "-fx-background-color: black;")
                child.setStyle("-fx-background-color: red;");
        }
        */
        
        // Lorsque le user quitte l'application on stop tout.
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() 
        {
            public void handle(WindowEvent we) 
            {
                System.out.println("Closing...");
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
