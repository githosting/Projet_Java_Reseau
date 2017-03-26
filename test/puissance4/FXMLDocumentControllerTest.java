
package puissance4;

import java.io.File;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FXMLDocumentControllerTest 
{
    
    public FXMLDocumentControllerTest() {}
    
    @BeforeClass
    public static void setUpClass() 
    {
        
        System.out.println("Avant tous les tests.");
        
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        
        System.out.println("Après tous les tests.");
        
    }
    
    @Before
    public void setUp() 
    {
        
        System.out.println("Avant chaque test.");
        
        // Réinitialisation de la grille de jeu.
        for(int i=0 ; i<FXMLDocumentController.grille_de_jeu.length ; i++)
        {
            for(int y=0 ; y<FXMLDocumentController.grille_de_jeu[i].length ; y++)
                FXMLDocumentController.grille_de_jeu[i][y] = "null";
        }
    }
    
    @After
    public void tearDown() 
    {
        
        System.out.println("Après chaque test.");
        
    }
    
    
    /**
     * Test Pas Victoire & Pas Match Nul
     * Method : check_victory
     * Class : FXMLDocumentController
     */
    @Test
    public void test_pas_victoire() 
    {
        
        System.out.println("test_pas_victoire");

        String expected = "null";
        String result = FXMLDocumentController.check_victory();
        assertEquals(expected, result);
    }
    
    
    /**
     * Test Match Nul
     * Method : check_victory
     * Class : FXMLDocumentController
     */
    @Test
    public void test_match_nul() 
    {
        
        System.out.println("test_match_nul");
        
        FXMLDocumentController.grille_de_jeu[0][0]= "1";
        FXMLDocumentController.grille_de_jeu[0][1]= "1";
        FXMLDocumentController.grille_de_jeu[0][2]= "1";
        FXMLDocumentController.grille_de_jeu[0][3]= "1";
        FXMLDocumentController.grille_de_jeu[0][4]= "1";
        FXMLDocumentController.grille_de_jeu[0][5]= "1";
        FXMLDocumentController.grille_de_jeu[0][6]= "1";
 
        String expected = "match_nul";
        String result = FXMLDocumentController.check_victory();
        assertEquals(expected, result);
    }
    
    
    /**
     * Test Victoire Horizontale
     * Method : check_victory
     * Class : FXMLDocumentController
     */
    @Test
    public void test_victoire_horizontale() 
    {
        
        System.out.println("test_victoire_horizontale");
        
        FXMLDocumentController.grille_de_jeu[5][0]= "blue";
        FXMLDocumentController.grille_de_jeu[5][1]= "blue";
        FXMLDocumentController.grille_de_jeu[5][2]= "blue";
        FXMLDocumentController.grille_de_jeu[5][3]= "blue";
 
        String expected = "blue";
        String result = FXMLDocumentController.check_victory();
        assertEquals(expected, result);
    }

    
    /**
     * Test Victoire Verticale
     * Method : check_victory
     * Class : FXMLDocumentController
     */
    @Test
    public void test_victoire_verticale() 
    {
        
        System.out.println("test_victoire_verticale");
        
        FXMLDocumentController.grille_de_jeu[0][0]= "blue";
        FXMLDocumentController.grille_de_jeu[1][0]= "blue";
        FXMLDocumentController.grille_de_jeu[2][0]= "blue";
        FXMLDocumentController.grille_de_jeu[3][0]= "blue";
 
        String expected = "blue";
        String result = FXMLDocumentController.check_victory();
        assertEquals(expected, result);
    }
    
    
    /**
     * Test Victoire diagonale bas haut
     * Method : check_victory
     * Class : FXMLDocumentController
     */
    @Test
    public void test_victoire_diagonale_bas_haut() 
    {
        
        System.out.println("test_victoire_diagonale_bas_haut");
        
        FXMLDocumentController.grille_de_jeu[0][6]= "blue";
        FXMLDocumentController.grille_de_jeu[1][5]= "blue";
        FXMLDocumentController.grille_de_jeu[2][4]= "blue";
        FXMLDocumentController.grille_de_jeu[3][3]= "blue";
 
        String expected = "blue";
        String result = FXMLDocumentController.check_victory();
        assertEquals(expected, result);
    }
  
    
    /**
     * Test Victoire diagonale haut bas
     * Method : check_victory
     * Class : FXMLDocumentController
     */
    @Test
    public void test_victoire_diagonale_haut_bas() 
    {
        
        System.out.println("test_victoire_diagonale_haut_bas");
        
        FXMLDocumentController.grille_de_jeu[0][0]= "blue";
        FXMLDocumentController.grille_de_jeu[1][1]= "blue";
        FXMLDocumentController.grille_de_jeu[2][2]= "blue";
        FXMLDocumentController.grille_de_jeu[3][3]= "blue";
 
        String expected = "blue";
        String result = FXMLDocumentController.check_victory();
        assertEquals(expected, result);
    }
    

    /**
     * Test Images Existent
     * Method : btn_connect, btn_server
     * Class : FXMLDocumentController
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
     * Test Réinitialisation du jeu
     * Method : 
     * Class : FXMLDocumentController
     */
    @Test
    public void test_reinitialisation_jeu() 
    {
        
        System.out.println("test_reinitialisation_jeu");
        
        // A FAIRE PLUS TARD
        
    }    
    
    
    /**
     * Test Fichier Scores Existe
     * Method : 
     * Class : FXMLDocumentController
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
     * Class : FXMLDocumentController
     */
    @Test
    public void test_fichier_sauvegardes_existe() 
    {
        
        System.out.println("test_fichier_sauvegardes_existe");
        
        // A FAIRE PLUS TARD
        
    }
    
    
}
