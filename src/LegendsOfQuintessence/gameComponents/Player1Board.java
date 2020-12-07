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

        width = "460px";
        height = "180px";
        xPos = "20px";
        yPos = "10px";
        color = "#9c9595"; //gray
        componentName = "player 1 board";
        numAnchors = 5;
        cardAnchorLayout = new CardSlot("20%");
        
        
        
    }
}
