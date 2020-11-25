/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.controls.dragndrop.builder.DraggableBuilder;
import de.lessvoid.nifty.controls.dynamic.TextCreator;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.Color;
import java.util.Properties;
import java.util.jar.Attributes;

/**
 *
 * @author JMT
 */
public abstract class AbstractCard implements Controller {
    protected Integer attack_power;
    protected Integer defense_power;
    protected Integer resource_req;
    
    private Element element;
    
    public ElementBuilder getCardBuilder() {
        
        DraggableBuilder DB = new DraggableBuilder() {{
            height("400px");
            width("250px");
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
                    backgroundColor(Color.randomColor());
                    childLayoutCenter();
                    text(new TextBuilder() {{ 
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
                        filename("Interface/DefenseGraphic.jpeg");
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
                        backgroundColor(Color.randomColor());
                        childLayoutCenter();
                        text(new TextBuilder() {{ 
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
                        backgroundColor(Color.randomColor());
                        childLayoutCenter();
                        text(new TextBuilder() {{ 
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
    
    @Override
    public void bind(Nifty nifty, Screen screen, Element elmnt, Parameters prmtrs) {
        this.element = elmnt;
    }

    @Override
    public void init(Parameters prmtrs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onEndScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void onStartScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onFocus(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inputEvent(NiftyInputEvent nie) {
        return false;
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
    }
    
    public final void setResourceReq(Integer resource_req) {
        this.resource_req = resource_req;
    } 
    
}
