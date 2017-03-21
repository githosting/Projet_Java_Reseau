
package puissance4;


public class Message 
{
    public int colIndex = 0;
    public int rowIndex = 0;
    public String imagePath = null;
    public String victoryColor = null;
    public String pseudo = null;
    
    public int get_colIndex() { return colIndex; }
    public int get_rowIndex() { return rowIndex; }
    public String get_imagePath() { return imagePath; }
    public String get_victoryColor() { return victoryColor; }
    public String get_pseudo() { return pseudo; }
    
    public Message()
    {
        this.colIndex = 0;
        this.rowIndex = 0;
        this.imagePath = null;
        this.victoryColor = null;
        this.pseudo = null;
    }
    
    public Message(int un_colIndex, int un_rowIndex, String un_imagePath, String un_victoryColor, String un_pseudo)
    {
        this.colIndex = un_colIndex;
        this.rowIndex = un_rowIndex;
        this.imagePath = un_imagePath;
        this.victoryColor = un_victoryColor;
        this.pseudo = un_pseudo;
    }
}
