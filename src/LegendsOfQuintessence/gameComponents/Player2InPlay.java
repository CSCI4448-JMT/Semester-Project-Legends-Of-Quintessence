/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import LegendsOfQuintessence.card.CardSlot;

/**
 *
 * @author Jenn1
 */
public class Player2InPlay extends AbstractGameComponent {
    
    public Player2InPlay() { 
        width = "460px";
        height = "175px";
        xPos = "20px";
        yPos = "400px";
        color = "#0a8c15"; //dark green
        componentName = "player 2 inPlay";
        numAnchors = 5;
        cardAnchorLayout = new CardSlot("20%");
        
    }
    
}
