// Contrôleur de la vue VUE_Historique

package puissance4;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
import puissance4.CONTROLLER_Super;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import static puissance4.RUN_Reception.message;


public class CONTROLLER_Historique implements Initializable,INTERFACE_Screen {

    CONTROLLER_Super mycontroller;
    
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
   
    
    @FXML
    public Label label_info;
    @FXML
    public Label label_pseudo_moi;
    @FXML
    public Label label_pseudo_adversaire;
    @FXML
    public Label label_victoire_defaite;
    @FXML
    public ListView listview;
    @FXML
    private GridPane gp;
    
    // FXML Getters 
    public Label getLabelInfo() { return label_info; }
    public GridPane getGridPane() { return gp; }
    
    @Override
    public void setScreenParent(CONTROLLER_Super screenParent) {
        mycontroller = screenParent;
    }
    
    
    @FXML 
    public void cellClick(MouseEvent event) throws MalformedURLException
    {
        //label_info.setText("CLICK");
    }
    
    
    // goToHome
    // @return void 
    // @params event lorsque l'on appuie sur un bouton ici
    // on retourne vers le screen de lancement 
    @FXML
    private void goToHome (ActionEvent event){
        mycontroller.setScreen(Puissance4.MAIN_SCREEN);
    }
    
    
    @FXML 
    public void handleMouseClick(MouseEvent arg0) 
    {
        String file_selected = listview.getSelectionModel().getSelectedItem().toString();
        
        // On ouvre le fichier sélectionné dans la listview et on récupère la chaîne JSON.
        Path path = Paths.get("./saves/" + file_selected);
        try 
        {
            // On récupère le contenu du fichier.    
                List<String> les_lignes = Files.readAllLines(path, StandardCharsets.UTF_8);
                String JSON_string = les_lignes.get(0);
            
            // Désérialisation de la JSON string en un objet Partie_History.
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Partie_History partie = new Partie_History();
                partie = objectMapper.readValue(JSON_string, Partie_History.class);
            
            // Affichage des informations dans la vue.
                label_pseudo_moi.setText("Moi : " + partie.pseudo_moi);
                label_pseudo_adversaire.setText("Adversaire : " + partie.pseudo_adversaire);
                label_victoire_defaite.setText("Résultat : " + partie.victoire_defaite);

                ObservableList<Node> childrens = gp.getChildren();
                for (int i = 0 ; i < 6 ; i++) 
                {
                    for (int j = 0; j < 7; j++) 
                    {
                        File image = new File("./pictures/"+partie.grille_de_jeu[i][j]+".png");
                        String image_path = image.toURI().toURL().toString();
                        for (Node node : childrens) {
                            if(gp.getRowIndex(node) == i && gp.getColumnIndex(node) == j) {
                                node.setStyle("-fx-background-image: url(" + image_path + ");");
                                break;
                            }
                        }
                    }
                }
        } catch (IOException ex) {Logger.getLogger(CONTROLLER_Historique.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // LE CODE ICI S'EXECUTE AVANT LE SHOW DE L'INTERFACE GRAPHIQUE.

        // On charge les saves dans la listview.
            ObservableList data = FXCollections.observableArrayList();

            File dossier = new File("./saves");  
            File[] les_files = dossier.listFiles();  
            for (File f : les_files)  
            {  
                //data.add(f.getAbsolutePath());
                data.add(f.getName());
            } 

            listview.setItems(data);
    }  
}
