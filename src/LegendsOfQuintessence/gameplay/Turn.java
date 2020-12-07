package LegendsOfQuintessence.gameplay;

import LegendsOfQuintessence.player.Player;

public class Turn {
    private final Round round;
    private final Player player;
    
    // used to store the number of cards in the board 
    // at start of turn, to detect skips when turn is ended
    private Integer start_board_num; 
    
    /* --------------- PACKAGE FUNCTIONS ------------------ */

    Turn(Player player, Round round) {
        this.round = round;
        this.player = player;
    }

    void startTurn() {
        System.out.println("\nPlayer " + player.getName() + " has started their turn.");
        
        start_board_num = player.getBoard().getNumCards();
        
        if (round.getSkipCounter() >= 2) {
            /* TODO: display end round button */
        }
        
        
        player.enable();
    }

    void endTurn() {
        player.disable();
        System.out.println("Player " + player.getName() + " has ended their turn.");

        // detect if player is skipping
        if (checkForSkip()) {
            round.incrementSkipCounter();
        } else {
            round.resetSkipCounter();
        }
        
        // detect if cards are being set for combat
        if (checkForCombat()) {
            round.combat_started = true;
        }

        round.nextTurn();
    }

    boolean checkForCombat() {
        if (player.getField().getNumCards() > 0) {
            return true;
        }
        return false;
    }

    boolean checkForSkip() {
        // to detect skip, check that there are no cards on the field, and
        // that the number of cards on the board hasn't changed:
        if (player.getField().getNumCards() == 0 && 
                player.getBoard().getNumCards() == start_board_num) {
            return true;
        }
        return false;
    }

}
