package LegendsOfQuintessence.player;

import LegendsOfQuintessence.gameplay.Game;
import LegendsOfQuintessence.card.AbstractCard;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import java.util.List;

public class Player {
    private String id;              // player id (GUI-related)
    private String name;            // player's name
    private Integer base_health;    // current health of player's base
    private Integer num_resources;  // current number of resources for player
    private final SimpleElementsFactory playerElementsGetter;
    
    
    // Nifty GUI and Elements
    private Nifty nifty;
    private final PlayerElements player_elements;
    
    private Game game; // player game

    public Player(String id, String name) {
        playerElementsGetter = new SimpleElementsFactory();
        this.id = id;
        this.name = name;
        this.base_health = 10;
        this.num_resources = 2;
        
        this.player_elements = playerElementsGetter.getPlayerElements(id);
    }
    
    public void buildGUI() {
        
        Nifty nifty = game.getNifty();
        Screen screen = nifty.getCurrentScreen();
        
        PlayerElementBuilder playerBuilder = new PlayerElementBuilder(
                nifty, screen, player_elements, id);
        
        playerBuilder.attachElements();
        playerBuilder.buildCardsAndAttach();
        
    }

    public void endTurn() {
        game.endTurnRequest();
    }

    public void endRound() {
        game.endRoundRequest();
    }

    // enable the player
    public void enable() {
        System.out.println("Player " + name + "'s GUI interactions have been enabled.");

        System.out.println("TO DO: notify player GUI action controls from here.");

        /* TODO: enable GUI interactions for player here and possibly show player's hand. */
        // enable all cards
        List<Element> cards = player_elements.getCards();
        
        List<Element> buttons = player_elements.getButtons();
        
        for(Element b: buttons) {
            b.enable();
        }
    }

    // disable the player
    public void disable() {
        System.out.println("Player " + name + "'s GUI interactions have been disabled.");

        /* TODO: disable GUI interactions for player here and possibly hide player's hand. */
        // disable all cards
        List<Element> cards = player_elements.getCards();
        
        for(Element c: cards){
            c.disable();
        }
        
        List<Element> buttons = player_elements.getButtons();
        
        for(Element b: buttons) {
            b.disable();
        }
    }

    public void incrementResources(Integer increment) {
        num_resources = num_resources + increment;

        System.out.printf("Player %s has been given %s resources for a total of %s.\n", name, increment, this.num_resources);

        /* TODO: update the resource graphic here. */
    }

    public void decrementBaseHealth(Integer decrement) {
        base_health = base_health - decrement;
        if (base_health < 0) base_health = 0;

        System.out.printf("Player %s's base health has been decremented by %s, leaving %s health points left.\n", name, decrement, this.base_health);

        /* TODO: update the base health graphic here. */
    }

    
    // return card element in field at given position    
    public Element getFieldCardAt(Integer position) {
        List<DroppableControl> inPlay = this.player_elements.getInPlay();
        
        DraggableControl cardControl = null;
        
        // retrieve the draggable control at given position
        int num = 0;        
        for (DroppableControl slot : inPlay) {
            if (num == position) {
                cardControl = slot.getDraggable();
            }
            num++;
        }
        
        // get the element associated to the control
        Element card = null;
        if (cardControl != null) {
            card = cardControl.getElement();
        }
        
        return card;
    }
    
    // return card element in board at given position
    public Element getBoardCardAt(Integer position) {
        List<DroppableControl> inPlay = this.player_elements.getBoard();
        
        DraggableControl cardControl = null;
        
        // retrieve the draggable control at given position
        int num = 0;
        for (DroppableControl slot : inPlay) {
            if (num == position) {
                cardControl = slot.getDraggable();
            }
            num++;
        }
        
        // get the element associated to the control
        Element card = null;
        if (cardControl != null) {
            card = cardControl.getElement();
        }
        
        return card;
    }
    
    // return slot element in board at given position
    public Element getBoardSlotAt(Integer position) {
        
        List<DroppableControl> board = this.player_elements.getBoard();
        
        Element droppable_element = null;
        
        int num = 0;
        for (DroppableControl slot : board) {
            if (num == position) {
                droppable_element = slot.getElement();
            }
            num++;    
        }
        
        return droppable_element;
    }
    
    // get the attack power from the card element
    private Integer getAttackPower(Element card_element) {
        Element text_element = card_element.findElementById("attack-power-text");
        
        String attack_power_text = "-1";
        if (text_element != null) {
            attack_power_text = text_element.getRenderer(TextRenderer.class).getOriginalText();
        }
        
        return Integer.parseInt(attack_power_text);
    }
    
    // get the defend power from the card element
    private Integer getDefendPower(Element card_element) {
        Element text_element = card_element.findElementById("defend-power-text");
        
        String defend_power_text = "-1";
        if (text_element != null) {
            defend_power_text = text_element.getRenderer(TextRenderer.class).getOriginalText();
        }
        
        return Integer.parseInt(defend_power_text);
    }
    
    private void decrementDefensePower(Element card_element, Integer decrement) {
        Element text_element = card_element.findElementById("defend-power-text");
        
        if (text_element != null) {
            Integer defend_power = getDefendPower(card_element);
            
            if (defend_power != -1) {
                defend_power -= decrement;

                // set defend power to 0, if decrmenting makes it goes below 0
                if (defend_power < 0) { defend_power = 0; }

                // update the text on the card
                text_element.getRenderer(TextRenderer.class).setText(defend_power.toString());       
            }
        }   
    }
    
    // player can combat(i.e. attack) another player
    public void attack(Player defend_player) { 
        
        // deal with damage-oriented aspects of combat 
        for (int i = 0; i < 5; i++) {
            Element attack_card = this.getFieldCardAt(i);
            Element defend_card = defend_player.getFieldCardAt(i);
            
            if (attack_card != null) {
                Integer attacker_attack_power = getAttackPower(attack_card);
                
                
                if (defend_card == null) {
                    // no defending card, subtract damage from base health
                    defend_player.decrementBaseHealth(attacker_attack_power);
                } else {
                    // defending card, subtract damage from both cards
                 
                    Integer defender_attack_power = getAttackPower(defend_card);
                    decrementDefensePower(attack_card, defender_attack_power);
                    decrementDefensePower(defend_card, attacker_attack_power);
                    
                    // destroy cards with zero defense power left
                    if (getDefendPower(attack_card) == 0) {
                        attack_card.markForRemoval();
                    }
                    if (getDefendPower(defend_card) == 0) {
                        defend_card.markForRemoval();
                    }                    
                }
               
            } 
        }
        
        // the cards left in the field must be returned to the board
        this.resetField();
        defend_player.resetField();
        
    }
    
    // move all cards from field to board
    public void resetField() {
        for (int i = 0; i < 5; i++) {
            Element card = this.getFieldCardAt(i);
            
            if (card != null) {
                for (int j = 0; j < 5; j++) {
                    // check for a conflicting card at board slot
                    Element conflict_card = this.getBoardCardAt(j);
                    
                    if (conflict_card == null) { // there is no conflicting card
                        Element board_slot = this.getBoardSlotAt(j);
                        conflict_card.markForMove(board_slot);
                        break;
                    }   
                }
            }
        }
    }
    
    public Integer getNumBoardCards() {
        //TODO: get board cards
        return 1;
    }

    public Integer getNumFieldCards() {
        //TODO: get field cards
        return 1;
    }
    
    public Integer getNumAffordableCards() {
        // TODO: get affordable cards
        return 1;
    }
    
    /* ----------------- Getters and Setters --------------------- */

    public String getId() {return this.id; }
    public String getName() { return this.name; }
    public Integer getNumResources() { return this.num_resources; }
    public Integer getBaseHealth() { return this.base_health; }
    
    public void setId(String id) {this.id = id;}
    
    public void setGame(Game game) {this.game = game;}
}
