/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.AbstractCard;
import LegendsOfQuintessence.card.CardSlot;
import LegendsOfQuintessence.card.ConcreteCard;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.dragndrop.DraggableControl;
import de.lessvoid.nifty.screen.Screen;
import java.util.List;

/**
 *
 * @author JMT
 */
public class PlayerDeck extends AbstractGameComponent {
    private final List<DraggableControl> deck;
    
    public PlayerDeck(String playerId, List<DraggableControl> d) {
        width = "50%";
        height = "75%";
        componentName = "player " + playerId + " deck";
        numAnchors = 1;
        cardAnchorLayout = new CardSlot("50%");
        backgroundImage = "Textures/deck-base.jpg";
        deck = d;
    }
    
    @Override
    public PanelBuilder gameComponentBuilder() {
        final AbstractCard c = new ConcreteCard("player 1 card");
        
        return new PanelBuilder(componentName) {{
            childLayoutVertical(); // panel properties, add more...
            valignCenter();
            alignCenter();
            width(width);
            height(height);
            backgroundImage(backgroundImage);
            panel(cardAnchorLayout.slotBuilder(componentName + " card slot0" ));
            for(DraggableControl d: deck) {
                controller(d);
            }
        }};
    }
}
