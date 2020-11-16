/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

/**
 * 
 * @author JMT
 */
public class DragDropControl extends AbstractControl {
    DragControlManager dragControlManager;
    DragControl dragControl;
    DropControl dropControl;
    
    Spatial spatial;
    
    DragDropControl(DragControlManager dc) {
        dragControlManager = dc;
        dragControl = new DragControl(dc);
        dropControl = new DropControl(dc);
    }

    public boolean isDraggable() {
        return dragControl.isDraggable();
    }
    
    public void setDraggable(boolean bool) {
        dragControl.setDraggable(bool);
    }
    
    public void addDropContainer(DropContainer container) {
        dropControl.addDropContainer(container);
    }
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        
        if (spatial != null) { 
            dragControlManager.register(this);
        } else {
            dragControlManager.remove(this);
        }
        
        dragControl.setSpatial(spatial);
        dropControl.setSpatial(spatial);
    }
    
    
    public void setDragControl(DragControl dc) {
        dragControl.setSpatial(null);
        dragControl = dc;
        dragControl.setSpatial(spatial);
    }
    
    public void setDropControl(DropControl dc) {
        dropControl.setSpatial(null);
        dropControl = dc;
        dropControl.setSpatial(spatial);
    }

    public void snapToCursor() {
        dragControl.snapToCursor();
        dropControl.snapToCursor();
    }
    
    public void unsnapFromCursor() {
        dropControl.unsnapFromCursor();
        dragControl.unsnapFromCursor();
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        dragControl.update(tpf);
        dropControl.update(tpf);
    }

    // IGNORE.
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
    
}