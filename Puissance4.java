
package puissance4;

import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Puissance4 extends Application {
    
    public static ServerSocket server_socket = null;
    public static Thread thread_attente_connexion;
    
    
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
            child.setStyle("-fx-background-color: white;");
            
            /*
            if(child.getStyle() == "-fx-background-color: black;")
                child.setStyle("-fx-background-color: red;");
            */
        }
        
        
        try 
        {
            server_socket = new ServerSocket(2013);
            System.out.println("Le serveur est à l'écoute du port "+server_socket.getLocalPort());

            thread_attente_connexion = new Thread(new RUN_Connexion(server_socket));
            thread_attente_connexion.start();
        } 
        catch (IOException e) { System.err.println("Le port "+server_socket.getLocalPort()+" est déjà utilisé !"); }
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
