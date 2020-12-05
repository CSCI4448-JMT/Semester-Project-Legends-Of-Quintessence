package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.CardSlot;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JMT
 */
public class Player2Board extends AbstractGameComponent {
    
    public Player2Board() { 
        width = "50%";
        height = "100%";
        componentName = "player 2 board";
        numAnchors = 5;
        cardAnchorLayout = new CardSlot("20%");
        backgroundImage = "Textures/cartoon_wood_texture.jpg";
    }
    
}
