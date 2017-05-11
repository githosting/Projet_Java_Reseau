
package puissance4.bean;


/**
    * Class allowing creation of Message objects which are exchanged between players.
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
    public Boolean my_turn;
    // Accessors
    public int get_colIndex() { return colIndex; }
    public int get_rowIndex() { return rowIndex; }
    public String get_imagePath() { return imagePath; }
    public String get_victoryColor() { return victoryColor; }
    public String get_pseudo() { return pseudo; }
    
    /**
        * Constructor without parameters for Message class.
        * @version 2.0
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
        * @param un_colIndex Game grid column index just modified.
        * @param un_rowIndex Game grid row index just modified.
        * @param un_imagePath Path to the picture we have to display.
        * @param un_victoryColor Return from the checkVictory function.
        * @param un_pseudo Pseudo of the player sending the message.
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
    public Message(int un_colIndex, int un_rowIndex, String un_imagePath, String un_victoryColor, String un_pseudo, Boolean my_turn, String[][] grille_de_jeu)
    {
        this.colIndex = un_colIndex;
        this.rowIndex = un_rowIndex;
        this.imagePath = un_imagePath;
        this.victoryColor = un_victoryColor;
        this.pseudo = un_pseudo;
        this.grille_de_jeu = grille_de_jeu;
        this.my_turn = my_turn;
        System.out.println(colIndex + "  " + rowIndex + "  " + imagePath + "  " + victoryColor + "  " + pseudo + "  " );
    }
}
