//package
package puissance4;

import java.io.File;
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
import puissance4.ScreensController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

/**
 *
 * controlleur de la vue jeu.fxml
 */
public class GameScreen implements Initializable,ControlledScreen {

    ScreensController mycontroller;
    
     // La matrice représentant la grille de jeu et les pions dessus.
    public static String grille_de_jeu [][] =   
        {
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"}
        };
   
    public static String player_color = "";
    public static boolean my_turn = false;
    public static String player_type;
    public static boolean client_connecte = false;
    public static boolean game_start = false;
    public static boolean game_over = false;
    public static String image_path;
    public static String image_path_adversaire;
    public static String pseudo;
    
    @FXML
    public Label label_info;
    @FXML
    private GridPane gp;
    
    // FXML Getters 
    public Label getLabelInfo() { return label_info; }
    public GridPane getGridPane() { return gp; }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        mycontroller = screenParent;
    }
    @FXML 
    public void cellClick(MouseEvent event) throws MalformedURLException
    {
        Node source = (Node)event.getSource();
        
        if(source.getStyle().length() == 0 && my_turn == true && game_start == true && game_over == false)
        {
            int colIndex = GridPane.getColumnIndex(source);
            int rowIndex = GridPane.getRowIndex(source);
            
            // On check si la case d'en dessous est bien occupée pour voir si le joueur peut effectivement poser son pion ici.
            if  (
                    (rowIndex == 5 || !grille_de_jeu[rowIndex+1][colIndex].equals("null"))
                    && grille_de_jeu[rowIndex][colIndex].equals("null")
                )
            {
                // On actualise grille_de_jeu avec le coup du joueur.
                grille_de_jeu[rowIndex][colIndex] = player_color;

                // On vérifie s'il y a un vainqueur ou un match nul.
                String victory_color = check_victory();
                if(victory_color.equals("match_nul"))
                {
                    label_info.setText("Match Nul !");
                }
                else if(!(victory_color.equals("null")))
                {
                    label_info.setText("Le joueur " + victory_color + " ("+pseudo+") gagne.");
                    //game_over = true;
                }
                else
                {
                    Platform.runLater(() -> {
                       label_info.setText("TOUR ADVERSE");
                    });
                }

                // On update le message à envoyer.
                Message le_message = new Message(colIndex, rowIndex, image_path, victory_color, pseudo);
                RUN_Emission.message = le_message;
                
                // On affiche le pion.
                Platform.runLater(() -> {
                    source.setStyle("-fx-background-image: url(" + image_path + ");");
                });

                my_turn = false;
            }
        }
    }
     public static String check_victory()
 {
        String actual_color;
        
        // S'il y a match nul.
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
        
        // Pour chaque élément de grille_de_jeu, on explore toutes les possibilités d'enchaînement de 4 pions à partir de lui.
        for(int i=0; i<6; i++)
        { 
            for(int j = 0; j < 7; j++)
            { 
                actual_color = grille_de_jeu[i][j];
				
                // Si une combinaison de 4 pions existe à partir du pion en cours d'analyse.  
                if( 
                    (!(actual_color.equals("null")))
                    &&
                    (
                        // Par la droite.
                        (j <= 3 && grille_de_jeu[i][j+1].equals(actual_color) && grille_de_jeu[i][j+2].equals(actual_color) && grille_de_jeu[i][j+3].equals(actual_color))
                        || // Par la gauche.
                        (j >= 3 && grille_de_jeu[i][j-1].equals(actual_color) && grille_de_jeu[i][j-2].equals(actual_color) && grille_de_jeu[i][j-3].equals(actual_color))
                        || // Par le haut.
                        (i >= 3 && grille_de_jeu[i-1][j].equals(actual_color) && grille_de_jeu[i-2][j].equals(actual_color) && grille_de_jeu[i-3][j].equals(actual_color))
                        || // Par le bas.
                        (i <= 2 && grille_de_jeu[i+1][j].equals(actual_color) && grille_de_jeu[i+2][j].equals(actual_color) && grille_de_jeu[i+3][j].equals(actual_color))
                        || // En diagonale haut droit
                        (i >= 3 && j <= 3 && grille_de_jeu[i-1][j+1].equals(actual_color) && grille_de_jeu[i-2][j+2].equals(actual_color) && grille_de_jeu[i-3][j+3].equals(actual_color))
                        || // En diagonale haut gauche
                        (i >= 3 && j >= 3 && grille_de_jeu[i-1][j-1].equals(actual_color) && grille_de_jeu[i-2][j-2].equals(actual_color) && grille_de_jeu[i-3][j-3].equals(actual_color))
                        || // En diagonale bas droit
                        (i <= 2 && j <= 3 && grille_de_jeu[i+1][j+1].equals(actual_color) && grille_de_jeu[i+2][j+2].equals(actual_color) && grille_de_jeu[i+3][j+3].equals(actual_color))
                        || // En diagonale bas gauche
                        (i <= 2 && j >= 3 && grille_de_jeu[i+1][j-1].equals(actual_color) && grille_de_jeu[i+2][j-2].equals(actual_color) && grille_de_jeu[i+3][j-3].equals(actual_color))
                    )
                )
                {
                    // On retourne la couleur du joueur qui gagne.
                    return actual_color;
                }  
            } 
        }
        return "null";
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // LE CODE ICI S'EXECUTE AVANT LE SHOW DE L'INTERFACE GRAPHIQUE.
        label_info.setText("En attente d'un autre joueur");
        // Le Thread qui va actualiser la gridPane.
            new Thread( new Runnable() 
            {
                @Override
                public void run() 
                {
                    Message ancien_message = new Message();
                    while(!game_over)
                    {  
                        // Pour le serveur à l'initialisation seulement.
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
                        String pseudo_adversaire;

                        System.out.println(nouveau_message != ancien_message);
                        if(nouveau_message != null && (!(nouveau_message.rowIndex == ancien_message.rowIndex) || !(nouveau_message.colIndex == ancien_message.colIndex)))
                        {
                            
                            System.out.println("coucou ca passe");
                            ancien_message = nouveau_message;

                            colIndex            = RUN_Reception.message.colIndex;
                            rowIndex            = RUN_Reception.message.rowIndex;
                            path_image          = RUN_Reception.message.imagePath;
                            victory_color       = RUN_Reception.message.victoryColor;
                            pseudo_adversaire   = RUN_Reception.message.pseudo;

                            // On met à jour grille_de_jeu avec le coup de l'adversaire.
                            if(player_color.equals("orange"))
                                grille_de_jeu[rowIndex][colIndex] = "blue";
                            else
                                grille_de_jeu[rowIndex][colIndex] = "orange";

                            
                            // On change la couleur du label concerné.
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

                            // On check s'il y a un vainqueur ou si match nul.
                            if(victory_color.equals("match_nul"))
                            {
                                Platform.runLater(() -> {    
                                    label_info.setText("Match Nul !");
                                });

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

                                game_over = true; 
                            }
                            else if(game_over == false)
                            {
                                System.out.println("my turn = true");
                                my_turn = true;
                                Platform.runLater(() -> {
                                    label_info.setText("VOTRE TOUR");
                                });
                            }
                        }        

                        // On fait une pause
                        try { Thread.sleep(1000); }
                        catch (Exception e) {  }
                    
                    } 
                }
            }).start();
    }  
    
    public static void setGame(boolean rep){
        game_start = rep;
    }
    public static void setTurn(boolean rep) {
        my_turn = rep;
    }
    
    @FXML
    private void btn_reset(ActionEvent event) throws MalformedURLException 
    {
        try {Process process = Runtime.getRuntime().exec("java -jar ./dist/Puissance4.jar");} 
        catch (IOException ex) {Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);}
        Platform.exit();
        System.exit(0);
    }
    
    public static void setInformationPlayer (String type_player, String pseudo_name)throws MalformedURLException {
        if(type_player == "client"){
            System.out.println("cest un client");
            pseudo = pseudo_name;
            player_color = "blue";
            File image = new File("./pictures/blue.png");
            image_path = image.toURI().toURL().toString();
            image_path_adversaire = new File("./pictures/orange.png").toURI().toURL().toString();
            player_type = "client";
        }
        if (type_player == "server"){
            System.out.println("cest un serveur");
            System.out.println("player type"+ player_type);
            pseudo = pseudo_name;
            player_color = "orange";
            File image = new File("./pictures/orange.png");
            image_path = image.toURI().toURL().toString();
            image_path_adversaire = new File("./pictures/blue.png").toURI().toURL().toString();
            player_type = "server";
            System.out.println("changement de player type "+player_type );
        }
    }
}
