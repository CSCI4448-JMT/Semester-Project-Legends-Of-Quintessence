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

/**
 *
 * @author timothylenahan
 */
public class CardDropFilter implements DroppableDropFilter {
    private final String draggableCardName;
    
    public CardDropFilter(String cardAbstractName){
        draggableCardName = cardAbstractName;
    }

    @Override
    public boolean accept(Droppable dropSource, Draggable draggable, Droppable dropDes) {
        return dropDes.getElement().findControl(draggableCardName, DraggableControl.class) == null;
    }
    
    
}
