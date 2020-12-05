/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.card;

import de.lessvoid.nifty.Nifty;

/**
 *
 * @author JMT
 */
public class ConcreteCard extends AbstractCard{
    public ConcreteCard(String abstractId) {
        attack_power = 5;
        defense_power = 3;
        resource_req = 4;
        cardAbstractId = abstractId;
        image_file = "Interface/shield.png";
    }
}
