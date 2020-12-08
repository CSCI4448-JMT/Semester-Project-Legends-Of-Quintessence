package LegendsOfQuintessence.gameplay;

import LegendsOfQuintessence.card.AbstractCard;
import LegendsOfQuintessence.player.Player;

/*  Responsibility: take care of the round-related aspects of the game */
public class Round {
    private final Game game;
    private final Integer round_number; // round number, used for resource allocation

    // round-related dynamics
    private Integer skip_counter;       // the number of consecutive skips in round
    public Boolean combat_started;      // has the attacker initiated combat
    public Boolean combat_resolved;     // has the combat been resolved
    private Boolean end_round_request;  // has the player requested to end the round

    // the players identified by their role
    public final Player attack_player;
    public final Player defend_player;

    private Turn turn;            // current turn
    public Player current_player; // player with current turn

    // TODO : REMOVE THIS
    Integer temp_count = 0;

    /* --------------- PACKAGE FUNCTIONS ------------------ */

    Round(Player attack_player, Player defend_player, Game game) {
        this.game = game;
        this.round_number = game.getRoundNumber();

        this.skip_counter = 0;          
        this.combat_started = false;
        this.end_round_request = false; 

        this.attack_player = attack_player;
        this.defend_player = defend_player;
    }

    void startRound() {
        System.out.println("\n========== ROUND " + round_number + " HAS STARTED ==========");
        System.out.println("Player " + attack_player.getName() +  " is the attacker for this round.");

        // attacker always goes first in the round
        current_player = attack_player;

        // increment both player's resources at start of round
        if (round_number < 5) {
            attack_player.incrementResources(round_number);
            defend_player.incrementResources(round_number);
        } else {
            attack_player.incrementResources(5);
            defend_player.incrementResources(5);
        }

        // start a new turn
        startTurn();
    }

    void nextTurn() {
        if (checkRoundEnd()) {
            endRound();
        } else if (combat_started && !combat_resolved && current_player == defend_player) {
            attack_player.attack(defend_player);
            combat_resolved = true;
        } else {
            // TODO : REMOVE THIS TEMP COUNT STUFF, USED TO LIMIT TURN COUNT WHEN TESTING
            temp_count += 1;
            if (temp_count < 4) {

                // switch current player for the turn
                if (current_player == attack_player) {
                    current_player = defend_player;
                } else {
                    current_player = attack_player;
                }

                // start a new turn
                startTurn();
            } else {
                endRound();
            }
        }
    }

    void endRound() {        
        System.out.println("========== ROUND " + round_number + " HAS ENDED ===========");
        game.nextRound();
    }

    void endRoundRequest() {
        end_round_request = true;
        turn.endTurn();
    }
    
    void endTurnRequest() {
        turn.endTurn();
    }

    private boolean checkRoundEnd() {
        // Case 1: End round action has been completed by either player
        if (end_round_request) {
            return true;
        }

        // Case 2. Attack action has been completed by the attacking player and both players
        // have less resources than all of the resource costs of cards in their hand.
        if (combat_started && current_player == defend_player) {
            
            if (attack_player.getNumAffordableCards() > 0 &&
                    defend_player.getNumAffordableCards() > 0) {
                return true;
            }
        }

        // Case 3: Match ends.
        if (attack_player.getBaseHealth() == 0 || defend_player.getBaseHealth() == 0) {
            return true;
        }

        return false;
    }
    
  
    void startTurn() {
        turn = new Turn(current_player, this);
        turn.startTurn();
    }
    
    void resetSkipCounter()      { skip_counter = 0; }
    void incrementSkipCounter()  { skip_counter += 1; }

    /* ----------------- Getters and Setters --------------------- */

    public Turn getTurn() {return turn; }
    public Integer getSkipCounter() {return skip_counter;}
}
