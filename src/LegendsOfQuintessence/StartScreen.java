 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
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
        nifty.fromXml("Interface/card.xml", "GScreen0", this);
        nifty.gotoScreen("GScreen0");
    }
    
}
