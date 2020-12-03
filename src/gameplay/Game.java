package gameplay;

import java.lang.Math;
import player.Player;

public class Game {
    // players of the game
    Player player1;
    Player player2;

    // current round in the game
    Round round;
    Integer round_number;

    // in any given round, a player is either be attacking or defending
    Player attack_player;
    Player defend_player;

    public Game(Player player1, Player player2) {
        System.out.printf("Players" +
                " %s and  %s have created a new game.\n", player1.getName(), player2.getName());

        this.player1 = player1;
        this.player2 = player2;

        this.player1.setGame(this);
        this.player2.setGame(this);
    }

    public void endRoundRequest() {round.endRoundRequest();}
    public void endTurnRequest()  {round.endTurnRequest();}

    public void start() {
        System.out.printf("Player %s and %s have started the game.\n", player1.getName(), player2.getName());

        // choose attacking player randomly at start of game
        if (Math.random() < 0.5) {
            attack_player = player1;
            defend_player = player2;
        } else {
            attack_player = player2;
            defend_player = player1;
        }
        System.out.println("Player " + attack_player.getName() +  " has been randomly chosen as the attacker.");

        // set round number to 1
        round_number = 1;

        // start a new round
        round = new Round(attack_player, defend_player, this);
        round.startRound();
    }

    /* --------------- PACKAGE FUNCTIONS ------------------ */

    void nextRound() {
        // if the game is over, end the game
        if (checkGameEnd()) {
            endGame();
        } else {
            // switch player roles
            Player temp_player = attack_player;
            attack_player = defend_player;
            defend_player = temp_player;

            // update the round_number
            round_number = round_number + 1;

            // start a new round

            // TODO : remove bound on number of rounds
            if (round_number < 5) {
                round = new Round(attack_player, defend_player, this);
                round.startRound();
            } else {
                endGame();
            }
        }
    }

     void endGame() {
        String end_text;

        if (player1.getBaseHealth() == 0 && player2.getBaseHealth() == 0){
            end_text = "The game was a tie!";
        } else if (player2.getBaseHealth() == 0) {
            end_text = "Player " + player1.getName() + " won the game!";
        } else if (player1.getBaseHealth() == 0) {
            end_text = "Player " + player2.getName() + " won the game!";
        } else {
            end_text = "No one won that game.";
        }
        System.out.println("\n" + end_text);

        // TODO: final popup message for end of game
    }

    private boolean checkGameEnd() {
        return player1.getBaseHealth() == 0 || player2.getBaseHealth() == 0;
    }

    /* ----------------- Getters and Setters --------------------- */

    public Round getRound() {return round; }
    public Integer getRoundNumber() { return round_number; }

}
