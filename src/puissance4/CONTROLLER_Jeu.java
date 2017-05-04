
package puissance4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;


/**
    * Class acting as a controller for view VUE_Jeu events.
    * @author Araba, Kessler, Bettinger
    * @version 4.0
    */
public class CONTROLLER_Jeu implements Initializable,INTERFACE_Screen {

    // Attributes
    public static String player_color = "";
    public static boolean my_turn = false;
    public static String player_type;
    public static boolean client_connecte = false;
    public static boolean game_start = false;
    public static boolean game_over = false;
    public static String image_path;
    public static String image_path_adversaire;
    public static String pseudo;
    public static String pseudo_adversaire;
    CONTROLLER_Super mycontroller;
    // The game matrix.
    public static String grille_de_jeu [][] =   
        {
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"}
        };
    
    @FXML
    public Label label_info;
    @FXML
    public Label label_versus;
    @FXML
    private GridPane gp;
    
    // Accessors
    public Label getLabelInfo() { return label_info; }
    public Label getLabelVersus() { return label_versus; }
    public GridPane getGridPane() { return gp; }
    
    @Override
    public void setScreenParent(CONTROLLER_Super screenParent) {
        mycontroller = screenParent;
    }
    
    public static void setGame(boolean rep){
        game_start = rep;
    }
    public static void setTurn(boolean rep) {
        my_turn = rep;
    }
    
    
    /**
        * Initialise a player variables considering his type.
        * @author Araba, Kessler
        * @version 1.0
        * @param String type_player
        * 	The player type : client or server.
        * @param String pseudo_name
        * 	The player pseudo.
        * @return void
        * @throws MalformedURLException
        */
    public static void setInformationPlayer (String type_player, String pseudo_name)throws MalformedURLException {
        if(type_player == "client"){
            pseudo = pseudo_name;
            player_color = "blue";
            image_path_adversaire = new Object(){}.getClass().getResource("/resources/orange.png").toExternalForm();
            image_path =  new Object(){}.getClass().getResource("/resources/blue.png").toExternalForm();
            player_type = "client";
        }
        if (type_player == "server"){
            pseudo = pseudo_name;
            player_color = "orange";
           
            image_path = new Object(){}.getClass().getResource("/resources/orange.png").toExternalForm();
            image_path_adversaire =  new Object(){}.getClass().getResource("/resources/blue.png").toExternalForm();
            player_type = "server";
        }
    }
    
    
    /**
        * Manage the click on a game grid cell.
        * @author Bettinger
        * @version 2.0
        * @param MouseEvent event
        * @return void
        * @throws MalformedURLException
        */
    @FXML 
    public void cellClick(MouseEvent event) throws MalformedURLException
    {
        Node source = (Node)event.getSource();
        
        if(source.getStyle().length() == 0 && my_turn == true && game_start == true && game_over == false)
        {
            int colIndex = GridPane.getColumnIndex(source);
            int rowIndex = GridPane.getRowIndex(source);
            
            // Check if the cell under the clicked one contains a piece in order to know if the player can perform this action.
            if  (
                    (rowIndex == 5 || !grille_de_jeu[rowIndex+1][colIndex].equals("null"))
                    && grille_de_jeu[rowIndex][colIndex].equals("null")
                )
            {
                // Refresh the game matrix with the player move.
                grille_de_jeu[rowIndex][colIndex] = player_color;

                // Check if there is a winner or a draw match.
                String victory_color = check_victory();
                if(victory_color.equals("match_nul"))
                {
                    label_info.setText("Match Nul !");
                    
                    // Save the game in history.
                    historiser_partie(pseudo, pseudo_adversaire, "Match Nul", grille_de_jeu, "");
                }
                else if(!(victory_color.equals("null")))
                {
                    label_info.setText("Le joueur " + victory_color + " ("+pseudo+") gagne.");
                    
                    // Save the game in history.
                    historiser_partie(pseudo, pseudo_adversaire, "Victoire", grille_de_jeu, "");
                }
                else
                {
                    Platform.runLater(() -> {
                       label_info.setText("TOUR ADVERSE");
                    });
                }

                // Update the message sent by the broadcast Thread.
                Message le_message = new Message(colIndex, rowIndex, image_path, victory_color, pseudo);
                RUN_Emission.message = le_message;
                
                // Display the player move on the grid.
                Platform.runLater(() -> {
                    source.setStyle("-fx-background-image: url(" + image_path + ");");
                });

                my_turn = false;
            }
        }
    }
    
    
    /**
        * Check victory conditions after a player move.
        * @author Bettinger
        * @version 3.0
        * @param No Parameters
        * @return String representing game state or winner color after a player move.
        */
    public static String check_victory()
    {
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
                actual_color = grille_de_jeu[i][j];
				
                // If 4 consecutives pieces are found from the current grid element.  
                if( 
                    (!(actual_color.equals("null")))
                    &&
                    (
                        // To the right.
                        (j <= 3 && grille_de_jeu[i][j+1].equals(actual_color) && grille_de_jeu[i][j+2].equals(actual_color) && grille_de_jeu[i][j+3].equals(actual_color))
                        || // To the left.
                        (j >= 3 && grille_de_jeu[i][j-1].equals(actual_color) && grille_de_jeu[i][j-2].equals(actual_color) && grille_de_jeu[i][j-3].equals(actual_color))
                        || // To top.
                        (i >= 3 && grille_de_jeu[i-1][j].equals(actual_color) && grille_de_jeu[i-2][j].equals(actual_color) && grille_de_jeu[i-3][j].equals(actual_color))
                        || // To bottom.
                        (i <= 2 && grille_de_jeu[i+1][j].equals(actual_color) && grille_de_jeu[i+2][j].equals(actual_color) && grille_de_jeu[i+3][j].equals(actual_color))
                        || // To diagonal top right.
                        (i >= 3 && j <= 3 && grille_de_jeu[i-1][j+1].equals(actual_color) && grille_de_jeu[i-2][j+2].equals(actual_color) && grille_de_jeu[i-3][j+3].equals(actual_color))
                        || // To diagonal top left.
                        (i >= 3 && j >= 3 && grille_de_jeu[i-1][j-1].equals(actual_color) && grille_de_jeu[i-2][j-2].equals(actual_color) && grille_de_jeu[i-3][j-3].equals(actual_color))
                        || // To diagonal bottom right.
                        (i <= 2 && j <= 3 && grille_de_jeu[i+1][j+1].equals(actual_color) && grille_de_jeu[i+2][j+2].equals(actual_color) && grille_de_jeu[i+3][j+3].equals(actual_color))
                        || // To diagonal bottom left.
                        (i <= 2 && j >= 3 && grille_de_jeu[i+1][j-1].equals(actual_color) && grille_de_jeu[i+2][j-2].equals(actual_color) && grille_de_jeu[i+3][j-3].equals(actual_color))
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
        * wrapper for evolution of the system of save
        * @author Milev
        * @version 1.0
        *  * @param EnumSauvegarde type
        * 	The pseudo of the player saving the game.
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
    public void sauvegarde(EnumSauvegarde type, String param_pseudo_moi, String param_pseudo_adversaire, String param_victoire_defaite, String[][] param_grille_de_jeu, String param_pseudo_vainqueur){
        if(type == EnumSauvegarde.Historisation){
            historiser_partie(param_pseudo_moi, param_pseudo_adversaire, param_victoire_defaite, param_grille_de_jeu, param_pseudo_vainqueur);
        }else{
            sauvegarde_partie(param_pseudo_moi, param_pseudo_adversaire, param_victoire_defaite, param_grille_de_jeu, param_pseudo_vainqueur);
        }
    }
    public void sauvegarde_partie(String param_pseudo_moi, String param_pseudo_adversaire, String param_victoire_defaite, String[][] param_grille_de_jeu, String param_pseudo_vainqueur)
    {
        if(param_victoire_defaite.equals("Défaite"))
        {
            if(param_pseudo_vainqueur.equals(pseudo))
                param_victoire_defaite = "Victoire";
        }
        
        // Create the Partie_History object we want to save.
        Partie_Save save = new Partie_Save(param_pseudo_moi, param_pseudo_adversaire, param_victoire_defaite, param_grille_de_jeu, "4200");
        
        // Save the object, serialised with JSON, in a file on the player computer.
        String save_JSON = "";
        ObjectMapper mapper = new ObjectMapper();
        try 
        {
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
        catch (JsonProcessingException ex) { Logger.getLogger(CONTROLLER_Jeu.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
     /**
        * Manage the automatic game save into history when it ends.
        * @author Bettinger
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
    public void historiser_partie(String param_pseudo_moi, String param_pseudo_adversaire, String param_victoire_defaite, String[][] param_grille_de_jeu, String param_pseudo_vainqueur)
    {
        if(param_victoire_defaite.equals("Défaite"))
        {
            if(param_pseudo_vainqueur.equals(pseudo))
                param_victoire_defaite = "Victoire";
        }
        
        // Create the Partie_History object we want to save.
        Partie_History save = new Partie_History(param_pseudo_moi, param_pseudo_adversaire, param_victoire_defaite, param_grille_de_jeu);
        
        // Save the object, serialised with JSON, in a file on the player computer.
        String save_JSON = "";
        ObjectMapper mapper = new ObjectMapper();
        try 
        {
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
        catch (JsonProcessingException ex) { Logger.getLogger(CONTROLLER_Jeu.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    
    /**
        * Manage a game reinitialisation.
        * @author Bettinger
        * @version 1.0
        * @param ActionEvent event
        * @return void
        * @throws MalformedURLException
        */
    @FXML
    private void btn_reset(ActionEvent event) throws MalformedURLException 
    {
        try {Process process = Runtime.getRuntime().exec("java -jar ./dist/Puissance4.jar");} 
        catch (IOException ex) {Logger.getLogger(CONTROLLER_Jeu.class.getName()).log(Level.SEVERE, null, ex);}
        Platform.exit();
        System.exit(0);
    }
    
     @FXML
     private void btn_save(ActionEvent event) throws MalformedURLException 
    {
        sauvegarde(EnumSauvegarde.Sauvegarde,pseudo, pseudo_adversaire, "Match en cours", grille_de_jeu, "");
    }    
    
    /**
        * Execute code when the view is displayed.
        * Contains the Thread which refreshes the game grid.
        * @author Bettinger
        * @version 4.0
        * @param URL url
        * @param ResourceBundle rb
        * @return void
        */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Code here executes before the interface is shown.
        if(CONTROLLER_Chargement.charger != null && CONTROLLER_Chargement.charger){
            try{
               ObservableList<Node> childrens = gp.getChildren();
               for (int i = 0 ; i < 6 ; i++) 
               {
                   for (int j = 0; j < 7; j++) 
                   {
                       File image = new File("./pictures/"+CONTROLLER_Chargement.grille_de_jeu[i][j]+".png");
                       String image_path = image.toURI().toURL().toString();
                       for (Node node : childrens) {
                           if(gp.getRowIndex(node) == i && gp.getColumnIndex(node) == j) {
                               node.setStyle("-fx-background-image: url(" + image_path + ");");
                               break;
                           }
                       }
                   }
               }
            }catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        }
        label_info.setText("En attente d'un autre joueur");
        System.out.println("ça marche");
        // Thread refreshing the grid.
            new Thread( new Runnable() 
            {
                @Override
                public void run() 
                {
                    Message ancien_message = new Message();
                    while(!game_over)
                    {  
                        // For the server player and at initialisation only.
                        if(client_connecte == true)
                        {
                            Platform.runLater(() -> {
                                label_info.setText("VOTRE TOUR");
                            });
                            game_start = true;
                            client_connecte = false;
                            
                        }
                        System.out.println("THREAD Refresh Grid...");
                        
                        Message nouveau_message = RUN_Reception.message;
                            
                        int colIndex;
                        int rowIndex;
                        String path_image;
                        String victory_color;

                        if(nouveau_message != null && (!(nouveau_message.rowIndex == ancien_message.rowIndex) || !(nouveau_message.colIndex == ancien_message.colIndex)))
                        {
                            ancien_message      = nouveau_message;
                            colIndex            = RUN_Reception.message.colIndex;
                            rowIndex            = RUN_Reception.message.rowIndex;
                            path_image          = RUN_Reception.message.imagePath;
                            victory_color       = RUN_Reception.message.victoryColor;
                            pseudo_adversaire   = RUN_Reception.message.pseudo;
                            
                            // Display the versus information.
                            Platform.runLater(() -> {
                                label_versus.setText("> " + pseudo + " <  VS  > " + pseudo_adversaire + " <");
                            });

                            // Refresh the grid with the opponent move.
                            if(player_color.equals("orange"))
                                grille_de_jeu[rowIndex][colIndex] = "blue";
                            else
                                grille_de_jeu[rowIndex][colIndex] = "orange";

                            // Change the picture of associated grid cell.
                            ObservableList<Node> childrens = gp.getChildren();
                            for (Node node : childrens) 
                            {
                                if (GridPane.getColumnIndex(node) == colIndex
                                    && GridPane.getRowIndex(node) == rowIndex) 
                                {
                                    Platform.runLater(() -> {
                                        ((Label)node).setStyle("-fx-background-image: url(" + image_path_adversaire + ");");
                                    });
                                    break;
                                }
                            }

                            // Check if there is a winner or a draw match.
                            if(victory_color.equals("match_nul"))
                            {
                                Platform.runLater(() -> {    
                                    label_info.setText("Match Nul !");
                                });

                                // Save the game in history.
                                sauvegarde(EnumSauvegarde.Historisation,pseudo, pseudo_adversaire, "Match Nul", grille_de_jeu, "");
                                
                                game_over = true;
                            }
                            else if(!(victory_color.equals("null")))
                            {
                                String pseudo_vainqueur;
                                if(victory_color.equals(player_color))
                                    pseudo_vainqueur = pseudo;
                                else
                                    pseudo_vainqueur = pseudo_adversaire;
                                
                                Platform.runLater(() -> {    
                                    label_info.setText("Le joueur " + victory_color + " ("+pseudo_vainqueur+") gagne.");
                                });
                                
                                // Save the game in history.
                                sauvegarde(EnumSauvegarde.Historisation,pseudo, pseudo_adversaire, "Défaite", grille_de_jeu, pseudo_vainqueur);
                                          
                                game_over = true; 
                            }
                            else if(game_over == false)
                            {
                                my_turn = true;
                                Platform.runLater(() -> {
                                    label_info.setText("VOTRE TOUR");
                                });
                            }
                        }        

                        // Make a brief pause.
                        try { Thread.sleep(100); }
                        catch (Exception e) {  }
                    
                    } 
                }
            }).start();
    }  
    
}
