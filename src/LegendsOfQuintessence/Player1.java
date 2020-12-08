/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import LegendsOfQuintessence.gameComponents.AbstractGameComponent;
import java.util.List;

/**
 *
 * @author timothylenahan
 */
public class Player1 extends PlayerElements {
    private int baseHealth;
    private int resources;
    private List<AbstractGameComponent> components;
    
    public Player1() {
        elementBaseName = "Player1";
        name = "Player1";
    }
    
    public Player1(String n) {
        elementBaseName = "Player1";
        name = n;
    }
}
