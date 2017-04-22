
package puissance4;


/**
    * Classe permettant la création d'objets Message qui seront échangés entre les joueurs.
    * @author Bettinger
    * @version 2.0
    */
public class Message 
{
    // Attributes
    public int colIndex = 0;
    public int rowIndex = 0;
    public String imagePath = null;
    public String victoryColor = null;
    public String pseudo = null;
    
    // Accessors
    public int get_colIndex() { return colIndex; }
    public int get_rowIndex() { return rowIndex; }
    public String get_imagePath() { return imagePath; }
    public String get_victoryColor() { return victoryColor; }
    public String get_pseudo() { return pseudo; }
    
    /**
        * Constructeur sans paramètres de la classe Message.
        * @author Bettinger
        * @version 2.0
        * @param No Parameters
        * @return Une instance de Message
        */
    public Message()
    {
        this.colIndex = 0;
        this.rowIndex = 0;
        this.imagePath = null;
        this.victoryColor = null;
        this.pseudo = null;
    }
    
    /**
        * Constructeur avec paramètres de la classe Message.
        * @author Bettinger
        * @version 2.0
        * @param int un_colIndex
        *       Indice de la colonne de la grille tout juste modifiée.
        * @param int un_rowIndex
        *       Indice de la ligne de la grille tout juste modifiée.
        * @param String un_imagePath
        *       Chemin vers l'image à insérer.
        * @param String un_victoryColor
        *       Retour de la fonction de vérification de victoire.
        * @param String un_pseudo
        *       Pseudo du joueur envoyant le message.
        * @return Une instance de Message
        */
    public Message(int un_colIndex, int un_rowIndex, String un_imagePath, String un_victoryColor, String un_pseudo)
    {
        this.colIndex = un_colIndex;
        this.rowIndex = un_rowIndex;
        this.imagePath = un_imagePath;
        this.victoryColor = un_victoryColor;
        this.pseudo = un_pseudo;
    }
}
