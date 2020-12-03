 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import LegendsOfQuintessence.card.CardDropFilter;
import LegendsOfQuintessence.card.CardSlot;
import LegendsOfQuintessence.card.AbstractCard;
import LegendsOfQuintessence.card.ConcreteCard;
import LegendsOfQuintessence.gameComponents.Player2Board;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.DroppableDropFilter;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.dragndrop.DraggableControl;
import de.lessvoid.nifty.controls.dragndrop.DroppableControl;
import de.lessvoid.nifty.controls.dynamic.PanelCreator;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.screen.DefaultScreenController;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author Jenn1
 */
public class StartScreen implements ScreenController{
    private Nifty nifty;
    private GameState gameState;
    
    //Binds this ScreenController to a screen,
    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        System.out.println("bind( " + screen.getScreenId() + ")");
    }

    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
        //initScene();
    }

    public void quit(){
        nifty.gotoScreen("end");
    }
    
    public void setGameState(GameState gameState){
        this.gameState = gameState;
        
        
    }
    
    @NiftyEventSubscriber(id="StartButton")
    public void onClick(String id, NiftyMousePrimaryClickedEvent event) {
//        nifty.fromXml("Interface/card.xml", "GScreen0", this);
//        nifty.gotoScreen("GScreen0"); 
        final AbstractCard defenseCard1 = new ConcreteCard("p1-card");
        final AbstractCard defenseCard2 = new ConcreteCard("p2-card");
        final Player2Board p2Board = new Player2Board();
        final CardGameScreen cgs = new CardGameScreen(nifty, gameState);
        
 
       Screen s = new ScreenBuilder("Hello Nifty Screen"){{
            controller(new DefaultScreenController());
            
            layer(new LayerBuilder("boardLayer") {{ 
                childLayoutAbsolute();
                
                // <panel>
                panel(new PanelBuilder("player1-board") {{
                    childLayoutHorizontal(); // panel properties, add more...
                    height("175px");
                    x("200px");
                    width("700px");
                    backgroundColor("#2eb82e");
                    
                    
                    for(int i = 0; i < 5; i++){
                        control(defenseCard1.cardBuilder());
                    }
//                   controller(new DroppableControl());
                       
               //.. add more GUI elements here

               }});
                
              // <panel>
                panel(p2Board.gameComponentBuilder());
                
            }});
            
        }}.build(nifty);
       
       DroppableDropFilter ddf = new CardDropFilter("p1-card");
       
       for(int i = 0; i < 5; i++) {
        s.findControl("player 2 board card slot" + i, DroppableControl.class).addFilter(ddf);
       }
        
        nifty.addScreen("game_screen", s);
        
        nifty.gotoScreen("game_screen");
    }
    
}
