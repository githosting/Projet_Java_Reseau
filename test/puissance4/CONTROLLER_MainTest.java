
package puissance4;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import puissance4.controller.CONTROLLER_Main;
import puissance4.controller.CONTROLLER_Super;

/**
     * Test CONTROLLER_Main
 */
public class CONTROLLER_MainTest {
    
    public CONTROLLER_MainTest() {
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
        * Test of initialize method, of class CONTROLLER_Main.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        CONTROLLER_Main instance = new CONTROLLER_Main();
        instance.initialize(location, resources);

    }

    /**
        * Test of setScreenParent method, of class CONTROLLER_Main.
     */
    @Test
    public void testSetScreenParent() {
        System.out.println("setScreenParent");
        CONTROLLER_Super screenParent = null;
        CONTROLLER_Main instance = new CONTROLLER_Main();
        instance.setScreenParent(screenParent);
    }
    
       /**
     * Test Fichier Scores Existe
     */
    @Test
    public void test_fichier_scores_existe() 
    {
        
        System.out.println("test_fichier_scores_existe");
        
        // A FAIRE PLUS TARD
        
    }
    
      /**
     * Test Fichier Sauvegardes Existe
     */
    @Test
    public void test_fichier_sauvegardes_existe() 
    {
        
        System.out.println("test_fichier_sauvegardes_existe");
        
        // A FAIRE PLUS TARD
        
    }
}
