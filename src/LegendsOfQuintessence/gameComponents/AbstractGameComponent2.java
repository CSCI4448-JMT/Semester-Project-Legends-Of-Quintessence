/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.AbstractCard;
import java.util.HashMap;
import java.util.Map;
import LegendsOfQuintessence.player.Player;
import java.util.List;

/** A gener
 *
 * @author JMT
 */
public abstract class AbstractGameComponent2 {
    protected String name;
    
    protected Player player;
    protected HashMap<Integer, AbstractCard> cards;
    
    public AbstractGameComponent2(String name, Player player) {
        this.name = name;
        this.player = player;
    }
    
    public AbstractGameComponent2(String name, Player player, List<AbstractCard> cards) {
        this.name = name;
        this.player = player;
        
        int k = 1;
        for (AbstractCard card : cards) {
            this.cards.put(k, card);
            k += 1;
        }
    }
    
    public Integer getEmptyPosition() {
        for (int i = 0; i < 5; i++) {
            if (cards.get(i) != null) {
                return i;
            }
        }
        
        return null;
    }
     
    public void addCard(Integer position, AbstractCard card) {
        cards.put(position, card);
        card.setGameComponent(this);
    }
    
    public void removeCard(Integer position) {
        AbstractCard card = cards.remove(position);
        
        if (card != null) {
            card.setGameComponent(null);
        }
    }
    
    public void destroyCard(Integer position) {
        AbstractCard card = getCardAt(position);
        card.destroy();
        removeCard(position);
    }
    
    public Integer getNumCards() {
        return cards.size();
    }
    
    public AbstractCard getCardAt(Integer position) {
        return cards.get(position);
    }
    
    protected void notifyObserver() {
       /* TODO: Notify state/player to limit other GUI interactions, depending on the component */
    }
}
