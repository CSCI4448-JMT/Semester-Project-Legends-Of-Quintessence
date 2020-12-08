package LegendsOfQuintessence.player;

import LegendsOfQuintessence.Player1;
import LegendsOfQuintessence.PlayerElements;
import LegendsOfQuintessence.gameplay.Game;
import LegendsOfQuintessence.card.AbstractCard;
import LegendsOfQuintessence.gameComponents.PlayerElementBuilder;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

public class Player {
    private String id;              // player id (GUI-related)
    private String name;            // player's name
    private Integer base_health;    // current health of player's base
    private Integer num_resources;  // current number of resources for player
    
    // Nifty GUI and Elements
    private Nifty nifty;
    private final PlayerElements player_elements;
    
    private Game game; // player game

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.base_health = 10;
        this.num_resources = 2;
        
        this.player_elements = new Player1();
    }
    
    public void buildGUI() {
        
        Nifty nifty = game.getNifty();
        Screen screen = nifty.getCurrentScreen();
        
        PlayerElementBuilder p1Builder = new PlayerElementBuilder(
                nifty, screen, player_elements, "Player2");
        
        p1Builder.attachElements();
        p1Builder.buildCardsAndAttach();
        
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
    }

    // disable the player
    public void disable() {
        System.out.println("Player " + name + "'s GUI interactions have been disabled.");

        /* TODO: disable GUI interactions for player here and possibly hide player's hand. */
        // disable all cards
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

    // player can combat(i.e. attack) another player
    public void attack(Player defend_player) {
        Field attack_field = this.field;
        Field defend_field = defend_player.getField();    
        
        // deal with damage-oriented aspects of combat 
        for (int i = 0; i < 5; i++) {
            AbstractCard attack_card = attack_field.getCardAt(i);
            AbstractCard defend_card = defend_field.getCardAt(i);
            
            if (attack_card != null) {
                Integer attack_damage = attack_card.getAttackPower();
                
                
                if (defend_card == null) {
                    // no defending card, subtract damage from base health
                    defend_player.decrementBaseHealth(attack_damage);
                } else {
                    // defending card, subtract damage from both cards
                 
                    Integer defend_damage = defend_card.getAttackPower();
                    attack_card.decrementDefensePower(defend_damage);
                    defend_card.decrementDefensePower(attack_damage);
                    
                    // destroy cards with zero defense power left
                    if (attack_card.getDefensePower() == 0) {
                        attack_card.destroy();
                    }
                    if (defend_card.getDefensePower() == 0) {
                        defend_card.destroy();
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
            AbstractCard card = this.field.getCardAt(i);
            
            if (card != null) {
                Integer empty_pos = this.board.getEmptyPosition();
                
                if (empty_pos != null) {
                    this.field.removeCard(i);
                    this.board.addCard(empty_pos, card);
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
    
    public Board getBoard() {return this.board;}
    public Field getField() {return this.field;}
    public Hand getHand() {return this.hand;}    
    
    public void setId(String id) {this.id = id;}
    
    public void setBoard(Board board) {this.board = board;}
    public void setField(Field field) {this.field = field;}
    public void setHand(Hand hand)    {this.hand  = hand;}
    
    public void setGame(Game game) {this.game = game;}
}
