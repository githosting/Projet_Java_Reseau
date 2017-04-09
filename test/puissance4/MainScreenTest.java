/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

import java.net.URL;
import java.util.ResourceBundle;
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
public class MainScreenTest {
    
    public MainScreenTest() {
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
     * Test of initialize method, of class MainScreen.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        MainScreen instance = new MainScreen();
        instance.initialize(location, resources);

    }

    /**
     * Test of setScreenParent method, of class MainScreen.
     */
    @Test
    public void testSetScreenParent() {
        System.out.println("setScreenParent");
        ScreensController screenParent = null;
        MainScreen instance = new MainScreen();
        instance.setScreenParent(screenParent);
    }
    
       /**
     * Test Fichier Scores Existe
     * Method : setInformationPlayer
     * Class : MainScreen
     */
    @Test
    public void test_fichier_scores_existe() 
    {
        
        System.out.println("test_fichier_scores_existe");
        
        // A FAIRE PLUS TARD
        
    }
    
      /**
     * Test Fichier Sauvegardes Existe
     * Method : 
     * Class : MainScreen
     */
    @Test
    public void test_fichier_sauvegardes_existe() 
    {
        
        System.out.println("test_fichier_sauvegardes_existe");
        
        // A FAIRE PLUS TARD
        
    }
}
