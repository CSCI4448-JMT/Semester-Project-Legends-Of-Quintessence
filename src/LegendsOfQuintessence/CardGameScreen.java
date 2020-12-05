/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import LegendsOfQuintessence.card.ConcreteCard;
import LegendsOfQuintessence.gameComponents.AbstractGameComponent;
import LegendsOfQuintessence.gameComponents.Player1Board;
import LegendsOfQuintessence.gameComponents.Player1Field;
import LegendsOfQuintessence.gameComponents.PlayerMiddlePanel;
import LegendsOfQuintessence.gameComponents.Player2Board;
import LegendsOfQuintessence.gameComponents.Player2Field;
import LegendsOfQuintessence.gameComponents.PlayerBase;
import LegendsOfQuintessence.gameComponents.PlayerComponentBuilder;
import LegendsOfQuintessence.gameComponents.PlayerDeck;
import LegendsOfQuintessence.gameComponents.PlayerOuterPanel;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JMT
 */
public class CardGameScreen implements ScreenController{
    private Nifty nifty;
    private GameState gameState;
    private Element player1ParentElement;
    private final List<AbstractGameComponent> player2Components;
    
    public CardGameScreen(Nifty n, GameState gs) {
        nifty = n;
        gameState = gs;
        player2Components = new ArrayList();
        
        player2Components.add(new PlayerMiddlePanel(
                new Player2Field(), new PlayerBase()
        ));
//        player2Components.add(new PlayerOuterPanel(
//                new Player2Board(), new PlayerDeck("2")
//        ));
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
//        ElementBuilder eb = c.cardBuilder();
//        Element card_element = eb.build(nifty, screen, screen.findElementById("GLayer0") );    
        
//        c.setElement(card_element);
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
        
        Screen s =  new ScreenBuilder("game screen") {{
            controller(new DefaultScreenController());
            
            layer(new LayerBuilder("background") {{
                childLayoutVertical();
            }});
            
            layer(new LayerBuilder("game component layer") {{ 
                childLayoutVertical();
                
                panel(new PanelBuilder("player 1 main panel") {{
                    childLayoutVertical();
                    width("100%");
                    height("50%");
                    backgroundColor("#ff8080");
                }});
                
                panel(new PanelBuilder("player 2 panel") {{
                    childLayoutVertical();
                    width("100%");
                    height("50%");
                    backgroundColor("#4da6ff");
                    
                    for(AbstractGameComponent agc: player2Components) {
                        panel(agc.gameComponentBuilder());
                    }
                    
                }});
               
            }});
            
        }}.build(nifty);
        
        player1ParentElement = s.findElementById("player 1 main panel");
        
        PanelBuilder random = new PanelBuilder();
        
        random.build(player1ParentElement);
        
        PlayerComponentBuilder playerBuilder = new PlayerComponentBuilder(nifty, s, player1ParentElement);
        
        System.out.println(player1ParentElement);
        playerBuilder.buildPlayer1OuterPanel();
        
        for(AbstractGameComponent agc: player2Components) {
            agc.attachFilters(s, "player 2 card");
        }
        
//        for(AbstractGameComponent agc: player1Components) {
//            agc.attachFilters(s, "player 1 card");
//        }
        
        return s;
    }
    
}
