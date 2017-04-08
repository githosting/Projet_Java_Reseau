/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Kevin Araba
 */
public class ConnectScreenServerTest {
    
    public ConnectScreenServerTest() {
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
     * Test of getLabelInfo method, of class ConnectScreenServer.
     */
    @Test
    public void testGetLabelInfo() {
        System.out.println("getLabelInfo");
        ConnectScreenServer instance = new ConnectScreenServer();
        Label expResult = null;
        Label result = instance.getLabelInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGridPane method, of class ConnectScreenServer.
     */
    @Test
    public void testGetGridPane() {
        System.out.println("getGridPane");
        ConnectScreenServer instance = new ConnectScreenServer();
        GridPane expResult = null;
        GridPane result = instance.getGridPane();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class ConnectScreenServer.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        ConnectScreenServer instance = new ConnectScreenServer();
        instance.initialize(location, resources);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScreenParent method, of class ConnectScreenServer.
     */
    @Test
    public void testSetScreenParent() {
        System.out.println("setScreenParent");
        ScreensController screenParent = null;
        ConnectScreenServer instance = new ConnectScreenServer();
        instance.setScreenParent(screenParent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
