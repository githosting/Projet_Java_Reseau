
package puissance4.bean;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert; 
import javafx.scene.control.Alert.AlertType;

/**
    * Class containing methods to validate user inputs.
    * Ensure that the program manages all error cases linked to user inputs.
    * @version 2.0
*/
public class Validation {
    
    // Collection containing the detected errors list.
    public static final Map<String, String> ERREURS = new HashMap<>();
    
    /**
        * Check the input integrity from pseudo field.
        * @version 2.0
        * @param pseudo
        *       The pseudo given by the player. 
    */
    public static void validationPseudo(String pseudo){
        // If the pseudo is not empty and contains less than 15 characters.
        if(!pseudo.trim().isEmpty() && pseudo.length()<15)
        {
            // If the pseudo contains only numerics and letters.
            if(pseudo.matches("[a-zA-Z0-9]+")){
                ERREURS.remove("pseudo");
            }
            // If not, the associated error is added to the errors collection.
            else{
                ERREURS.put("pseudo","Caractères spéciaux non autorisés \n");
            }
        }
        // If not, the associated error is added to the errors collection.
        else
        {
           ERREURS.put("pseudo","Champ obligatoire, 15 caractères maximum \n");
        }
    } // End validationPseudo
    
    
    /**
        * Check the input integrity from adresse field.
        * @version 2.0
        * @param ip
        *       The IP address given by the player.
    */
    public static void validationIp(String ip){
        
        // The REGEX pattern the IP must stick to.
        String ip_pattern =
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        
        // If the IP is not empty.
        if(!ip.trim().isEmpty())
        {
            // If the IP stick to the REGEX pattern.
            if(ip.matches(ip_pattern)){
                ERREURS.remove("ip");
            }
            // If not, the associated error is added to the errors collection.
            else{
                ERREURS.put("ip","IP incorrecte \n");
            }
        }
        // If not, the associated error is added to the errors collection.
        else{
            ERREURS.put("ip","Champ obligatoire \n");
        }
    } // End validationIp
    

    /**
        * Check the input integrity from port field.
        * @version 2.0
        * @param port
        *       The port given by the player. 
    */
    public static void validationPort(String port){
        
        // If the port is not empty and contains 6 numeric characters maximum.
        if(!port.trim().isEmpty() && port.length() < 6 && port.matches("[0-9]+")){
            // If the port stands between 1024 and 65535. Authorised port range.
            if(Integer.parseInt(port)>=1024 && Integer.parseInt(port)<65535 ){ 
                ERREURS.remove("port");
            }
            // If not, the associated error is added to the errors collection.
            else{
                ERREURS.put("port","Veuillez renseigner un port entre 1024 et 65 535 \n");
            }
        }
        // If not, the associated error is added to the errors collection.
        else{
            ERREURS.put("port","Champ obligatoire, 5 caractères maximum, caractères spéciaux non autorisés \n");
        }
    } // End validationPort
    

    /**
        * Display detected errors to the player.
        * @version 2.0
        * @param No Parameters
        * @return String containing concatenated error messages.
    */
    public static String afficheErreur(){
        String listeErreur = ""; 
        // If the errors list is not empty.
        if(!ERREURS.isEmpty())
        {
            // Concatenate all errors into the string listeErreur.
            for(Map.Entry<String, String> erreur: ERREURS.entrySet()){
                listeErreur = listeErreur.concat(erreur.getKey().concat(" : ").concat(erreur.getValue()));
            }
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Message d'information");
            alert.setContentText(listeErreur);
            alert.showAndWait();
        }
        return listeErreur;
    }
}
