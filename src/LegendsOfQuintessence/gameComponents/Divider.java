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
public class Divider extends AbstractGameComponent {
    public Divider() {
        width = "1100px";
        height = "6px";
        xPos = "0px";
        yPos = "385px";
        color = "#0a0a0a"; //black line
        componentName = "divider";
        numAnchors = 0;
        //cardAnchorLayout = new CardSlot("20%");
    }
    
}
