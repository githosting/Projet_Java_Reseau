/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;
 
import java.net.ServerSocket;
import java.net.Socket;

    
/**
 *
 * @author Alex
 */
public class Partie_Save {
    
public String pseudo_moi;
public String pseudo_adversaire;
public String victoire_defaite;
public String textfield_port;
public String grille_de_jeu [][] =   
        {
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"}
        };   

public Partie_Save(){
    
}


public Partie_Save(String un_pseudo_moi, String un_pseudo_adversaire, String un_victoire_defaite, String[][] un_grille_de_jeu, String textfield_port)
    {
        this.pseudo_moi = un_pseudo_moi;
        this.pseudo_adversaire = un_pseudo_adversaire;
        this.victoire_defaite = un_victoire_defaite;
        this.grille_de_jeu = un_grille_de_jeu;
        this.textfield_port = textfield_port;
    }

}

