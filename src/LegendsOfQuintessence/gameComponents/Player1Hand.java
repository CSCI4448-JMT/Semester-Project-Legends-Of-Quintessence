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
public class Player1Hand extends AbstractGameComponent {
    public Player1Hand() {
        width = "460px";
        height = "175px";
        xPos = "550px";
        yPos = "10px";
        color = "#d0ccff"; //light blue
        componentName = "player 1 hand";
        numAnchors = 5;
        cardAnchorLayout = new CardSlot("20%");
    }
    
}
