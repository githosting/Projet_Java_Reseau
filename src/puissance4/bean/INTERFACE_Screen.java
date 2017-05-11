
package puissance4.bean;

import puissance4.controller.CONTROLLER_Super;


/**
    * Interface for views management.
    * @version 1.0
*/
public interface INTERFACE_Screen 
{
    /**
        * Allow injection of the parent screenPage.
        * @version 1.0
        * @param screenPage CONTROLLER_Super 
        *       The application super-controller.
    */
    public void setScreenParent (CONTROLLER_Super screenPage);
}
