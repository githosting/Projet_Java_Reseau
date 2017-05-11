
package puissance4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import puissance4.controller.CONTROLLER_Connexion_Client;
import puissance4.controller.CONTROLLER_Super;

/**
    * Test CONTROLLER_Connexion_Client
    * @version 1.0
 */
public class CONTROLLER_Connexion_ClientTest {
    
    public CONTROLLER_Connexion_ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    





    /**
        * Test of setScreenParent method, of class CONTROLLER_Connexion_Client.
        * @param No parameters
        * @version 1.0
        * 
     */
    @Test
    public void testSetScreenParent() {
        System.out.println("setScreenParent");
        CONTROLLER_Super screenParent = null;
        CONTROLLER_Connexion_Client instance = new CONTROLLER_Connexion_Client();
        instance.setScreenParent(screenParent);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
