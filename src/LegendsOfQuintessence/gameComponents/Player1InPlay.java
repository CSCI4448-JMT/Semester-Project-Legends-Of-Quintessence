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
public class Player1InPlay extends AbstractGameComponent {
    public Player1InPlay() {
        width = "460px";
        height = "175px";
        xPos = "20px";
        yPos = "200px";
        color = "#3223db"; //dark blue
        componentName = "player 1 inPlay";
        numAnchors = 5;
        cardAnchorLayout = new CardSlot("20%");
    }
    
}
