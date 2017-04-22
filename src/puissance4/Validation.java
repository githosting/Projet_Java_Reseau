
package puissance4;

import java.util.HashMap;
import java.util.Map;

/**
    * Classe contenant les méthodes permettant la validation des saisies de l'utilisateur.
    * Permet de s'assurer que le programme gère tous les cas d'erreur possible liés aux saisies des utilisateurs.
    * @author Kessler
    * @version 2.0
    */
public class Validation {
    
    // Collection contenant la liste des erreurs détectées.
    public static final Map<String, String> ERREURS = new HashMap<>();
    
    /**
        * Vérifie l'intégrité de la saisie du champ pseudo.
        * @author Kessler
        * @version 2.0
        * @param pseudo
        *       Le pseudo renseigné par le joueur. 
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
        * Vérifie l'intégrité de la saisie du champ adresse.
        * @author Kessler
        * @version 2.0
        * @param ip
        *       L'adresse IP saisie par le joueur.
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
        * Vérifie l'intégrité de la saisie du champ port.
        * @author Kessler
        * @version 2.0
        * @param port
        *       Le port renseigné par le joueur. 
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
        * Affiche au joueur les erreurs de saisie détectées.
        * @author Kessler
        * @version 2.0
        * @param No Parameters
        * @return String contenant les messages d'erreur concaténés.
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
