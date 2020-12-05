/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.CardDropFilter;
import LegendsOfQuintessence.card.CardSlot;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.DroppableDropFilter;
import de.lessvoid.nifty.controls.dragndrop.DroppableControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import java.util.List;

/**
 *
 * @author JMT
 */
public abstract class AbstractGameComponent {
    protected String width;
    protected String height;
    protected String componentName;
    protected CardSlot cardAnchorLayout;
    protected int numAnchors;
    protected String backgroundColor;
    protected String backgroundImage;
    
    public PanelBuilder gameComponentBuilder() {
        
        return new PanelBuilder(componentName) {{
            childLayoutHorizontal(); // panel properties, add more...
            valignCenter();
            alignCenter();
            width(width);
            height(height);
            if(backgroundColor != null) {
                backgroundColor(backgroundColor);
            }
            if(backgroundImage != null) {
                backgroundImage(backgroundImage);
            }
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
    
    public void attachFilters(Screen s, String filterId) {
        DroppableDropFilter ddf = new CardDropFilter(filterId); 
        for(int i = 0; i < numAnchors; i++) {
            System.out.println(componentName + " Attaching filter");
            s.findNiftyControl(
                    componentName + " card slot" + i, 
                    DroppableControl.class).addFilter(ddf);
        }
        System.out.println(componentName + " Card filter attached");
    }
    
    public void removeFilters(Screen s, String filterId) {
        
    }
  
}
