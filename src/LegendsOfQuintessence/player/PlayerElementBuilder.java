/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.player;

import LegendsOfQuintessence.card.CardDropFilter;
import LegendsOfQuintessence.card.ConcreteCard;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.DroppableDropFilter;
import de.lessvoid.nifty.controls.dragndrop.DroppableControl;
import de.lessvoid.nifty.controls.dragndrop.builder.DraggableBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author JMT
 */
public class PlayerElementBuilder {
    private Nifty nifty;
    private Screen screen;
    private PlayerElements player;
    private String opponentBaseElementName;
    private final DroppableDropFilter cardSlotFilter;
    
    public PlayerElementBuilder(Nifty n, Screen s, 
            PlayerElements p, String playerId) {
        nifty = n;
        screen = s;
        player = p;
        String opponent;
        if("1".equals(playerId)) {
            opponent = "Player2";
        }else{
            opponent = "Player1";
        }
        opponentBaseElementName = opponent;
        // create dropfilter for player card slots
        cardSlotFilter = new CardDropFilter(
            player.getElementBaseName(),
            opponentBaseElementName);
    }
    
    
    public void buildCardsAndAttach() {
        ConcreteCard c = new ConcreteCard(player.getElementBaseName()+"-card");
        DraggableBuilder eb = (DraggableBuilder) c.cardBuilder();
        
        for(int i = 1; i <= 15; i++) {
            Element card = eb.build(nifty, screen, player.getDeck());
            c.setDefensePower(10);
            eb = c.cardBuilder();
            
            /* 
            add to player cards attribute to 
            keep track of cards active on the board
            */
            player.addCard(card);
        }
       
    }
    
    
    public void attachElements() {
        String baseElementName = player.getElementBaseName();
        // find deck element and attach to player
        Element deckPanel = screen.findElementById(baseElementName + "_deck_panel");
        player.setDeck(deckPanel);
        
        // base element and attach to player
        Element baseText = screen.findElementById(baseElementName + "_baseInfo_label");
        player.setBase(baseText);
        
        // get board slots and attach
        for(int i = 1; i <= 5; i++) {
            DroppableControl dc = screen.findNiftyControl(
                    baseElementName + "-board" + i, DroppableControl.class);
            
            // attach filter
            dc.addFilter(cardSlotFilter);
            
            player.addBoardSlot(dc);
        }
        
        // get inPlay slots and attach
        for(int i = 1; i <= 5; i++) {
            DroppableControl dc = screen.findNiftyControl(
                    baseElementName + "-inPlay" + i, DroppableControl.class);
            
            // attach filter
            dc.addFilter(cardSlotFilter);
            
            player.addInPlaySlot(dc);
        }
        
        // get player endTurn button
        player.addButton(screen.findElementById(player.getElementBaseName() + "-EndTurnButton"));
       
    }  
    
}
