/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.AbstractCard;
import LegendsOfQuintessence.card.ConcreteCard;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.dragndrop.DraggableControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import java.util.ArrayList;

/**
 *
 * @author JMT
 */
public class PlayerComponentBuilder {
    private final Nifty nifty;
    private final Screen screen;
    private final Element player1ParentElement; 
    
    public PlayerComponentBuilder(Nifty n, Screen s,Element p1e) {
        nifty = n;
        screen = s;
        player1ParentElement = p1e;
    }
    
    public void buildPlayer1OuterPanel() {
        ArrayList<DraggableControl> playerCards = new ArrayList();
        AbstractCard ac = new ConcreteCard("player 1");
        
        AbstractGameComponent board = new Player1Board();
        
        PanelBuilder outerPlayerPanel = new PlayerOuterPanel(board).gameComponentBuilder();
        Element outerPlayerElement = outerPlayerPanel.build(player1ParentElement);
        
//        Element outerPlayerElement = outerPlayerPanel.build(nifty, screen, player1ParentElement);
        
        Element deckParent = new PanelBuilder() {{
            childLayoutVertical();
            alignCenter();
            width("25%");
        }}.build(outerPlayerElement);
        
        for(int i = 0; i < 15; i++) {
            ac.cardBuilder("card"+i).build(deckParent);
        }
    }
   
    
}
