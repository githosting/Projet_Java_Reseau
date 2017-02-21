
package puissance4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label_info;

    @FXML
    private GridPane gp;
    
    // FXML Getters
    public Label getLabelInfo() { return label_info; }
    public GridPane getGridPane() { return gp; }
    
    @FXML
    public void fillGridWithLabels() 
    {
        for (int i = 0 ; i < 30 ; i++) 
        {
            for (int j = 0; j < 30; j++) 
            {
                Label label = new Label("-");
                gp.add(label, i, j);
            }
        }
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        /*
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());

        out.println("Entrez votre login :");
        out.flush();
        login = in.readLine();

        out.println("connecte");
        System.out.println(login +" vient de se connecter ");
        out.flush();
        */
    }
    
    @FXML 
    public void label_infoClick(MouseEvent event)
    {
        System.out.println("CLICK");
    }
    
    @FXML 
    public void gpClick(MouseEvent event)
    {
        System.out.println("CLICK");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // LE CODE ICI S'EXECUTE AVANT LE SHOW DE L'INTERFACE GRAPHIQUE.
 
    }    
    
}
