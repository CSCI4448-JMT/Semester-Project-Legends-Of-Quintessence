/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.card;

import de.lessvoid.nifty.controls.Draggable;
import de.lessvoid.nifty.controls.Droppable;
import de.lessvoid.nifty.controls.DroppableDropFilter;
import de.lessvoid.nifty.controls.dragndrop.DraggableControl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author timothylenahan
 */
public class CardDropFilter implements DroppableDropFilter {
    private final String playerBaseName;
    private final String playerOpponentBaseName;
    
    public CardDropFilter(String player, String opponent){
        playerBaseName = player;
        playerOpponentBaseName = opponent;
    }

    @Override
    public boolean accept(Droppable dropSource, Draggable draggable, Droppable dropDes) {
        
        // assume draggable can be dropped/attached
        boolean canDrop = true;
        
        // check if a correct player card is already attached
        for(int i = 1; i <= 15; i++) {
            String curCardName = playerBaseName + "-card" + i;
            if(dropDes.getElement().findControl(
                    curCardName, DraggableControl.class) != null){
                canDrop = false;
            }
//            System.out.println(canDrop);
        }
        
        // check if the card being dropped is opponent's card
        for(int j = 1; j <= 15; j++) {
            String curCardName = playerOpponentBaseName + "-card" + j;
            if(draggable.getId().equals(curCardName)){
                canDrop = false;
            }

        }
            
        //if false is card cannot be dragged to the dropDes position
        return canDrop;
    }
    
    
}
