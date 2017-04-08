//package
package puissance4;

//importations
import javafx.util.Duration;
import java.util.HashMap;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreensController extends StackPane {
    //creation hashmap pour manageer les différents écrans 
    private HashMap<String, Node> screens= new HashMap<>();
    
 // methodes
    
    /*
     ajout d'un ecran 
     @retrun void 
     @params String name nom de l'ecran
     @params Node screen noeud de l'ecran
    */
    public void addScreen(String name, Node screen){
        screens.put(name, screen);
    }
    
    // on obtient le nom de l'ecran
    public Node getScreen(String name){
        return screens.get(name);
    }
    
    /* on demande un chargement d'ecran 
     @return boolean
     @params String name nom de l'ecran sur lequel on veut rediriger
     @params String resource
    */
    public boolean loadScreen(String name, String resource){
        try{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource)); 
            
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            this.addScreen(name, loadScreen);
            return true;
        }catch(Exception e ){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
     public boolean setScreen(final String name) { 
        if(screens.get(name) != null) { //screen loaded 
            final DoubleProperty opacity = opacityProperty(); 
            //Is there is more than one screen 
            if(!getChildren().isEmpty()){ 
                Timeline fade = new Timeline( 
                                new KeyFrame(Duration.ZERO, 
                                new KeyValue(opacity,1.0)), 
                                new KeyFrame(new Duration(1000), 
                                new EventHandler() { 
                    @Override
                    public void handle(Event event) {
                  //remove displayed screen 
                                    getChildren().remove(0); 
                                    //add new screen 
                                    getChildren().add(0, screens.get(name)); 
                                    Timeline fadeIn = new Timeline( 
                                                      new KeyFrame(Duration.ZERO, 
                                                      new KeyValue(opacity, 0.0)), 
                                                      new KeyFrame(new Duration(800), 
                                                      new KeyValue(opacity, 1.0))); 
                                                      fadeIn.play(); 
                                } 
                             }, new KeyValue(opacity, 0.0))); 
                fade.play();             
            } else { 
                //no one else been displayed, then just show 
                setOpacity(0.0); 
                getChildren().add(screens.get(name)); 
                Timeline fadeIn = new Timeline( 
                    new KeyFrame(Duration.ZERO, 
                                 new KeyValue(opacity, 0.0)), 
                                 new KeyFrame(new Duration(2500), 
                                 new KeyValue(opacity, 1.0))); 
                fadeIn.play(); 
            } 
            return true; 
        } else { 
         System.out.println("screen hasn't been loaded!\n"); 
        return false; 
    }
  }
     
  /*
   Lors d'un changement d'ecran on veriife que ce dernier n'a pas rencontre de probleme
   @return boolean true si tout s'est bien passe
   @params nom de l'ecran 
  */
  public boolean unloadScreen(String name) { 
    if(screens.remove(name) == null) { 
        System.out.println("Screen didn't exist"); 
        return false; 
    } else { 
        return true; 
    } 
  } 
}
