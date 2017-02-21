
package puissance4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        
        
        controller.getLabelInfo().setText("DEBUG LABEL");

        //controller.fillGridWithLabels();
        
        for (Node child : controller.getGridPane().getChildren()) 
        {
            child.setStyle("-fx-background-color: black;");
            
            /*
            if(child.getStyle() == "-fx-background-color: black;")
                child.setStyle("-fx-background-color: red;");
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
