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
public class PlayerBase extends AbstractGameComponent {
    
    public PlayerBase() {
        width = "100";
        height = "100%";
        componentName = "player 1 board";
        numAnchors = 0;
        backgroundImage = "Textures/heart-stone.png";
    }
    
}
