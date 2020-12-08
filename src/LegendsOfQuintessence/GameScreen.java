/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import LegendsOfQuintessence.player.PlayerElements;
import LegendsOfQuintessence.player.Player2;
import LegendsOfQuintessence.player.Player1;
import LegendsOfQuintessence.card.ConcreteCard;
import LegendsOfQuintessence.player.PlayerElementBuilder;
import LegendsOfQuintessence.gameplay.Game;
import LegendsOfQuintessence.player.Player;
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
import de.lessvoid.nifty.controls.label.LabelControl;
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
    private final Player player1 = new Player("1", "Player1");
    private final Player player2 = new Player("2", "Player2");
    
    public GameScreen(Nifty n) {
        nifty = n;
        game = new Game(n, player1, player2);
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
        
        player1.buildGUI();
        player2.buildGUI();
        
        tl/getfieldcards
        game.start();

        //Player 1 Display Stats
        Element baseHealth1 = screen.findElementByName("Player1_baseHealth");
        LabelControl control1 = baseHealth1.getControl(LabelControl.class);
        control1.setText("Base Health: "+ player1.getBaseHealth().toString());
        
        Element numResources1 = screen.findElementByName("Player1_numResources");
        LabelControl control2 = numResources1.getControl(LabelControl.class);
        control2.setText("# of Resources: "+ player1.getNumResources().toString());
        
        //Player 2 Display Stats
        Element baseHealth2 = screen.findElementByName("Player2_baseHealth");
        LabelControl control3 = baseHealth2.getControl(LabelControl.class);
        control3.setText("Base Health: "+ player2.getBaseHealth().toString());
        
        Element numResources2 = screen.findElementByName("Player2_numResources");
        LabelControl control4 = numResources2.getControl(LabelControl.class);
        control4.setText("# of Resources: "+ player2.getNumResources().toString());
        
       
//        PlayerElementBuilder p1Builder = new PlayerElementBuilder(
//                nifty, screen, player1, "Player2");
//        p1Builder.attachElements();
//        p1Builder.buildCardsAndAttach();
//        
//        PlayerElementBuilder p2Builder = new PlayerElementBuilder(
//                nifty, screen, player2, "Player1");
//        p2Builder.attachElements();
//        p2Builder.buildCardsAndAttach();
    }

    @Override
    public void onEndScreen() {
        System.out.println("Leaving game screen");
        //initScene();
    }

    public void quit(){
        nifty.gotoScreen("end");
    }
    
    @NiftyEventSubscriber(id="Player1-EndTurnButton")
    public void onEndTurn1(String id, NiftyMousePrimaryClickedEvent event) {
        game.endTurnRequest();
    }
    
    @NiftyEventSubscriber(id="Player2-EndTurnButton")
    public void onEndTurn2(String id, NiftyMousePrimaryClickedEvent event) {
        game.endTurnRequest();
    }
    
}
