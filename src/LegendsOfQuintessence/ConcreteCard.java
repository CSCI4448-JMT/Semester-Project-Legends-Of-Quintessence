/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author JMT
 */
public class ConcreteCard extends AbstractCard{
    ConcreteCard() {
        attack_power = 5;
        defense_power = 3;
        resource_req = 4;
        
        image_file = "Interface/shield.png";
    }
}
