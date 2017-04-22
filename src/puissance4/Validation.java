
package puissance4;

import java.util.HashMap;
import java.util.Map;

/**
    * Class containing methods to validate user inputs.
    * Ensure that the program manages all error cases linked to user inputs.
    * @author Kessler
    * @version 2.0
    */
public class Validation {
    
    // Collection contenant la liste des erreurs détectées.
    public static final Map<String, String> ERREURS = new HashMap<>();
    
    /**
        * Check the input integrity from pseudo field.
        * @author Kessler
        * @version 2.0
        * @param pseudo
        *       The pseudo given by the player. 
        * @return void
        */
    public static void validationPseudo(String pseudo){
        if(!pseudo.trim().isEmpty() && pseudo.length()<15)
        {
            if(pseudo.matches("[a-zA-Z0-9]+")){
                ERREURS.remove("pseudo");
            }else{
                ERREURS.put("pseudo","Caractères spéciaux non autorisés \n");
            }
        }
        else
        {
           ERREURS.put("pseudo","Champ obligatoire, 15 caractères maximum \n");
        }
    } // Fin validationPseudo
    
    
    /**
        * Check the input integrity from adresse field.
        * @author Kessler
        * @version 2.0
        * @param ip
        *       The IP address given by the player.
        * @return void
        */
    public static void validationIp(String ip){
        
        String ip_pattern =
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        
        if(!ip.trim().isEmpty())
        {
            if(ip.matches(ip_pattern)){
                ERREURS.remove("ip");
            }else{
                ERREURS.put("ip","IP incorrecte \n");
            }
        }else{
            ERREURS.put("ip","Champ obligatoire \n");
        }
    } // Fin validationIp
    

    /**
        * Check the input integrity from port field.
        * @author Kessler
        * @version 2.0
        * @param port
        *       The port given by the player. 
        * @return void
        */
    public static void validationPort(String port){
        if(!port.trim().isEmpty() && port.length()<6 && port.matches("([0-9]{4})+")){
            if(Integer.parseInt(port)>=1024 && Integer.parseInt(port)<65535 ){ 
                ERREURS.remove("port");
            }else{
                ERREURS.put("port","Veuillez renseigner un port entre 1024 et 65 535 \n");
            }
        }else{
            ERREURS.put("port","Champ obligatoire, 5 caractères maximum, caractères spéciaux non autorisés \n");
        }
    } // Fin validationPort
    

    /**
        * Display detected errors to the player.
        * @author Kessler
        * @version 2.0
        * @param No Parameters
        * @return String containing concatenated error messages.
        */
    public static String afficheErreur(){
        String listeErreur = ""; 
        if(!ERREURS.isEmpty())
        {
            for(Map.Entry<String, String> erreur: ERREURS.entrySet()){
                listeErreur = listeErreur.concat(erreur.getKey().concat(" : ").concat(erreur.getValue()));
            
            }
            
        }
        return listeErreur;
    }
}
