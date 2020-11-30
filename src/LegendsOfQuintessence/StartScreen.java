 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.DroppableDropFilter;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
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
        final AbstractCard defenseCard = new ConcreteCard();
        final CardSlot cSlot = new CardSlot("board-slot");
        
        
 
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
                    
                    

                    control(defenseCard.cardBuilder());
                    control(defenseCard.cardBuilder());
                    
                //.. add more GUI elements here

               }});
                
               // <panel>
                panel(new PanelBuilder("player2-board") {{
                    childLayoutHorizontal(); // panel properties, add more...
                    x("200px");
                    y("500px");
                    height("175px");
                    width("700px");
                    backgroundColor("#2eb82e");
                    
                    // GUI elements
//                    control(new ButtonBuilder("Button_ID", "Hello Nifty"){{
//                        alignCenter();
//                        valignCenter();
//                        height("5%");
//                        width("15%");
//                    }});
                     

                    control(cSlot.slotBuilder());
                //.. add more GUI elements here

               }});
            }});
            
            
        }}.build(nifty);
       
       DroppableDropFilter ddf = new CardDropFilter();
       
       s.findControl("board-slot", DroppableControl.class).addFilter(ddf);
        
        nifty.addScreen("game_screen", s);
        
        nifty.gotoScreen("game_screen");
    }
    
}
