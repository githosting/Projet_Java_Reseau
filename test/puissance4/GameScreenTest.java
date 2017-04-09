/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
public class GameScreenTest {
    
    public GameScreenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() 
    {
        
        System.out.println("Avant chaque test.");
        
        // Réinitialisation de la grille de jeu.
        for(int i=0 ; i<GameScreen.grille_de_jeu.length ; i++)
        {
            for(int y=0 ; y<GameScreen.grille_de_jeu[i].length ; y++)
                GameScreen.grille_de_jeu[i][y] = "null";
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getLabelInfo method, of class GameScreen.
     */
    @Test
    public void testGetLabelInfo() {
        System.out.println("getLabelInfo");
        GameScreen instance = new GameScreen();
        Label expResult = null;
        Label result = instance.getLabelInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGridPane method, of class GameScreen.
     */
    @Test
    public void testGetGridPane() {
        System.out.println("getGridPane");
        GameScreen instance = new GameScreen();
        GridPane expResult = null;
        GridPane result = instance.getGridPane();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScreenParent method, of class GameScreen.
     */
    @Test
    public void testSetScreenParent() {
        System.out.println("setScreenParent");
        ScreensController screenParent = null;
        GameScreen instance = new GameScreen();
        instance.setScreenParent(screenParent);
    }

    /**
     * Test Pas Victoire & Pas Match Nul
     * Method : check_victory
     * Class : GameScreen
     */
    @Test
     public void test_pas_victoire() 
    {
        
        System.out.println("test_pas_victoire");

        String expected = "null";
        String result = GameScreen.check_victory();
        assertEquals(expected, result);
    }

     /**
     * Test Match Nul
     * Method : check_victory
     * Class : GameScreen
     */
    @Test
    public void test_match_nul() 
    {
        
        System.out.println("test_match_nul");
        
        GameScreen.grille_de_jeu[0][0]= "1";
        GameScreen.grille_de_jeu[0][1]= "1";
        GameScreen.grille_de_jeu[0][2]= "1";
        GameScreen.grille_de_jeu[0][3]= "1";
        GameScreen.grille_de_jeu[0][4]= "1";
        GameScreen.grille_de_jeu[0][5]= "1";
        GameScreen.grille_de_jeu[0][6]= "1";
 
        String expected = "match_nul";
        String result = GameScreen.check_victory();
        assertEquals(expected, result);
    }
    
   /**
     * Test Victoire Horizontale
     * Method : check_victory
     * Class : GameScreen
     */
    @Test
    public void test_victoire_horizontale() 
    {
        
        System.out.println("test_victoire_horizontale");
        
        GameScreen.grille_de_jeu[5][0]= "blue";
        GameScreen.grille_de_jeu[5][1]= "blue";
        GameScreen.grille_de_jeu[5][2]= "blue";
        GameScreen.grille_de_jeu[5][3]= "blue";
 
        String expected = "blue";
        String result = GameScreen.check_victory();
        assertEquals(expected, result);
    }

    
    /**
     * Test Victoire Verticale
     * Method : check_victory
     * Class : GameScreen
     */
    @Test
    public void test_victoire_verticale() 
    {
        
        System.out.println("test_victoire_verticale");
        
        GameScreen.grille_de_jeu[0][0]= "blue";
        GameScreen.grille_de_jeu[1][0]= "blue";
        GameScreen.grille_de_jeu[2][0]= "blue";
        GameScreen.grille_de_jeu[3][0]= "blue";
 
        String expected = "blue";
        String result = GameScreen.check_victory();
        assertEquals(expected, result);
    }
    
    
    /**
     * Test Victoire diagonale bas haut
     * Method : check_victory
     * Class : GameScreen
     */
    @Test
    public void test_victoire_diagonale_bas_haut() 
    {
        
        System.out.println("test_victoire_diagonale_bas_haut");
        
        GameScreen.grille_de_jeu[0][6]= "blue";
        GameScreen.grille_de_jeu[1][5]= "blue";
        GameScreen.grille_de_jeu[2][4]= "blue";
        GameScreen.grille_de_jeu[3][3]= "blue";
 
        String expected = "blue";
        String result = GameScreen.check_victory();
        assertEquals(expected, result);
    }
  
    /**
     * Test Victoire diagonale haut bas
     * Method : check_victory
     * Class : GameScreen
     */
    @Test
    public void test_victoire_diagonale_haut_bas() 
    {
        
        System.out.println("test_victoire_diagonale_haut_bas");
        
        GameScreen.grille_de_jeu[0][0]= "blue";
        GameScreen.grille_de_jeu[1][1]= "blue";
        GameScreen.grille_de_jeu[2][2]= "blue";
        GameScreen.grille_de_jeu[3][3]= "blue";
 
        String expected = "blue";
        String result = GameScreen.check_victory();
        assertEquals(expected, result);
    }
    
    /**
     * Test Images Existent
     * Method : 
     * Class : GameScreen
     */
    @Test
    public void test_images_existent() throws MalformedURLException 
    {
        
        System.out.println("test_images_existent");
        
        File image = new File("./pictures/blue.png");
        File image2 = new File("./pictures/orange.png");
        
        assertTrue(image.exists());
        assertTrue(image2.exists());
    }
    
    /**
     * Test Fichier Scores Existe
     * Method : 
     * Class : GameScreen
     */
    @Test
    public void test_fichier_scores_existe() 
    {
        
        System.out.println("test_fichier_scores_existe");
        
        // A FAIRE PLUS TARD
        
    }
    

}
