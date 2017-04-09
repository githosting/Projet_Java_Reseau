/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

import javafx.scene.Node;
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
public class ScreensControllerTest {
    
    public ScreensControllerTest() {
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
     * Test of addScreen method, of class ScreensController.
     */
    @Test
    public void testAddScreen() {
        System.out.println("addScreen");
        String name = "";
        Node screen = null;
        ScreensController instance = new ScreensController();
        instance.addScreen(name, screen);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScreen method, of class ScreensController.
     */
    @Test
    public void testGetScreen() {
        System.out.println("getScreen");
        String name = "";
        ScreensController instance = new ScreensController();
        Node expResult = null;
        Node result = instance.getScreen(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadScreen method, of class ScreensController.
     */
    @Test
    public void testLoadScreen() {
        System.out.println("loadScreen");
        String name = "";
        String resource = "";
        ScreensController instance = new ScreensController();
        boolean expResult = false;
        boolean result = instance.loadScreen(name, resource);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScreen method, of class ScreensController.
     */
    @Test
    public void testSetScreen() {
        System.out.println("setScreen");
        String name = "";
        ScreensController instance = new ScreensController();
        boolean expResult = false;
        boolean result = instance.setScreen(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unloadScreen method, of class ScreensController.
     */
    @Test
    public void testUnloadScreen() {
        System.out.println("unloadScreen");
        String name = "";
        ScreensController instance = new ScreensController();
        boolean expResult = false;
        boolean result = instance.unloadScreen(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
