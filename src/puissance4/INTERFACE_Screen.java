
package puissance4;

import puissance4.CONTROLLER_Super;


/**
    * Interface pour la gestion des vues.
    * @author Araba, Kessler
    * @version 1.0
    */
public interface INTERFACE_Screen 
{
    /**
        * Allow injection of the parent screenPage.
        * @author Araba, Kessler
        * @version 1.0
        * @param CONTROLLER_Super screenPage
        *       The application super-controller.
        * @return void
        */
    public void setScreenParent (CONTROLLER_Super screenPage);
}
