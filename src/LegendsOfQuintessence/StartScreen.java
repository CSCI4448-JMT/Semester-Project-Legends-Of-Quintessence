 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import LegendsOfQuintessence.card.CardDropFilter;

import LegendsOfQuintessence.gameComponents.Player1Board;
import LegendsOfQuintessence.gameComponents.Player1InPlay;
import LegendsOfQuintessence.gameComponents.Player1Hand;

import LegendsOfQuintessence.gameComponents.Player2Board;
import LegendsOfQuintessence.gameComponents.Player2Hand;
import LegendsOfQuintessence.gameComponents.Player2InPlay;
import LegendsOfQuintessence.gameComponents.Player2Base;

import LegendsOfQuintessence.gameComponents.Divider;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.DroppableDropFilter;
import de.lessvoid.nifty.controls.dragndrop.DroppableControl;
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
        nifty.fromXml("Interface/Board.xml", "Board", this);
        nifty.gotoScreen("Board"); 
        //final AbstractCard defenseCard1 = new ConcreteCard("p1-card");
        //final AbstractCard defenseCard2 = new ConcreteCard("p2-card");
        
        // Player 1 Set-up
        final Player1Board p1Board = new Player1Board();
        final Player1Hand p1Hand = new Player1Hand();
        final Player1InPlay p1InPlay = new Player1InPlay();
        
        final Divider divider = new Divider();
        
        
        final Player2Board p2Board = new Player2Board();
        final Player2Hand p2Hand = new Player2Hand();
        final Player2InPlay p2InPlay = new Player2InPlay();
        final Player2Base p2Base = new Player2Base();
        
        
        final CardGameScreen cgs = new CardGameScreen(nifty, gameState);
        
 /* 
       Screen s = new ScreenBuilder("Hello Nifty Screen"){{
            controller(new DefaultScreenController());
            
            layer(new LayerBuilder("boardLayer") {{ 
                childLayoutAbsolute();

                // <panel>
                /*
                panel(new PanelBuilder("player1-board") {{
                    childLayoutHorizontal(); // panel properties, add more...
                    height("175px");
                    x("200px");
                    width("700px");
                    backgroundColor("#2eb82e");
               

                    for(int i = 0; i < 5; i++){
                        control(defenseCard1.cardBuilder());
                    }

                   controller(new DroppableControl());
                       
               //.. add more GUI elements here

               }});

              // <panel>
              //Player 1 Panel Components
                panel(p1Board.gameComponentBuilder());
                panel(p1Hand.gameComponentBuilder());
                panel(p1InPlay.gameComponentBuilder());
                
                //Dividing Line btwn the 2 players
                panel(divider.gameComponentBuilder());
                
                //Player 2 Panel Components
                panel(p2Board.gameComponentBuilder());
                panel(p2Hand.gameComponentBuilder());
                panel(p2InPlay.gameComponentBuilder());
                panel(p2Base.gameComponentBuilder());
                
            }});
            
        }}.build(nifty);
       
       DroppableDropFilter ddf = new CardDropFilter("p1-card");
       
       for(int i = 0; i < 5; i++) {
            s.findControl("player 1 board card slot" + i, DroppableControl.class).addFilter(ddf);
            s.findControl("player 1 hand card slot" + i, DroppableControl.class).addFilter(ddf);
            s.findControl("player 1 inPlay card slot" + i, DroppableControl.class).addFilter(ddf);
            s.findControl("player 2 board card slot" + i, DroppableControl.class).addFilter(ddf);
            s.findControl("player 2 hand card slot" + i, DroppableControl.class).addFilter(ddf);
            s.findControl("player 2 inPlay card slot" + i, DroppableControl.class).addFilter(ddf);
       }
     
        nifty.addScreen("game_screen", s);

        nifty.gotoScreen("game_screen");
        */
    }
    
}
