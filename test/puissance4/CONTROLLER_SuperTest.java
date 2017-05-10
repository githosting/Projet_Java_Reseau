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
import puissance4.controller.CONTROLLER_Super;

/**
 *
 * @author Kevin Araba
 */
public class CONTROLLER_SuperTest {
    
    public CONTROLLER_SuperTest() {
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
     * Test of addScreen method, of class CONTROLLER_Super.
     */
    @Test
    public void testAddScreen() {
        System.out.println("addScreen");
        String name = "";
        Node screen = null;
        CONTROLLER_Super instance = new CONTROLLER_Super();
        instance.addScreen(name, screen);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getScreen method, of class CONTROLLER_Super.
     */
    @Test
    public void testGetScreen() {
        System.out.println("getScreen");
        String name = "";
        CONTROLLER_Super instance = new CONTROLLER_Super();
        Node expResult = null;
        Node result = instance.getScreen(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of loadScreen method, of class CONTROLLER_Super.
     */
    @Test
    public void testLoadScreen() {
        System.out.println("loadScreen");
        String name = "";
        String resource = "";
        CONTROLLER_Super instance = new CONTROLLER_Super();
        boolean expResult = false;
        boolean result = instance.loadScreen(name, resource);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setScreen method, of class CONTROLLER_Super.
     */
    @Test
    public void testSetScreen() {
        System.out.println("setScreen");
        String name = "";
        CONTROLLER_Super instance = new CONTROLLER_Super();
        boolean expResult = false;
        boolean result = instance.setScreen(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of unloadScreen method, of class CONTROLLER_Super.
     */
    @Test
    public void testUnloadScreen() {
        System.out.println("unloadScreen");
        String name = "";
        CONTROLLER_Super instance = new CONTROLLER_Super();
        boolean expResult = false;
        boolean result = instance.unloadScreen(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
