
package puissance4;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * 
 * @author ck
 */

public class Validation {
    public static final Map<String, String> ERREURS = new HashMap<>();
    
    /**
     * Vérifie que le pseudo comporte des lettres, chiffres et quelques caractères précis
     * @param pseudo  le pseudo renseigné par le joueur     
     */
    public static void validationPseudo(String pseudo){
        if(!pseudo.trim().isEmpty())
        {
            if(pseudo.matches("[a-zA-Z0-9]+")){
                ERREURS.remove("pseudo");
            }else{
                ERREURS.put("pseudo","Veuillez renseigner un pseudo correct \n");
            }
        }
        else
        {
           ERREURS.put("pseudo","Veuillez renseigner un pseudo correct \n");
        }
    }//fin validationPseudo
    
    /**
     * Vérifie si l'adresse internet renseignée est valide (IPv4)
     * @param ip l'adresse ip saisie par le joueur       
     */
    public static void validationIp(String ip){
        if(!ip.trim().isEmpty())
        {
            if(InetAddressValidator.getInstance().isValid(ip)){
                ERREURS.remove("ip");
            }else{
                ERREURS.put("ip","Veuillez renseigner une ip correcte \n");
            }
        }else{
            ERREURS.put("ip","Veuillez renseigner une ip correcte \n");
        }
    }//fin validationIp
    
    /**
     * Vérifie si le port est bien compris entre l'interval 1024 et 65 535
     * @param port le port renseigné par le joueur        
     */
    public static void validationPort(String port){
        if(!port.trim().isEmpty()){
            if(Integer.parseInt(port)>=1024 && Integer.parseInt(port)<65535){ 
                ERREURS.remove("port");
            }else{
                ERREURS.put("port","Veuillez renseigner un port entre 1024 et 65 535 \n");
            }
        }else{
            ERREURS.put("port","Veuillez renseigner un port entre 1024 et 65 535 \n");
        }
    }//fin validationPort
    
    /**
     * Affiche les erreurs de saisie du joueur
     * @return listeErreur les erreurs de saisies du joueur      
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
