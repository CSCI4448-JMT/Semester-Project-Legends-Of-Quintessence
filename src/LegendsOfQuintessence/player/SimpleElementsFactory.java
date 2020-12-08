/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.player;

/**
 *
 * @author JMT
 */
public class SimpleElementsFactory {
    
    public PlayerElements getPlayerElements(String id) {
        if ("1".equals(id)) {
            return new Player1();
        }
        
        return new Player2();
    }
}
