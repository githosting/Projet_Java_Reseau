/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

import puissance4.ScreensController;

/**
 *
 * @author Kevin Araba
 */
public interface ControlledScreen {
 // this method will allow injection of the parent screenPane
    public void setScreenParent (ScreensController screenPage);
}
