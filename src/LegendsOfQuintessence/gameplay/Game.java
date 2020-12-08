package LegendsOfQuintessence.gameplay;

import java.lang.Math;
import LegendsOfQuintessence.player.Player;
import de.lessvoid.nifty.Nifty;

public class Game {
    Nifty nifty;
    Player player1;
    Player player2;

    Round round;            // the current round
    Integer round_number;   // the current round number

    // in any given round, a player is either be attacking or defending
    private Player attack_player;
    private Player defend_player;

    public Game(Nifty n, Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        System.out.printf("Players" +
                " %s and  %s have created a new game.\n", player1.getName(), player2.getName());
        
        this.nifty = n;
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
        startRound();
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
                startRound();
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

    
    public void startRound() {
        round = new Round(attack_player, defend_player, this);
        round.startRound();
    }
    
    /* ----------------- Getters and Setters --------------------- */

    public Round getRound() {return round; }
    public Integer getRoundNumber() { return round_number; }
    public Nifty getNifty() {return nifty;}
}
