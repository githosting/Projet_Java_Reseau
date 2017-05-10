
package puissance4;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import puissance4.bean.Validation;

/**
 * 
 * @author ck
 */

public class ValidationTest {
    
    //liste de pseudos érronés
    private ArrayList<String> pseudos = new ArrayList<>();
    private ArrayList<String> ips = new ArrayList<>();
    private ArrayList<String> ports = new ArrayList<>();
    
    public ValidationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //pseudos non corrects
        pseudos.add(" ");
        pseudos.add("!");
        pseudos.add("<p>");
        pseudos.add("o's");
        //ip non correctes
        ips.add("256.0.0.0");
        ips.add("");
        ips.add("<!&-");
        //ports non correctes
        ports.add("0");
        ports.add("80");
        ports.add("65600");
        ports.add("");
        ports.add("4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444");
        ports.add("<'9&!");
    }
    
    @After
    public void tearDown() {
        pseudos.clear();
        pseudos = null;
        ips.clear();
        ips = null;
        ports.clear();
        ports = null;
    }

    /**
     * Test of validationPseudo method, of class Validation.
     */
    @Test
    public void testValidationPseudo() {
        System.out.println("validationPseudo");
        for (String pseudo: pseudos){
            Validation.validationPseudo(pseudo);
            assertTrue(Validation.ERREURS.containsKey("pseudo"));
        }
    }

    /**
     * Test of validationIp method, of class Validation.
     */
    @Test
    public void testValidationIp() {
        System.out.println("validationIp");
        for (String ip: ips){
            Validation.validationIp(ip);
            assertTrue(Validation.ERREURS.containsKey("ip"));
            Validation.afficheErreur();
        }
    }

    /**
     * Test of validationPort method, of class Validation.
     */
    @Test
    public void testValidationPort() {
        System.out.println("validationPort");
        for (String port: ports){
            Validation.validationPort(port);
            assertTrue(Validation.ERREURS.containsKey("port"));
            Validation.afficheErreur();
        }
    }

    /**
     * Test of afficheErreur method, of class Validation.
     */
    @Test
    public void testAfficheErreur() {
        System.out.println("afficheErreur");
        Validation.validationPseudo("toto");
        Validation.validationIp("127.0.0.1");
        Validation.validationPort("4200");
        String val = Validation.afficheErreur();
        assertTrue(Validation.afficheErreur().isEmpty());
        
    }
}
