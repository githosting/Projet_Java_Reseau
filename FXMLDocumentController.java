package puissance4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class FXMLDocumentController implements Initializable 
{
    public static Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner sc;
    private String message;
    private Scanner scanner;
    public static ServerSocket server_socket;
    public static Thread thread_attente_connexion;
    public static Thread thread_emission;
    public static Thread thread_reception;
    public static RUN_Connexion runnable_connexion;
    public static RUN_Emission runnable_emission;
    public static RUN_Reception runnable_reception;
    public String player_color = "";
    public boolean my_turn = false;
    public static String player_type;
    public static boolean client_connecte = false;
    public boolean game_start = false;
    public static boolean game_over = false;
    public String image_path;
    
    // La matrice représentant la grille de jeu et les pions dessus.
    public String grille_de_jeu [][] =   
        {
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"}, 
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"},
            {"null", "null", "null", "null", "null", "null", "null"}
        };
     
    @FXML
    private TextField textfield_pseudo;
    @FXML
    private TextField textfield_adresse; 
    @FXML
    private TextField textfield_port;
    @FXML
    private Button button_server; 
    @FXML
    private Button button_connect;
    @FXML
    public Label label_info;
    @FXML
    private GridPane gp;
    
    // FXML Getters
    public Label getLabelInfo() { return label_info; }
    public GridPane getGridPane() { return gp; }
    
    
    @FXML
    private void btn_connect(ActionEvent event) throws MalformedURLException 
    {
        button_connect.setVisible(false);
        button_server.setVisible(false);
        textfield_adresse.setVisible(false);
        textfield_port.setVisible(false);
        
        player_color = "blue";
        File image = new File("pictures/blue.png");
        image_path = image.toURI().toURL().toString();
        
        player_type = "client";
        
        try 
        {
            label_info.setText("Demande de connexion...");
            
            String adresse_serveur = textfield_adresse.getText();
            int port_serveur = Integer.parseInt(textfield_port.getText());
            
            socket = new Socket(adresse_serveur, port_serveur);

            // Si ce message s'affiche c'est que je suis connecté
            label_info.setText("Connecté au serveur.");
            label_info.setText("TOUR ADVERSE");
            game_start = true;

            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	

            thread_emission = new Thread(new RUN_Emission(out));
            thread_emission.start();
            thread_reception = new Thread(new RUN_Reception(in));
            thread_reception.start();
        } 
        catch (UnknownHostException e) { System.err.println("BUG : Connexion impossible à l'adresse "+socket.getLocalAddress()); } 
        catch (IOException e) { System.err.println("BUG : Pas de serveur à l'écoute du port "+socket.getLocalPort()); }
    }
    
    @FXML
    private void btn_server(ActionEvent event) throws MalformedURLException 
    {
        button_connect.setVisible(false);
        button_server.setVisible(false);
        textfield_adresse.setVisible(false);
        textfield_port.setVisible(false);
        
        player_color = "orange";
        File image = new File("pictures/orange.png");
        image_path = image.toURI().toURL().toString();
        
        player_type = "server";
        
        my_turn = true;
        
        try 
        {
            int port_serveur = Integer.parseInt(textfield_port.getText());
            server_socket = new ServerSocket(port_serveur);
            label_info.setText("Port "+server_socket.getLocalPort()+" écouté...");

            thread_attente_connexion = new Thread(new RUN_Connexion(server_socket));
            thread_attente_connexion.start();     
        } 
        catch (IOException e) { System.err.println("BUG : Port "+server_socket.getLocalPort()+" déjà utilisé."); }
    }
    
    @FXML 
    public void label_infoClick(MouseEvent event)
    {
        System.out.println("CLICK"); 
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

                // On vérifie s'il y a un vainqueur.
                // S'il y a un vainqueur, la fonction check_victory se charge de l'afficher au joueur et bloque le jeu.
                String victory_color = check_victory();
                if(!(victory_color.equals("null")))
                {
                    label_info.setText("Le joueur " + victory_color + " gagne.");
                }
                else
                {
                    Platform.runLater(() -> {
                        label_info.setText("TOUR ADVERSE");
                    });
                }

                // On update le message à envoyer.
                RUN_Emission.message = colIndex + ";" + rowIndex + ";" + image_path + ";" + victory_color;

                System.out.println("OK OK OK OK OK OK");
                
                // On affiche le pion.
                Platform.runLater(() -> {
                    source.setStyle("-fx-background-image: url(" + image_path + ");");
                });

                my_turn = false;
            }
        }
    }
    
    @FXML
    public void fillGridWithLabels() 
    {
        for (int i = 0 ; i < 30 ; i++) 
        {
            for (int j = 0; j < 30; j++) 
            {
                Label label = new Label("-");
                gp.add(label, i, j);
            }
        }
    }
    
    
    public String check_victory()
    {
        String actual_color;
        
        // Pour chaque élément de grille_de_jeu, on explore toutes les possibilités d'enchaînement de 4 pions à partir de lui.
        for(int i=0; i<6; i++)
        { 
            for(int j = 0; j < 7; j++)
            { 
                actual_color = grille_de_jeu[i][j];
				
                // Si une combinaison de 4 pions existe à partir du pion en cours d'analyse.  
                if( 
                    (actual_color != "null")
                    &&
                    (
                        // Par la droite.
                        (j <= 3 && grille_de_jeu[i][j+1] == actual_color && grille_de_jeu[i][j+2] == actual_color && grille_de_jeu[i][j+3] == actual_color)
                        || // Par la gauche.
                        (j >= 3 && grille_de_jeu[i][j-1] == actual_color && grille_de_jeu[i][j-2] == actual_color && grille_de_jeu[i][j-3] == actual_color)
                        || // Par le haut.
                        (i >= 3 && grille_de_jeu[i-1][j] == actual_color && grille_de_jeu[i-2][j] == actual_color && grille_de_jeu[i-3][j] == actual_color)
                        || // Par le bas.
                        (i <= 2 && grille_de_jeu[i+1][j] == actual_color && grille_de_jeu[i+2][j] == actual_color && grille_de_jeu[i+3][j] == actual_color)
                        || // En diagonale haut droit
                        (i >= 3 && j <= 3 && grille_de_jeu[i-1][j+1] == actual_color && grille_de_jeu[i-2][j+2] == actual_color && grille_de_jeu[i-3][j+3] == actual_color)
                        || // En diagonale haut gauche
                        (i >= 3 && j >= 3 && grille_de_jeu[i-1][j-1] == actual_color && grille_de_jeu[i-2][j-2] == actual_color && grille_de_jeu[i-3][j-3] == actual_color)
                        || // En diagonale bas droit
                        (i <= 2 && j <= 3 && grille_de_jeu[i+1][j+1] == actual_color && grille_de_jeu[i+2][j+2] == actual_color && grille_de_jeu[i+3][j+3] == actual_color)
                        || // En diagonale bas gauche
                        (i <= 2 && j >= 3 && grille_de_jeu[i+1][j-1] == actual_color && grille_de_jeu[i+2][j-2] == actual_color && grille_de_jeu[i+3][j-3] == actual_color)
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
        
        // Le Thread qui va actualiser la gridPane.
            new Thread( new Runnable() 
            {
                @Override
                public void run() 
                {
                    String ancien_message = "";
                    
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
                        String nouveau_message = RUN_Reception.message;
                            
                        int colIndex;
                        int rowIndex;
                        String path_image;
                        String victory_color;

                        if(nouveau_message != null && nouveau_message.length() > 0 && !(nouveau_message.equals(ancien_message)))
                        {
                            ancien_message = nouveau_message;

                            colIndex = Integer.parseInt((RUN_Reception.message.split(";"))[0]);
                            rowIndex = Integer.parseInt((RUN_Reception.message.split(";"))[1]);
                            path_image = (RUN_Reception.message.split(";"))[2];
                            victory_color = (RUN_Reception.message.split(";"))[3];

                            // On met à jour grille_de_jeu avec le coup de l'adversaire.
                            grille_de_jeu[rowIndex][colIndex] = player_color;

                            // On change la couleur du label concerné.
                            ObservableList<Node> childrens = gp.getChildren();
                            for (Node node : childrens) 
                            {
                                if (GridPane.getColumnIndex(node) == colIndex
                                    && GridPane.getRowIndex(node) == rowIndex) 
                                {
                                    Platform.runLater(() -> {
                                        ((Label)node).setStyle("-fx-background-image: url(" + path_image + ");");
                                    });
                                    break;
                                }
                            }

                            // On check s'il y a un vainqueur.
                            if(!(victory_color.equals("null")))
                            {
                                Platform.runLater(() -> {    
                                    label_info.setText("Le joueur " + victory_color + " gagne.");
                                });

                                //thread_attente_connexion.interrupt();
                                //thread_emission.interrupt();
                                //thread_reception.interrupt();
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

                        // On fait une pause
                        try { Thread.sleep(1000); }
                        catch (Exception e) {  }
                    
                    } 
                }
            }).start();
 
    }    
}