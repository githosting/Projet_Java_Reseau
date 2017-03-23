/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import puissance4.ScreensController;

/**
 *
 * @author Kevin Araba
 */
public class GameScreen implements Initializable,ControlledScreen {

    ScreensController mycontroller;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        mycontroller = screenParent;
    }
}
