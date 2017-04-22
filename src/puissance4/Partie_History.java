
package puissance4;


/**
    * Classe permettant la création d'objets Partie_History représentant les parties que l'on souhaite historiser.
    * @author Bettinger
    * @version 1.0
    */
public class Partie_History 
{
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
        * Constructeur sans paramètres de la classe Partie_History.
        * @author Bettinger
        * @version 1.0
        * @param No Parameters
        * @return Une instance de Partie_History
        */
    public Partie_History(){}
            
    
    /**
        * Constructeur avec paramètres de la classe Partie_History.
        * @author Bettinger
        * @version 1.0
        * @param String un_pseudo_moi
        * 	Le pseudo du joueur historisant cette partie.
        * @param String un_pseudo_adversaire
        * 	Le pseudo de l'adversaire du joueur historisant cette partie.
        * @param String un_victoire_defaite
        * 	Le résultat de la partie pour le joueur historisant cette partie.
        * @param String[][] un_grille_de_jeu
        * 	La configuration de la grille de jeu en fin de partie.
        * @return Une instance de Partie_History
        */
    public Partie_History(String un_pseudo_moi, String un_pseudo_adversaire, String un_victoire_defaite, String[][] un_grille_de_jeu)
    {
        this.pseudo_moi = un_pseudo_moi;
        this.pseudo_adversaire = un_pseudo_adversaire;
        this.victoire_defaite = un_victoire_defaite;
        this.grille_de_jeu = un_grille_de_jeu;
    }
    
    
}
