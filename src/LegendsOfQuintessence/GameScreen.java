/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import LegendsOfQuintessence.card.ConcreteCard;
import LegendsOfQuintessence.gameComponents.PlayerElementBuilder;
import LegendsOfQuintessence.gameplay.Game;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.dragndrop.DraggableControl;
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
public class GameScreen implements ScreenController{
    private Nifty nifty;
    private Game game;
    
    private final PlayerElements player1 = new Player1();
    private final PlayerElements player2 = new Player2();
    
    public GameScreen(Nifty n) {
        nifty = n;
        game = new Game(n);
    }
    
    public GameScreen(Nifty n, Game gs) {
        nifty = n;
        game = gs;
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
        
        PlayerElementBuilder p1Builder = new PlayerElementBuilder(
                nifty, screen, player1, "Player2");
        p1Builder.attachElements();
        p1Builder.buildCardsAndAttach();
        
        PlayerElementBuilder p2Builder = new PlayerElementBuilder(
                nifty, screen, player2, "Player1");
        p2Builder.attachElements();
        p2Builder.buildCardsAndAttach();
    }

    @Override
    public void onEndScreen() {
        System.out.println("Leaving game screen");
        //initScene();
    }

    public void quit(){
        nifty.gotoScreen("end");
    }
    
    
}
