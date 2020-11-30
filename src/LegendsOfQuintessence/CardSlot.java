/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.controls.dragndrop.builder.DroppableBuilder;

/**
 *
 * @author JMT
 */
public class CardSlot {
    private final String height = "125px";
    private final String width = "85px";
    private String name;
    
    public CardSlot(String string) {
        name = string;
    }
    
    public ControlBuilder slotBuilder() {
        ControlBuilder slot = new DroppableBuilder(name) {{
            height(height);
            width(width);
            backgroundColor("#ccffcc");
            valignCenter();
            
        }};
        
        return slot;
    }
    
}
