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
 * @author JMT
 */
public class Player2 extends Player{
    private int baseHealth;
    private int resources;
    private List<AbstractGameComponent> components;
    
    public Player2() {
        elementBaseName = "Player2";
        name = "Player2";
    }
    
    public Player2(String n) {
        elementBaseName = "Player2";
        name = n;
    }
}
