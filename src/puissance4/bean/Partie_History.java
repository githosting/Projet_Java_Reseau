
package puissance4.bean;


/**
    * Class allowing the creation of Partie_History objects representing games we want to save in the history.
    * @version 1.0
*/
public class Partie_History 
{
    // Attributes
    public String pseudo_moi;
    public String pseudo_adversaire;
    public String victoire_defaite;
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
        * Constructor without parameters for Partie_History class.
        * @version 1.0
        * @param No Parameters
        * @return A Partie_History instance
    */
    public Partie_History(){}
            
    /**
        * Constructor with parameters for Partie_History class.
        * @version 1.0
        * @param String un_pseudo_moi
        * 	The pseudo of the player who save the game in history.
        * @param String un_pseudo_adversaire
        * 	The pseudo of the player opponent.
        * @param String un_victoire_defaite
        * 	The game result for the player who save the game in history.
        * @param String[][] un_grille_de_jeu
        * 	The game grid configuration at game end.
        * @return A Partie_History instance
    */
    public Partie_History(String un_pseudo_moi, String un_pseudo_adversaire, String un_victoire_defaite, String[][] un_grille_de_jeu)
    {
        this.pseudo_moi = un_pseudo_moi;
        this.pseudo_adversaire = un_pseudo_adversaire;
        this.victoire_defaite = un_victoire_defaite;
        this.grille_de_jeu = un_grille_de_jeu;
    }
    
    
}
