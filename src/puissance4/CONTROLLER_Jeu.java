
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
   public static Partie game = new Partie();
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
    
      
    
    /**
        * Manage the click on a game grid cell.
        * @version 2.0
        * @param MouseEvent event
        * @return void
        * @throws MalformedURLException
        */
    @FXML 
    public void cellClick(MouseEvent event) throws MalformedURLException
    {
      
        Node source = (Node)event.getSource();
        
       // if(source.getStyle().length() == 0 && my_turn == true && game_start == true && game_over == false)
      System.out.println(source.getStyle().length() + "   " + game.isMy_turn() + "  " + game.isGame_start() + "  " + game.isGame_over());
       if( game.isMy_turn() && game.isGame_start() && !game.isGame_over())
        {
            int colIndex = GridPane.getColumnIndex(source);
            int rowIndex = GridPane.getRowIndex(source);
            System.out.println(colIndex + "   " + rowIndex);
            // Check if the cell under the clicked one contains a piece in order to know if the player can perform this action.
            if  ((rowIndex == 5 || !grille_de_jeu[rowIndex+1][colIndex].equals("null")) && grille_de_jeu[rowIndex][colIndex].equals("null"))
            {
                // Refresh the game matrix with the player move.
               // grille_de_jeu[rowIndex][colIndex] = player_color;
                grille_de_jeu[rowIndex][colIndex] = game.getPlayer_color();
                // Check if there is a winner or a draw match.
                String victory_color = game.check_victory();
                if(victory_color.equals("match_nul"))
                {
                    label_info.setText("Match Nul !");
                    
                    // Save the game in history.
                   // historiser_partie(pseudo, pseudo_adversaire, "Match Nul", grille_de_jeu, "");
                   game.sauvegarde(EnumSauvegarde.Historisation, game.getPseudo(), game.getPseudo_adversaire(), "Match Nul", grille_de_jeu, "");
                }
                else if(!(victory_color.equals("null")))
                {
                    label_info.setText("Le joueur " + victory_color + " ("+ game.getPseudo() +") gagne.");
                    // Save the game in history.
                     game.sauvegarde(EnumSauvegarde.Historisation, game.getPseudo(), game.getPseudo_adversaire(), "Victoire", grille_de_jeu, "");
                }
                else
                {
                    Platform.runLater(() -> {
                       label_info.setText("TOUR ADVERSE");
                    });
                }

                // Update the message sent by the broadcast Thread.
                Message le_message = new Message(colIndex, rowIndex, game.getImage_path(), victory_color, game.getPseudo());
                RUN_Emission.message = le_message;
                
                // Display the player move on the grid.
                Platform.runLater(() -> {
                    source.setStyle("-fx-background-image: url(" + game.getImage_path() + ");");
                });

               
                game.setMy_turn(false);
            }
        }
    }
    
   
    /**
        * Manage a game reinitialisation.
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
        game.sauvegarde(EnumSauvegarde.Sauvegarde,game.getPseudo(), game.getPseudo_adversaire(), "Match en cours", grille_de_jeu, "");
    }    
    private void partieChargement(){
        if(Partie.gameChargement.isCharger()){
              game = Partie.gameChargement;
              grille_de_jeu = game.getGrille_de_jeu();
              ObservableList<Node> childrens = gp.getChildren();
              for (int i = 0 ; i < 6 ; i++) 
              {
                for (int j = 0; j < 7; j++) 
                {
                  for (Node node : childrens) {
                      if(gp.getRowIndex(node) == i && gp.getColumnIndex(node) == j) {
                          String img = grille_de_jeu[i][j];
                          if(img != "null"){
                            node.setStyle("-fx-background-image: url(" + getClass().getResource("/resources/"+img + ".png").toExternalForm() + ");");
                            break;
                          }
                      }
                  }

                 }
              }
          } 
    }
    
    private void rafraichissementGrilleReception(String[][] grille){
              ObservableList<Node> childrens = gp.getChildren();
              game.setGrille_de_jeu(grille);
              grille_de_jeu = grille;
              for (int i = 0 ; i < 6 ; i++) 
              {
                for (int j = 0; j < 7; j++) 
                {
                  for (Node node : childrens) {
                      if(gp.getRowIndex(node) == i && gp.getColumnIndex(node) == j) {
                          String img = grille[i][j];
                          node.setStyle("-fx-background-image: url(" + getClass().getResource("/resources/"+img + ".png").toExternalForm() + ");");
                          break;
                      }
                  }

                 }
              }
        
    }
    
    private void rafraichissementGrille(int rowIndex, int colIndex){
// Refresh the grid with the opponent move.
        if(game.getPlayer_color().equals("orange"))
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
                  ((Label)node).setStyle("-fx-background-image: url(" + game.getImage_path_adversaire() + ");");
                });
                break;
            }
        }
    }
    private void controleVictoire(String victory_color){
                      if(victory_color.equals("match_nul"))
                            {
                                Platform.runLater(() -> {    
                                    label_info.setText("Match Nul !");
                                });

                                // Save the game in history.
                              
                                game.sauvegarde(EnumSauvegarde.Historisation,game.getPseudo(), game.getPseudo_adversaire(), "Match Nul", grille_de_jeu, "");
                                
                               // game_over = true;
                               game.setGame_over(true);
                               
                            }
                            else if(!(victory_color.equals("null")))
                            {
                                String pseudo_vainqueur;
                              
                                if(victory_color.equals(game.getPlayer_color())){
                                    pseudo_vainqueur = game.getPseudo();
                                }else{
                                    pseudo_vainqueur = game.getPseudo_adversaire();                                  
                                }
                                Platform.runLater(() -> {    
                                    label_info.setText("Le joueur " + victory_color + " ("+pseudo_vainqueur+") gagne.");
                                });
                                
                                // Save the game in history
                                game.sauvegarde(EnumSauvegarde.Historisation,game.getPseudo(), game.getPseudo_adversaire(), "Défaite", grille_de_jeu, pseudo_vainqueur);         
                                //game_over = true; 
                                game.setGame_over(true);
                            }
                           // else if(game_over == false)
                           else if(!game.isGame_over())
                            {
                                game.setMy_turn(true);
                                Platform.runLater(() -> {
                                    label_info.setText("VOTRE TOUR");
                                });
                            }
    }
    /**
        * Execute code when the view is displayed.
        * Contains the Thread which refreshes the game grid.
        * @version 4.0
        * @param URL url
        * @param ResourceBundle rb
        * @return void
        */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          label_info.setText("En attente d'un autre joueur");
          partieChargement();
        // Thread refreshing the grid.
            new Thread( new Runnable() 
            {
                @Override
                public void run() 
                {
                    Message ancien_message = new Message();
                    while(!game.isGame_over())
                    {  
                        // For the server player and at initialisation only.
                        if(game.isClient_connecte())
                        {
                            Platform.runLater(() -> {
                                label_info.setText("VOTRE TOUR");
                            });
                            game.setGame_start(true);
                            game.setClient_connecte(false);
                            if(game.isCharger()){
                                
                                game.setCharger(false);
                                Message le_message = new Message(-1, -1, game.getImage_path(), "null", game.getPseudo(),grille_de_jeu);
                                RUN_Emission.message = le_message;
                            }
                            
                        }
                       // System.out.println("THREAD Refresh Grid... saucisse");
                        
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
                            game.setPseudo_adversaire(RUN_Reception.message.pseudo);
                            String [][] grille_temp = RUN_Reception.message.grille_de_jeu;
                            
                            // Display the versus information.
                            Platform.runLater(() -> {
                              label_versus.setText("> " + game.getPseudo() + " <  VS  > " + game.getPseudo_adversaire() + " <");
                            });

                            if(grille_temp == null){
                                rafraichissementGrille(rowIndex,colIndex);
                                controleVictoire(victory_color);
                            }else{
                                rafraichissementGrilleReception(grille_temp);
                                game.setGame_over(false);
                            }
                            // Check if there is a winner or a draw match.
                            
                             System.out.println(colIndex + "  " + rowIndex + "  " + path_image + "  " + victory_color + "  " + RUN_Reception.message.pseudo + "  " );
                        }        

                        // Make a brief pause.
                        try { Thread.sleep(100); }
                        catch (Exception e) {  }
                    
                    } 
                }
            }).start();
    }  
    
}
