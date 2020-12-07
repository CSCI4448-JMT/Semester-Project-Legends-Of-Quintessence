/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.CardSlot;
/**
 *
 * @author timothylenahan
 */
public class Player2Board extends AbstractGameComponent {
    
    public Player2Board() { 
        width = "460px";
        height = "175px";
        xPos = "20px";
        yPos = "590px";
        color = "#9c9595"; //gray
        componentName = "player 2 board";
        numAnchors = 5;
        cardAnchorLayout = new CardSlot("20%");
        
    }
    
}
