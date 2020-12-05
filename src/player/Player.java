package player;

import gameplay.Game;
import items.Board;
import items.Field;

public class Player {
    private Integer id;
    private String name;            // player's name
    private Integer base_health;    // current health of player's base
    private Integer num_resources;  // current number of resources for player

    private Game game;

    /* TODO: add board components here */
    private Board board;
    private Field field;
    
   
    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.base_health = 10;
        this.num_resources = 2;
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
    }

    // disable the player
    public void disable() {
        System.out.println("Player " + name + "'s GUI interactions have been disabled.");

        /* TODO: disable GUI interactions for player here and possibly hide player's hand. */
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

        /* TODO: update the base health grahic here. */
    }

    /* ----------------- Getters and Setters --------------------- */

    public void setGame(Game game) {this.game = game;}

    public Integer getId() {return this.id; }
    public String getName() { return this.name; }
    public Integer getNumResources() { return this.num_resources; }
    public Integer getBaseHealth() { return this.base_health; }

    public Board getBoard() {return this.board;}
    public Field getField() {return this.field;}
    
}
