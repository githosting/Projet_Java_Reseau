
package puissance4;

import java.io.Console;
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
import javafx.scene.layout.GridPane;
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
        
        /* CODE DEPORTE VERS LE CONTROLLER - TOUT EN BAS DU CONTROLLER
        // Le Thread qui va actualiser la gridPane.
            new Thread( new Runnable() 
            {
                @Override
                public void run() 
                {
                    while(true)
                    {  
                        Platform.runLater(() -> {
                            
                            int colIndex = 0;
                            int rowIndex = 0;
                            String player_color;

                            if(RUN_Reception.message != null && RUN_Reception.message.length() > 0)
                            {
                                colIndex = Integer.parseInt((RUN_Reception.message.split(";"))[0]);
                                rowIndex = Integer.parseInt((RUN_Reception.message.split(";"))[1]);
                                player_color = (RUN_Reception.message.split(";"))[2];

                                // On change la couleur du label concerné.
                                for (Node node : controller.getGridPane().getChildren()) 
                                {
                                    System.out.println("colIndex : " + colIndex);
                                    System.out.println("node_col_index : " + controller.getGridPane().getColumnIndex(node));

                                    if (node instanceof Label 
                                        && GridPane.getColumnIndex(node) == colIndex
                                        && GridPane.getRowIndex(node) == rowIndex) 
                                    {
                                            ((Label)node).setStyle("-fx-background-color: " + player_color + ";");
                                    }
                                }
                            }       
                        });         

                        // On fait une pause
                        try { Thread.sleep(1000); }
                        catch (Exception e) {  }
                    
                    }
                    
                }
            }).start();
        */
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
