
package puissance4.controller;

import javafx.util.Duration;
import java.util.HashMap;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import puissance4.bean.INTERFACE_Screen;



/**
    * Class acting as a general controller for other controllers.
    * @author Kessler, Araba
    * @version 1.0
    */
public class CONTROLLER_Super extends StackPane {
    
    // Hashmap to manage the different screens. 
    private HashMap<String, Node> screens= new HashMap<>();
    
    // Accessors

    public void addScreen(String name, Node screen){
        // @param name : screen name
        // @param screen : screen node
        screens.put(name, screen);
    }
    
    public Node getScreen(String name){
        return screens.get(name);
    }
    
    
    /**
        * Load a screen.
        * @author Kessler, Araba
        * @version 1.0
        * @param final String name
        * 	The screen name we have to load.
        * @return boolean representing the loading success or failure.
        */
    public boolean setScreen(final String name) { 
        
        // If the screen is loaded.
        if(screens.get(name) != null) {  
            final DoubleProperty opacity = opacityProperty(); 
            // If there is more than one screen. 
            if(!getChildren().isEmpty()){ 
                Timeline fade = new Timeline( 
                                new KeyFrame(Duration.ZERO, 
                                new KeyValue(opacity,1.0)), 
                                new KeyFrame(new Duration(50), 
                                new EventHandler() { 
                                    @Override
                                    public void handle(Event event) {
                                        // Remove displayed screen.
                                        getChildren().remove(0); 
                                        // Add new screen.
                                        getChildren().add(0, screens.get(name)); 
                                        Timeline fadeIn = new Timeline( 
                                                          new KeyFrame(Duration.ZERO, 
                                                          new KeyValue(opacity, 0.0)), 
                                                          new KeyFrame(new Duration(50), 
                                                          new KeyValue(opacity, 1.0))); 
                                                          fadeIn.play(); 
                                    } 
                                }, new KeyValue(opacity, 0.0))); 
                fade.play();             
            } 
            else { 
                // No one else has been displayed so just show.
                setOpacity(0.0); 
                getChildren().add(screens.get(name)); 
                Timeline fadeIn = new Timeline( 
                                    new KeyFrame(Duration.ZERO, 
                                    new KeyValue(opacity, 0.0)), 
                                    new KeyFrame(new Duration(50), 
                                    new KeyValue(opacity, 1.0))); 
                fadeIn.play(); 
            } 
            return true; 
        } 
        else { 
            System.out.println("screen hasn't been loaded " + name + " " + screens.get(name) + "!\n"); 
            return false; 
        }
    }
    
    
    /**
        * Manage the redirection to a screen.
        * @author Kessler, Araba
        * @version 1.0
        * @param String name
        * 	The screen name we have to redirect to.
        * @param String resource
        * @return boolean representing redirection success or failure.
        */
    public boolean loadScreen(String name, String resource){
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource)); 
            Parent loadScreen = (Parent) myLoader.load();
            INTERFACE_Screen myScreenController = ((INTERFACE_Screen) myLoader.getController());
            myScreenController.setScreenParent(this);
            this.addScreen(name, loadScreen);
            return true;
        }
        catch(Exception e ){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
     
    /**
        * Check if a screen unload gone well.
        * @author Kessler, Araba
        * @version 1.0
        * @param String name
        * 	The screen name.
        * @return boolean representing unload success or failure.
        */
    public boolean unloadScreen(String name) { 
        if(screens.remove(name) == null) { 
            System.out.println("Screen didn't exist"); 
            return false; 
        } 
        else { 
            return true; 
        } 
    } 
}