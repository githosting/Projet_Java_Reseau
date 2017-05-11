package puissance4.bean;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import puissance4.controller.CONTROLLER_Connexion_Client;
import puissance4.controller.CONTROLLER_Connexion_Server;
import puissance4.controller.CONTROLLER_Jeu;

public class Partie {
     // Attributes
    private String player_color = "";
    private boolean my_turn = false;
    private String player_type;
    private boolean client_connecte = false;
    private boolean game_start = false;
    private boolean game_over = false;
    private String image_path;
    private String image_path_adversaire;
    private String pseudo;
    private String pseudo_adversaire;
    // The game matrix.
    private String grille_de_jeu [][] =   CONTROLLER_Jeu.grille_de_jeu;
    private Boolean charger = false;
    public static Partie gameChargement = new Partie();
    @FXML
    private Label label_info;
    @FXML
    private Label label_versus;
    @FXML
   // private GridPane gp;
    
    // Accessors
    public Label getLabelInfo() { return getLabel_info(); }
    public Label getLabelVersus() { return getLabel_versus(); }
   // public GridPane getGridPane() { return getGp(); }
        
    public Partie(){
        
    }
     /**
        * Initialise a player variables considering his type.
        * @version 1.0
        * @param String type_player
        * 	The player type : client or server.
        * @param String pseudo_name
        * 	The player pseudo.
        * @return void
        * @throws MalformedURLException
        */
    public void setInformationPlayer (String type_player, String pseudo_name)throws MalformedURLException {
        if(type_player == "client"){
            setPseudo(pseudo_name);
            setPlayer_color("blue");
            setImage_path_adversaire(new Object(){}.getClass().getResource("/resources/orange.png").toExternalForm());
            setImage_path(new Object(){}.getClass().getResource("/resources/blue.png").toExternalForm());
            setPlayer_type("client");
        }
        if (type_player == "server"){
            setPseudo(pseudo_name);
            setPlayer_color("orange");
           
            setImage_path(new Object(){}.getClass().getResource("/resources/orange.png").toExternalForm());
            setImage_path_adversaire(new Object(){}.getClass().getResource("/resources/blue.png").toExternalForm());
            setPlayer_type("server");
        }
    }
         /**
        * Check victory conditions after a player move.
        * @version 3.0
        * @param No Parameters
        * @return String representing game state or winner color after a player move.
        */
     public  String check_victory()
    {
        grille_de_jeu = CONTROLLER_Jeu.grille_de_jeu;
        String actual_color;
        
        // If there is a draw match.
        if(!grille_de_jeu[0][0].equals("null")
            && !grille_de_jeu[0][1].equals("null")
            && !grille_de_jeu[0][2].equals("null")
            && !grille_de_jeu[0][3].equals("null")
            && !grille_de_jeu[0][4].equals("null")
            && !grille_de_jeu[0][5].equals("null")
            && !grille_de_jeu[0][6].equals("null")
          )
        {
            return "match_nul";
        }
        
        // For each grid element, explore all possibilities of 4 consecutives pieces from it.
        for(int i=0; i<6; i++)
        { 
            for(int j = 0; j < 7; j++)
            { 
                actual_color = getGrille_de_jeu()[i][j];
				
                // If 4 consecutives pieces are found from the current grid element.  
                if( 
                    (!(actual_color.equals("null")))
                    &&
                    (
                        (j <= 3 && getGrille_de_jeu()[i][j+1].equals(actual_color) && getGrille_de_jeu()[i][j+2].equals(actual_color) && getGrille_de_jeu()[i][j+3].equals(actual_color))
                        || // To the left.
                        (j >= 3 && getGrille_de_jeu()[i][j-1].equals(actual_color) && getGrille_de_jeu()[i][j-2].equals(actual_color) && getGrille_de_jeu()[i][j-3].equals(actual_color))
                        || // To top.
                        (i >= 3 && getGrille_de_jeu()[i-1][j].equals(actual_color) && getGrille_de_jeu()[i-2][j].equals(actual_color) && getGrille_de_jeu()[i-3][j].equals(actual_color))
                        || // To bottom.
                        (i <= 2 && getGrille_de_jeu()[i+1][j].equals(actual_color) && getGrille_de_jeu()[i+2][j].equals(actual_color) && getGrille_de_jeu()[i+3][j].equals(actual_color))
                        || // To diagonal top right.
                        (i >= 3 && j <= 3 && getGrille_de_jeu()[i-1][j+1].equals(actual_color) && getGrille_de_jeu()[i-2][j+2].equals(actual_color) && getGrille_de_jeu()[i-3][j+3].equals(actual_color))
                        || // To diagonal top left.
                        (i >= 3 && j >= 3 && getGrille_de_jeu()[i-1][j-1].equals(actual_color) && getGrille_de_jeu()[i-2][j-2].equals(actual_color) && getGrille_de_jeu()[i-3][j-3].equals(actual_color))
                        || // To diagonal bottom right.
                        (i <= 2 && j <= 3 && getGrille_de_jeu()[i+1][j+1].equals(actual_color) && getGrille_de_jeu()[i+2][j+2].equals(actual_color) && getGrille_de_jeu()[i+3][j+3].equals(actual_color))
                        || // To diagonal bottom left.
                        (i <= 2 && j >= 3 && getGrille_de_jeu()[i+1][j-1].equals(actual_color) && getGrille_de_jeu()[i+2][j-2].equals(actual_color) && getGrille_de_jeu()[i+3][j-3].equals(actual_color))
                    )
                )
                {
                    // Return the winner player color.
                    return actual_color;
                }  
            } 
        }
        return "null";
    }
          /**
        * Manage the game save or history.
        * @version 1.0
        * @param String param_pseudo_moi
        * 	The pseudo of the player saving the game.
        * @param String param_pseudo_adversaire
        * 	The pseudo of his opponent.
        * @param String param_victoire_defaite
        * 	The game result for the player saving the game.
        * @param String[][] param_grille_de_jeu
        * 	The game grid configuration at game end.
        * @param String param_pseudo_vainqueur
        * 	The winner pseudo.
        * @return void
    */
     public void sauvegarde(EnumSauvegarde type, String param_victoire_defaite, String[][] param_grille_de_jeu, String param_pseudo_vainqueur) throws JsonProcessingException{
        if(type == EnumSauvegarde.Historisation){
            historiser_partie(this.getPseudo(), this.getPseudo_adversaire(), param_victoire_defaite, param_grille_de_jeu, param_pseudo_vainqueur);
        }else{
            sauvegarde_partie(this.getPseudo(), this.getPseudo_adversaire(), param_victoire_defaite, param_grille_de_jeu, param_pseudo_vainqueur);
        }
    }
          /**
        * Manage the game save.
        * @version 1.0
        * @param String param_pseudo_moi
        * 	The pseudo of the player saving the game.
        * @param String param_pseudo_adversaire
        * 	The pseudo of his opponent.
        * @param String param_victoire_defaite
        * 	The game result for the player saving the game.
        * @param String[][] param_grille_de_jeu
        * 	The game grid configuration at game end.
        * @param String param_pseudo_vainqueur
        * 	The winner pseudo.
        * @return void
    */
    public void sauvegarde_partie(String param_pseudo_moi, String param_pseudo_adversaire, String param_victoire_defaite, String[][] param_grille_de_jeu, String param_pseudo_vainqueur) throws JsonProcessingException
    {
        if(param_victoire_defaite.equals("Défaite"))
        {
            if(param_pseudo_vainqueur.equals(getPseudo()))
                param_victoire_defaite = "Victoire";
        }
        
        // Create the Partie_History object we want to save.
        String port;
        port = CONTROLLER_Connexion_Server.port_serveur != null ? CONTROLLER_Connexion_Server.port_serveur : new Integer(CONTROLLER_Connexion_Client.socket.getPort()).toString();
        Partie_Save save = new Partie_Save(param_pseudo_moi, param_pseudo_adversaire, param_victoire_defaite, param_grille_de_jeu, my_turn ,port);
        
        // Save the object, serialised with JSON, in a file on the player computer.
        String save_JSON = "";
        ObjectMapper mapper = new ObjectMapper();
        // Serialisation JSON.
        save_JSON = mapper.writeValueAsString(save);
        // Create the file name : save_ followed by a timestamp.
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String timestamp = ts.toString().replace(':', '-').replace('.', '_');
        String nom_fichier = "game_" + timestamp + ".txt";
        // Save the file on disk if it doesn't already exist.
        File repertoire = new File("./games");
        if(!repertoire.exists()){
            repertoire.mkdir();
        }
        File f = new File("./games/" + nom_fichier);
        if(!f.exists())
        {
            List<String> lines = Arrays.asList(save_JSON);
            Path file = Paths.get("./games/" + nom_fichier);
            try
            {
                Files.write(file, lines, Charset.forName("UTF-8"));
            } catch (IOException ex) {Logger.getLogger(CONTROLLER_Jeu.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }
    
     /**
        * Manage the automatic game save into history when it ends.
        * @version 1.0
        * @param String param_pseudo_moi
        * 	The pseudo of the player saving the game.
        * @param String param_pseudo_adversaire
        * 	The pseudo of his opponent.
        * @param String param_victoire_defaite
        * 	The game result for the player saving the game.
        * @param String[][] param_grille_de_jeu
        * 	The game grid configuration at game end.
        * @param String param_pseudo_vainqueur
        * 	The winner pseudo.
        * @return void
    */
    public void historiser_partie(String param_pseudo_moi, String param_pseudo_adversaire, String param_victoire_defaite, String[][] param_grille_de_jeu, String param_pseudo_vainqueur) throws JsonProcessingException
    {
        if(param_victoire_defaite.equals("Défaite"))
        {
            if(param_pseudo_vainqueur.equals(getPseudo()))
                param_victoire_defaite = "Victoire";
        }
        
        // Create the Partie_History object we want to save.
        Partie_History save = new Partie_History(param_pseudo_moi, param_pseudo_adversaire, param_victoire_defaite, param_grille_de_jeu);
        
        // Save the object, serialised with JSON, in a file on the player computer.
        String save_JSON = "";
        ObjectMapper mapper = new ObjectMapper();
        // Serialisation JSON.
        save_JSON = mapper.writeValueAsString(save);
        // Create the file name : save_ followed by a timestamp.
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String timestamp = ts.toString().replace(':', '-').replace('.', '_');
        String nom_fichier = "save_" + timestamp + ".txt";
        // Save the file on disk if it doesn't already exist.
        File repertoire = new File("./saves");
        if(!repertoire.exists()){
            repertoire.mkdir();
        }
        File f = new File("./saves/" + nom_fichier);
        if(!f.exists())
        {
            List<String> lines = Arrays.asList(save_JSON);
            Path file = Paths.get("./saves/" + nom_fichier);
            try
            {
                Files.write(file, lines, Charset.forName("UTF-8"));
            } catch (IOException ex) {Logger.getLogger(CONTROLLER_Jeu.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }

    /**
     * @return the player_color
     */
    public String getPlayer_color() {
        return player_color;
    }

    /**
     * @param player_color the player_color to set
     */
    public void setPlayer_color(String player_color) {
        this.player_color = player_color;
    }

    /**
     * @return the my_turn
     */
    public boolean isMy_turn() {
        return my_turn;
    }

    /**
     * @param my_turn the my_turn to set
     */
    public void setMy_turn(boolean my_turn) {
        this.my_turn = my_turn;
    }

    /**
     * @return the player_type
     */
    public String getPlayer_type() {
        return player_type;
    }

    /**
     * @param player_type the player_type to set
     */
    public void setPlayer_type(String player_type) {
        this.player_type = player_type;
    }

    /**
     * @return the client_connecte
     */
    public boolean isClient_connecte() {
        return client_connecte;
    }

    /**
     * @param client_connecte the client_connecte to set
     */
    public void setClient_connecte(boolean client_connecte) {
        this.client_connecte = client_connecte;
    }

    /**
     * @return the game_start
     */
    public boolean isGame_start() {
        return game_start;
    }

    /**
     * @param game_start the game_start to set
     */
    public void setGame_start(boolean game_start) {
        this.game_start = game_start;
    }

    /**
     * @return the game_over
     */
    public boolean isGame_over() {
        return game_over;
    }

    /**
     * @param game_over the game_over to set
     */
    public void setGame_over(boolean game_over) {
        this.game_over = game_over;
    }

    /**
     * @return the image_path
     */
    public String getImage_path() {
        return image_path;
    }

    /**
     * @param image_path the image_path to set
     */
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    /**
     * @return the image_path_adversaire
     */
    public String getImage_path_adversaire() {
        return image_path_adversaire;
    }

    /**
     * @param image_path_adversaire the image_path_adversaire to set
     */
    public void setImage_path_adversaire(String image_path_adversaire) {
        this.image_path_adversaire = image_path_adversaire;
    }

    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * @return the pseudo_adversaire
     */
    public String getPseudo_adversaire() {
        return pseudo_adversaire;
    }

    /**
     * @param pseudo_adversaire the pseudo_adversaire to set
     */
    public void setPseudo_adversaire(String pseudo_adversaire) {
        this.pseudo_adversaire = pseudo_adversaire;
    }

    /**
     * @return the grille_de_jeu
     */
    public String[][] getGrille_de_jeu() {
        return grille_de_jeu;
    }

    /**
     * @param aGrille_de_jeu the grille_de_jeu to set
     */
    public void setGrille_de_jeu(String[][] aGrille_de_jeu) {
        grille_de_jeu = aGrille_de_jeu;
    }

    /**
     * @return the label_info
     */
    public Label getLabel_info() {
        return label_info;
    }

    /**
     * @param label_info the label_info to set
     */
    public void setLabel_info(Label label_info) {
        this.label_info = label_info;
    }

    /**
     * @return the label_versus
     */
    public Label getLabel_versus() {
        return label_versus;
    }

    /**
     * @param label_versus the label_versus to set
     */
    public void setLabel_versus(Label label_versus) {
        this.label_versus = label_versus;
    }

    /**
     * @return the charger
     */
    public Boolean isCharger() {
        return charger;
    }

    /**
     * @param charger the charger to set
     */
    public void setCharger(Boolean charger) {
        this.charger = charger;
    }
    
    /**
     * @return the gp
     */
   /*
    public GridPane getGp() {
        return gp;
    } */

    /**
     * @param gp the gp to set
     */
    /*
    public void setGp(GridPane gp) {
        this.gp = gp;
    }*/
    
}
