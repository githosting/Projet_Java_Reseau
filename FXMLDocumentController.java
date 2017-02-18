/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpionet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Bettinger
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label1;
    private Rectangle r4;
    private AnchorPane case4;
    
    // FXML Getters
    public Label getLabel() { return label1; }
    public Rectangle getRectangle() { return r4; }
    public AnchorPane getCase() { return case4; }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label1.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // LE CODE ICI S'EXECUTE AVANT LE SHOW DE L'INTERFACE GRAPHIQUE.
        r4.setFill(Color.RED);
        
    }   
    
}
