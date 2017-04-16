
package puissance4;


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
    
    
    public Partie_History(){}
            
            
    public Partie_History(String un_pseudo_moi, String un_pseudo_adversaire, String un_victoire_defaite, String[][] un_grille_de_jeu)
    {
        this.pseudo_moi = un_pseudo_moi;
        this.pseudo_adversaire = un_pseudo_adversaire;
        this.victoire_defaite = un_victoire_defaite;
        this.grille_de_jeu = un_grille_de_jeu;
    }
    
    
}
