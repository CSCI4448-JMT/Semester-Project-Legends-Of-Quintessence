/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

/**
 * 
 * @author JMT
 */
public class DragDropControl {
    DragControlManager dragControlManager;
    DragControl dragControl;
    DropControl dropControl;
    
    Spatial spatial;
    
    DragDropControl(DragControlManager dc) {
        dragControlManager = dc;
        dragControl = new DragControl(dc);
        dropControl = new DropControl(dc);
    }
    
    public void setDraggable(boolean bool) {
        dragControl.setDraggable(bool);
    }
    
    public void addDroppable(Spatial item) {
        dropControl.addDroppable(item);
    }
    
    public void setSpatial(Spatial spatial) {
        if (spatial != null) { 
            spatial.addControl(dragControl);
            spatial.addControl(dropControl);
            
            dragControlManager.register(this);
        } else {
            dragControlManager.remove(this);
        }
    }
    
    public Spatial getSpatial() {
        return spatial;
    }
    
}
