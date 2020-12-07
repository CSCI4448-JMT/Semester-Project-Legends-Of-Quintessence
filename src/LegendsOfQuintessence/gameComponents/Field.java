/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import java.util.HashMap;
import LegendsOfQuintessence.player.Player;

/** The player's Field contains cards used for attack or defending against the opponent.
 *
 * @author JMT
 */
public class Field extends AbstractGameComponent2 {
    
    public Field(Player player) {
        this.name = player.getId() + "-field";
        this.player = player;
        this.cards = new HashMap();
    }
    
}
