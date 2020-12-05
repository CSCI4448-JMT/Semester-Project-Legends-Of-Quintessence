/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.CardDropFilter;
import LegendsOfQuintessence.card.CardSlot;
import de.lessvoid.nifty.controls.DroppableDropFilter;
import de.lessvoid.nifty.controls.dragndrop.DroppableControl;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author JMT
 */
public class Player2Field extends AbstractGameComponent {
    
    public Player2Field() {
        width = "50%";
        height = "100%";
        componentName = "player 2 field";
        numAnchors = 5;
        cardAnchorLayout = new CardSlot("20%");
        backgroundImage = "Textures/stone.jpg";
    }
    
    public void attachFilters(Screen s) {
        DroppableDropFilter ddf = new CardDropFilter("player 2 card"); 
        for(int i = 0; i < numAnchors; i++) {
            s.findNiftyControl(
                    componentName + " card slot" + i, 
                    DroppableControl.class).addFilter(ddf);
        }
    }
    
}
