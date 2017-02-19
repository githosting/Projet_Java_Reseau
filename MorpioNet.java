
package morpionet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MorpioNet extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        controller.getLabelInfo().setText("KIKOO !");
         
        for (Node child : controller.getGridPane().getChildren()) 
        {
            child.setStyle("-fx-background-color: black;");
            /*
            if(child.getStyle() == "-fx-background-color: red;")
                child.setStyle("-fx-background-color: black;");
            */
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
