/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.controls.dragndrop.builder.DraggableBuilder;
import de.lessvoid.nifty.controls.dynamic.TextCreator;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.Color;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;

/**
 *
 * @author JMT
 */
public abstract class AbstractCard {
    protected Integer attack_power;
    protected Integer defense_power;
    protected Integer resource_req;
    
    private Element card_element; // card element to be stored here, whenever it is built in Nifty screen.
    protected String image_file;
    
    public void updateDefensePower() {
        if (card_element != null) {
            Element text_element = card_element.findElementById("defense-power-text");

            if  (text_element != null) {
                text_element.getRenderer(TextRenderer.class).setText(defense_power.toString());
            }
        }
    }
    
    public ControlBuilder cardBuilder() {
        final Color color = Color.randomColor();
        
        DraggableBuilder DB = new DraggableBuilder("card") {{
            valignCenter();
            height("150px");
            width("85px");
            //backgroundColor("#f00f");
            style("nifty-panel-simple");
            childLayoutVertical();
            panel(new PanelBuilder() {{
                height("30%");
                width("100%");
                //backgroundColor(Color.randomColor());
                childLayoutCenter();
                panel(new PanelBuilder() {{
                    height("50%");
                    width("25%");
                    backgroundColor("#33cc33");
                    childLayoutCenter();
                    text(new TextBuilder("resource-req-text") {{ 
                        font("Interface/Fonts/ArialBlack.fnt");
                        text(resource_req.toString());
                        }}
                    );
                    }}
                );
                }}
            );
            panel(new PanelBuilder() {{
                height("40%");
                width("100%");
                //backgroundColor(Color.randomColor());                    
                childLayoutCenter();
                image(new ImageBuilder() {{
                        height("100%");
                        width("70%");
                        filename(image_file);
                    }}
                );
                }}
            );
            panel(new PanelBuilder() {{
                height("30%");
                width("100%");
                //backgroundColor(Color.randomColor());
                childLayoutHorizontal();
                panel(new PanelBuilder() {{
                    height("100%");
                    width("50%");
                    //backgroundColor(Color.randomColor());
                    childLayoutCenter();
                    panel(new PanelBuilder() {{
                        height("50%");
                        width("50%");
                        backgroundColor("#DC143C");
                        childLayoutCenter();
                        text(new TextBuilder("attack-power-text") {{ 
                            font("Interface/Fonts/ArialBlack.fnt");
                            text(attack_power.toString());
                            }}
                        );
                        }}
                    );
                    }}
                );
                panel(new PanelBuilder() {{
                    height("100%");
                    width("50%");
                    //backgroundColor(Color.randomColor());
                    childLayoutCenter();
                    panel(new PanelBuilder() {{
                        height("50%");
                        width("50%");
                        backgroundColor("#6495ED");
                        childLayoutCenter();
                        text(new TextBuilder("defense-power-text") {{ 
                            font("Interface/Fonts/ArialBlack.fnt");
                            text(defense_power.toString());
                            }}
                        );
                        }}
                    );
                    }}
                );
                }}
            );
        }};
        
        return DB;
    }
    
    
    // ----------- GETTERS / SETTERS ------------- //
    
    public final Integer getAttackPower() {
        return attack_power;
    }
    
    public final Integer getDefensePower() { 
        return defense_power;
    }

    public final Integer getResourceReq() {
        return resource_req;
    }
    
    public final void setAttackPower(Integer attack_power) {
        this.attack_power = attack_power;
    }
    
    public final void setDefensePower(Integer defense_power) {
        this.defense_power = defense_power;
        updateDefensePower();
    }
    
    public final void setResourceReq(Integer resource_req) {
        this.resource_req = resource_req;
    } 
    
    public final void setElement(Element card_element) {
        this.card_element = card_element;
    }
    
}
