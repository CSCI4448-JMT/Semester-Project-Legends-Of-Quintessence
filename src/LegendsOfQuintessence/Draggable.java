/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.scene.Spatial;

/**
 *
 * @author Rashed
 */
public interface Draggable {
    void setDraggable(boolean b);
    void setUseSnapBack(boolean b);
    void setUseLockToDrop(boolean b);
    
    void addDropSpatial(Spatial s);
}
