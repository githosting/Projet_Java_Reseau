
package puissance4.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import puissance4.bean.EnumSauvegarde;

import puissance4.bean.INTERFACE_Screen;
import puissance4.bean.Message;
import puissance4.bean.Partie;
import puissance4.bean.RUN_Emission;
import puissance4.bean.RUN_Reception;
// import alert
import javafx.scene.control.Alert;

/**
    * Class acting as a controller for view VUE_Jeu events.
    * @version 4.0
    */
public class CONTROLLER_Jeu implements Initializable,INTERFACE_Screen {
    
   // initialize a new partie
   public static Partie game = new Partie();
   
    CONTROLLER_Super mycontroller;
    
    // The grid of game.
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
    // concerning the grid
    private GridPane gp;
    
    // Accessors
    public Label getLabelInfo() { 
        return label_info; 
    }
    
    public Label getLabelVersus() {
        return label_versus; 
    }
    
    public GridPane getGridPane() {
        return gp;
    }
    
    /**
     * return parent screen
     * @param screenParent 
     * @return void
     */
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
    public void cellClick(MouseEvent event) throws MalformedURLException, JsonProcessingException
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
                grille_de_jeu[rowIndex][colIndex] = game.getPlayer_color();
                // Check if there is a winner or a draw match.
                String victory_color = game.check_victory();
                if(victory_color.equals("match_nul"))
                {
                    label_info.setText("Match Nul !");
                    
                    // Save the game in history.
                   game.sauvegarde(EnumSauvegarde.Historisation, "Match Nul", grille_de_jeu, "");
                }
                else if(!(victory_color.equals("null")))
                {
                    label_info.setText("Le joueur " + victory_color + " ("+ game.getPseudo() +") gagne.");
                    // Save the game in history.
                     game.sauvegarde(EnumSauvegarde.Historisation, "Victoire", grille_de_jeu, "");
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
    
    /**
     * Manage a save game
     * @param event
     * @throws MalformedURLException
     * @throws JsonProcessingException 
     */
    @FXML
    private void btn_save(ActionEvent event) throws MalformedURLException, JsonProcessingException 
    {
        game.sauvegarde(EnumSauvegarde.Sauvegarde, "Match en cours", grille_de_jeu, "");
    }    
      /**
        * Manage the load of the game.
        * @version 2.0
        * @return void
        */
    private void partieChargement(){
        if(Partie.gameChargement.isCharger()){
              game = Partie.gameChargement;
              grille_de_jeu = game.getGrille_de_jeu();
              rafraichissementGrilleTotal(grille_de_jeu);
             
          } 
    }
    
      /**
        * Manage the refresh on a game grid when the game is loaded.
        * @version 2.0
        * @param String[][] grille
        * @return void
        */
    private void rafraichissementGrilleReception(String[][] grille){
              game.setGrille_de_jeu(grille);
              grille_de_jeu = grille;
              rafraichissementGrilleTotal(grille);
        
    }
    /**
        * Manage to refresh the grid.
        * @version 2.0
        * @param String[][] grille
        * @return void
        */
    private void rafraichissementGrilleTotal(String[][] grille){
        ObservableList<Node> childrens = gp.getChildren();
        for (int i = 0 ; i < 6 ; i++) 
        {
          for (int j = 0; j < 7; j++) 
          {
            for (Node node : childrens) {
                if(gp.getRowIndex(node) == i && gp.getColumnIndex(node) == j) {
                    String img = grille[i][j];
                    if(img != "null"){
                      node.setStyle("-fx-background-image: url(" + getClass().getResource("/resources/"+img + ".png").toExternalForm() + ");");
                      break;
                    }
                }
            }

           }
        }
    }
    /**
        * Manage to refresh a cell of the grid.
        * @version 2.0
        * @param String[][] grille
        * @return void
        */
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
     /**
        * Manage to check this victory when a message is received.
        * @version 2.0
        * @param String victory_color
        * @return void
        * @throws JsonProcessingException 
        */
    private void controleVictoireReception(String victory_color) throws JsonProcessingException{
                      if(victory_color.equals("match_nul"))
                            {
                                Platform.runLater(() -> {    
                                    label_info.setText("Match Nul !");
                                });
                               // Save the game in history.                              
                               game.sauvegarde(EnumSauvegarde.Historisation, "Match Nul", grille_de_jeu, "");                              
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
                                game.sauvegarde(EnumSauvegarde.Historisation, "Défaite", grille_de_jeu, pseudo_vainqueur);         
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
                           
                            game.setGame_start(true);
                            game.setClient_connecte(false);
                            if(game.isCharger()){
                                
                                game.setCharger(false);
                                Message le_message = new Message(-1, -1, game.getImage_path(), "null", game.getPseudo(),!game.isMy_turn(), grille_de_jeu);
                                RUN_Emission.message = le_message;
                            }
                            final String tour =  game.isMy_turn() ? "VOTRE TOUR" : "TOUR ADVERSE";
                            Platform.runLater(() -> {
                                label_info.setText(tour);
                            });
                            
                        }
                       // System.out.println("THREAD Refresh Grid...");
                        
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
                                try {
                                    controleVictoireReception(victory_color);
                                } catch (JsonProcessingException ex) {
                                    Logger.getLogger(CONTROLLER_Jeu.class.getName()).log(Level.SEVERE, null, ex);
                                }
                               
                            }else{
                                rafraichissementGrilleReception(grille_temp);
                                
                                game.setGame_over(false);
                                game.setMy_turn(RUN_Reception.message.my_turn);
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
    /** * Redirect to the history interface.
     * @version 1.0 
     * @param ActionEvent event
     * @return void */
    @FXML 
    private void goToRegle (ActionEvent event){ 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message d'information"); 
        alert.setContentText("Le but du jeu est d'aligner 4 pions sur une grille comptant 6 rangées et 7 colonnes. \n\n Chaque joueur dispose de 21 pions d'une couleur (par convention, en général jaune ou rouge). \nTour à tour les deux joueurs placent un pion dans la colonne de leur choix, le pion coulisse alors jusqu'à la position la plus basse possible dans la dite colonne à la suite de quoi c'est à l'adversaire de jouer. \n\n Le vainqueur est le joueur qui réalise le premier un alignement (horizontal, vertical ou diagonal) d'au moins quatre pions de sa couleur.\n\n Si, alors que toutes les cases de la grille de jeu sont remplies, aucun des deux joueurs n'a réalisé un tel alignement, la partie est déclarée nulle."); 
    alert.showAndWait(); 
    }
}
