/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.CardSlot;

/**
 *
 * @author JMT
 */
public class Player1Board extends AbstractGameComponent {
    
    public Player1Board() {
        width = "50%";
        height = "100%";
        componentName = "player 1 board";
        numAnchors = 5;
        cardAnchorLayout = new CardSlot("20%");
        backgroundImage = "Textures/cartoon_wood_texture.jpg";
    }
}
