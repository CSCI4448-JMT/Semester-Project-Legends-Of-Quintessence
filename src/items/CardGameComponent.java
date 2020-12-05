/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.HashMap;
import java.util.Map;
import player.Player;

/** A gener
 *
 * @author JMT
 */
public abstract class CardGameComponent {
    protected String name;
    
    protected Player player;
    protected HashMap<Integer, Card> cards;
    
    public void addCard(Integer position, Card card) {
        cards.put(position, card);
    }
    
    public void removeCard(Integer position, Card card) {
        cards.put(position, card);
    }
    
    public Integer getNumCards() {
        return cards.size();
    }
    
    public Card getCardAt(Integer position) {
        return cards.get(position);
    }
    
    protected void notifyObserver() {
       /* TODO: Notify state/player to limit other GUI interactions, depending on the component */
    }
}