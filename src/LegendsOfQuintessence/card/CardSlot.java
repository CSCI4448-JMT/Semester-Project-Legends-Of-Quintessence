/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.card;

import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.dragndrop.builder.DroppableBuilder;

/**
 *
 * @author JMT
 */
public class CardSlot {
    private final String height = "125px";
    private final String width = "85px";
    private final String panelWidth;
    
    public CardSlot(String pw) {
        panelWidth = pw;
    }
    
    public PanelBuilder slotBuilder(String slotName) {
        final String sn = slotName;
        
        PanelBuilder cardPanel =  new PanelBuilder() {{
            childLayoutVertical();
            valignCenter();
            alignCenter();
            width(panelWidth);
            control(new DroppableBuilder(sn) {{
                height(height);
                width(width);
                backgroundColor("#ccffcc");
                valignCenter();
                alignCenter();
            }});
        }};
        
        return cardPanel;
    }
    
}
