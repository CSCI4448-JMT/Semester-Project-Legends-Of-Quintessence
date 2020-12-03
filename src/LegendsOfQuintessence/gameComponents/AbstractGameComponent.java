/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.CardSlot;
import de.lessvoid.nifty.builder.PanelBuilder;
import java.util.List;

/**
 *
 * @author JMT
 */
public abstract class AbstractGameComponent {
    protected String width;
    protected String height;
    protected String xPos;
    protected String yPos;
    protected String componentName;
    protected CardSlot cardAnchorLayout;
    protected int numAnchors;
    
    public PanelBuilder gameComponentBuilder() {
        
        return new PanelBuilder(componentName) {{
            childLayoutHorizontal(); // panel properties, add more...
            width(width);
            height(height);
            x(xPos);
            y(yPos);
            backgroundColor("#2eb82e");
            for(int i = 0; i < numAnchors; i++) {
                panel(cardAnchorLayout.slotBuilder(componentName + " card slot" + i));
            }
        }};
    }
    
    public void setWidth(String w) {
        width = w;
    }
    
    public void setHeight(String h) {
        height = h;
    }
    
    public void setX(String x) {
        xPos = x;
    }
    
    public void setY(String y) {
        yPos = y;
    }
}
