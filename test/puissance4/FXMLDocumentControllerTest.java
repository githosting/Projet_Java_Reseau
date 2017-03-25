
package puissance4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FXMLDocumentControllerTest {
    
    public FXMLDocumentControllerTest() {
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
     * Test of getLabelInfo method, of class FXMLDocumentController.
     * @throws java.io.IOException
     */
    @Test
    public void testGetLabelInfo() throws IOException {
        System.out.println("getLabelInfo");
        Label result = FXMLDocumentController.getLabelInfo();
        assertNull(result);
    }

    /**
     * Test of getGridPane method, of class FXMLDocumentController.
     */
    @Test
    public void testGetGridPane() {
        System.out.println("getGridPane");
        FXMLDocumentController instance = new FXMLDocumentController();
        GridPane expResult = null;
        GridPane result = instance.getGridPane();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cellClick method, of class FXMLDocumentController.
     */
    @Test
    public void testCellClick() throws Exception {
        System.out.println("cellClick");
        MouseEvent event = null;
        FXMLDocumentController instance = new FXMLDocumentController();
        instance.cellClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of check_victory method, of class FXMLDocumentController.
     */
    @Test
    public void testCheck_victory() {
        System.out.println("check_victory");
        FXMLDocumentController instance = new FXMLDocumentController();
        String expResult = "";
        String result = instance.check_victory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class FXMLDocumentController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        FXMLDocumentController instance = new FXMLDocumentController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
