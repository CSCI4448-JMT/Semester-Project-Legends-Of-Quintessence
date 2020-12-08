/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.player;

import de.lessvoid.nifty.controls.dragndrop.DroppableControl;
import de.lessvoid.nifty.elements.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author JMT
 */
public abstract class PlayerElements {
    // name for identifying xml elements
    protected String elementBaseName;
    // name for rendering
    protected String name;
    private Element base;
    private Element deck;
    private List<Element> cards = new ArrayList();
    private List<DroppableControl> hand = new ArrayList();
    private List<DroppableControl> board = new ArrayList();
    private List<DroppableControl> inPlay = new ArrayList();
    private List<Element> buttons = new ArrayList();
    
    
    public List<DroppableControl> getHand() {
        return hand;
    }
    
    public List<DroppableControl> getBoard() {
        return board;
    }
    
    public List<DroppableControl> getInPlay() {
        return inPlay;
    }
    
    public String getElementBaseName() {
        return elementBaseName;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public Element getBase() {
        return base;
    }
    
    public void setBase(Element b) {
        base = b;
    }
    
    public Element getDeck() {
        return deck;
    }
    
    public void setDeck(Element d) {
        deck = d;
    }
    
    public List<Element> getCards() {
        return cards;
    }
    
    public void addCard(Element card) {
        cards.add(card);
    }
    
    public void addHandSlot(DroppableControl slot) {
        hand.add(slot);
    }
    
    public void addBoardSlot(DroppableControl slot) {
        board.add(slot);
    } 
    
    public void addInPlaySlot(DroppableControl slot) {
        inPlay.add(slot);
    }
    
    public List<Element> getButtons() {
        return buttons;
    }
    
    public void addButton(Element b) {
        buttons.add(b);
    }
    
}
