package gameplay;

import player.Player;

public class Turn {
    private final Round round;
    private final Player player;

    /* --------------- PACKAGE FUNCTIONS ------------------ */

    Turn(Player player, Round round) {
        this.round = round;
        this.player = player;
    }

    void start() {
        System.out.println("\nPlayer " + player.getName() + " has started their turn.");
        player.enable();

        this.endTurn();
    }

    void endTurn() {
        player.disable();
        System.out.println("Player " + player.getName() + " has ended their turn.");


        if (checkForSkip()) {
            round.incrementSkipCounter();
        } else {
            round.resetSkipCounter();
        }

        round.nextTurn();
    }

    boolean checkForSkip() {
        /* TODO: check if current player skipped (aka. cards stayed in their respective components) */
        return false;
    }

}
