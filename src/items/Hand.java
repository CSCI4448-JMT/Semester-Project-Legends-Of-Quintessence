/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.HashMap;
import player.Player;

/** The player's Hand is used to store cards that have not yet been purchased 
 *  with player resources, and are updated with cards from the player's Deck
 * @author JMT
 */
public class Hand extends AbstractGameComponent {
    
    public Hand(Player player) {
        this.name = player.getId() + "-hand";
        this.player = player;
        this.cards = new HashMap();
    }
    
    // returns hash map of cards the player can currently afford with their resources
    public HashMap<Integer,AbstractCard> getAffordableCards() {
        
        HashMap<Integer,AbstractCard> affordable_cards = new HashMap();
        
        for (int i = 0; i < 5; i++) {
            AbstractCard card = cards.get(i);
            
            if (card != null) {
                Integer cost = card.getResourceReq();
                if (cost <= player.getNumResources()) {
                    affordable_cards.put(i, card);
                }
            }
        }
        
        return affordable_cards;
    }
}