
package snakenet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import javafx.scene.shape.*;


public class SnakeNet extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        //Parent root = loader.load();
        // Récupération du controller pour pouvoir ensuite modifier des éléments de la vue.
        
        
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        FXMLDocumentController controller = loader.getController();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        // EXEMPLE POUR MODIFIER LA VUE
        controller.getLabel().setText("KIKOO !");
        
        controller.getLabel().setStyle("-fx-background-color: red;");
        
        //controller.getCase().setStyle("-fx-background-color: cornsilk;");
        //controller.getCase().setStyle("-fx-background-color: red;");
        //controller.getCase().setStyle("-fx-background-color: #ff3333");
        
        //javafx.scene.paint.Color color = javafx.scene.paint.Color.rgb(220, 20, 60);
        //controller.getRectangle().setFill(color); 
        
        //rectangle.setFill(color); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
