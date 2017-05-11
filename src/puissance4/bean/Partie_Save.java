
package puissance4.bean;
 
import java.net.ServerSocket;
import java.net.Socket;

    
/**
    * Class allowing the creation of a save objects representing games we want to save.
    * @version 1.0
*/
public class Partie_Save {
    
public String pseudo_moi;
public String pseudo_adversaire;
public String victoire_defaite;
public String textfield_port;
public Boolean myTurn;
public String grille_de_jeu [][] =   
        {
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"}
        };   

/**
        * Constructor without parameters for Partie_Save class.
        * @version 1.0
    */
public Partie_Save(){
    
}

/**
        * Constructor with parameters for Partie_History class.
        * @version 1.0
        * @param un_pseudo_moi The pseudo of the player who save the game in history.
        * @param un_pseudo_adversaire The pseudo of the player opponent.
        * @param un_victoire_defaite The game result for the player who save the game in history.
        * @param un_grille_de_jeu The game grid configuration at game end.
        * @param myTurn The turn of the player.
        * @param textfield_port the port of the server
    */
public Partie_Save(String un_pseudo_moi, String un_pseudo_adversaire, String un_victoire_defaite, String[][] un_grille_de_jeu,Boolean myTurn, String textfield_port)
    {
        this.pseudo_moi = un_pseudo_moi;
        this.pseudo_adversaire = un_pseudo_adversaire;
        this.victoire_defaite = un_victoire_defaite;
        this.grille_de_jeu = un_grille_de_jeu;
        this.myTurn = myTurn;
        this.textfield_port = textfield_port;
    }

}

