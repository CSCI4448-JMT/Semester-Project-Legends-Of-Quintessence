/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import java.util.HashMap;
import LegendsOfQuintessence.player.Player;

/** The player's Board is used to store cards, purchased with player resources and 
 *  transferred from the player's hand before they are transferred to the player's Field.
 *
 * @author JMT
 */
public class Board extends AbstractGameComponent {
    
    public Board(Player player) {
        this.name = player.getId() + "-board";
        this.player = player;
        this.cards = new HashMap();
    }
    
}