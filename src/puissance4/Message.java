
package puissance4;


/**
    * Class allowing creation of Message objects which are exchanged between players.
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
    public String[][] grille_de_jeu = null;
    
    // Accessors
    public int get_colIndex() { return colIndex; }
    public int get_rowIndex() { return rowIndex; }
    public String get_imagePath() { return imagePath; }
    public String get_victoryColor() { return victoryColor; }
    public String get_pseudo() { return pseudo; }
    
    /**
        * Constructor without parameters for Message class.
        * @author Bettinger
        * @version 2.0
        * @param No Parameters
        * @return A Message instance.
        */
    public Message()
    {
        this.colIndex = 0;
        this.rowIndex = 0;
        this.imagePath = null;
        this.victoryColor = null;
        this.pseudo = null;
        this.grille_de_jeu = null;
    }
    
    /**
        * Constructor with parameters for Message class.
        * @version 2.0
        * @param int un_colIndex
        *       Game grid column index just modified.
        * @param int un_rowIndex
        *       Game grid row index just modified.
        * @param String un_imagePath
        *       Path to the picture we have to display.
        * @param String un_victoryColor
        *       Return from the checkVictory function.
        * @param String un_pseudo
        *       Pseudo of the player sending the message.
        * @return A Message instance
        */
    public Message(int un_colIndex, int un_rowIndex, String un_imagePath, String un_victoryColor, String un_pseudo)
    {
        this.colIndex = un_colIndex;
        this.rowIndex = un_rowIndex;
        this.imagePath = un_imagePath;
        this.victoryColor = un_victoryColor;
        this.pseudo = un_pseudo;
        System.out.println(colIndex + "  " + rowIndex + "  " + imagePath + "  " + victoryColor + "  " + pseudo + "  " );
    }
    public Message(int un_colIndex, int un_rowIndex, String un_imagePath, String un_victoryColor, String un_pseudo, String[][] grille_de_jeu)
    {
        this.colIndex = un_colIndex;
        this.rowIndex = un_rowIndex;
        this.imagePath = un_imagePath;
        this.victoryColor = un_victoryColor;
        this.pseudo = un_pseudo;
        this.grille_de_jeu = grille_de_jeu;
        
        System.out.println(colIndex + "  " + rowIndex + "  " + imagePath + "  " + victoryColor + "  " + pseudo + "  " );
    }
}
