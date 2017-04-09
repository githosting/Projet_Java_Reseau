// Interface pour la gestion des vues

package puissance4;

import puissance4.CONTROLLER_Super;


public interface INTERFACE_Screen {
 // this method will allow injection of the parent screenPane
    public void setScreenParent (CONTROLLER_Super screenPage);
}
