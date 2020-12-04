/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import LegendsOfQuintessence.card.ConcreteCard;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.dragndrop.builder.DraggableBuilder;
import de.lessvoid.nifty.controls.dynamic.PanelCreator;
import de.lessvoid.nifty.controls.dynamic.TextCreator;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.screen.DefaultScreenController;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;
import java.util.List;

/**
 *
 * @author JMT
 */
public class CardGameScreen implements ScreenController{
    private Nifty nifty;
    private GameState gameState;
    
    public CardGameScreen(Nifty n, GameState gs) {
        nifty = n;
        gameState = gs;
    }
    
//Binds this ScreenController to a screen,
    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        System.out.println("bind( " + screen.getScreenId() + ")");
    }

    @Override
    public void onStartScreen() {
        System.out.println("Entering game screen");
        
        Screen screen = nifty.getCurrentScreen();
        ConcreteCard c = new ConcreteCard("p1-card");
        ElementBuilder eb = c.cardBuilder();
        Element card_element = eb.build(nifty, screen, screen.findElementById("GLayer0") );    
        
        c.setElement(card_element);
        c.setDefensePower(7);
    }

    @Override
    public void onEndScreen() {
        System.out.println("Leaving game screen");
        //initScene();
    }

    public void quit(){
        nifty.gotoScreen("end");
    }
    
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }
    
    /* 
    create the game screen with the following layers:
    - background
    - game component layer
    */
    public Screen gameScreenBuilder() {
        
        return new ScreenBuilder("game screen") {{
            controller(new DefaultScreenController());
            
            layer(new LayerBuilder("background") {{
                childLayoutVertical();
            }});
            
            layer(new LayerBuilder("game component layer") {{ 
                childLayoutVertical();
                
                panel(new PanelBuilder("player 1 panel") {{
                    childLayoutVertical();
                    width("100%");
                    height("50%");
                    backgroundColor("#ff8080");
                }});
 /*               
                panel(new PanelBuilder("divider") {{
                    childLayoutVertical();
                    width("100%");
                    height("2%");
                    backgroundColor("#0a0a0a");
                }});
 */               
                panel(new PanelBuilder("player 2 panel") {{
                    childLayoutVertical();
                    width("100%");
                    height("50%");
                    backgroundColor("#4da6ff");
                }});
               
            }});
            
        }}.build(nifty);
    }
    
}
