package player;

import gameplay.Game;

/*
*  */
public class PlayerStateControl {
    private Game game;
    
    private Player player;
    private Player current_player;
    
    public PlayerStateControl(Player player) {
        this.player = player;
    }
    
    private Player attack_player;
    private Player defend_player;
    private boolean attack_occurred;

    private boolean play_action_state;          // can put cards into play
    private boolean select_action_state;        // can select card from hand
    private boolean end_round_action_state;     // can end the round

    /* TODO: make sure these are necessary */
    private boolean skip_action_state;          // ????
    private boolean skip_defense_action_state;  // ????
    
    public void update() {
        attack_occurred = game.getRound().combat_started;
        current_player = game.getRound().current_player;
        
        
    }

}
