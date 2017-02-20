
package snakenet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
    private void handleButtonAction(ActionEvent event) {
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // LE CODE ICI S'EXECUTE AVANT LE SHOW DE L'INTERFACE GRAPHIQUE.
 
    }    
    
}
