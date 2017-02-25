
package puissance4;

import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Puissance4 extends Application {
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        //Application.setUserAgentStylesheet(null);
        //scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());

        controller.getLabelInfo().setText("Bienvenue.");
        //controller.fillGridWithLabels();
        
        for (Node child : controller.getGridPane().getChildren()) 
        {

            //child.setStyle("-fx-background-color: white;");
            
            
            /*
            if(child.getStyle() == "-fx-background-color: black;")
                child.setStyle("-fx-background-color: red;");
            */
        }
        
        // Le Thread qui va actualiser la gridPane.
            new Thread( new Runnable() 
            {
                @Override
                public void run() 
                {
                    while(true)
                    {
                        Platform.runLater(() -> {
                            controller.getLabelInfo().setText(controller.getLabelInfo().getText() + RUN_Reception.message);
                        });
                        
                        // On fait une pause
                        try { Thread.sleep(1000); }
                        catch (Exception e) {  }
                    }
                }
            }).start();
    
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
