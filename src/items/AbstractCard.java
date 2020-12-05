/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author Rashed
 */
public class AbstractCard {
    protected AbstractGameComponent card_component;
    
    protected Integer attack_power;
    protected Integer defense_power;
    protected Integer resource_req;
    
    // private Element card_element; // card element to be stored here, whenever it is built in Nifty screen.
    protected String image_file;
    
    public void updateDefensePower() {
        /*if (card_element != null) {
            Element text_element = card_element.findElementById("defense-power-text");

            if  (text_element != null) {
                text_element.getRenderer(TextRenderer.class).setText(defense_power.toString());
            }
        }*/
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
    
    public final void decrementDefensePower(Integer decrement) {
        this.defense_power = this.defense_power - decrement;
        if (this.defense_power < 0) {
            this.defense_power = 0;
        }
        
        if (this.defense_power != 0) { 
            updateDefensePower();
        } else {
            // TODO: destroy card gui
        }
    }
    
    /*
    public final void moveTo(AbstractGameComponent component) {
        card_component.removeCard(attack_power);
        
    }*/
    
    public final void destroy() {
        /* TO DO: remove GUI element from field */
    }
    
    //public final void setElement(Element card_element) {
    //    this.card_element = card_element;
    //}
}
