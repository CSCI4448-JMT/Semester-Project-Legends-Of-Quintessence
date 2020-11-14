/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.input.InputManager;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

/** A custom control that can be used for draggability.
 *  Usage:
 *      - construct new control with reference to a DragControlManager
 *      - add control to any Spatial, and call the following methods;
 *          - setDraggable(boolean) to turn on or turn off draggability
 *          - MORE FUNCTIONALITY TO COME...
 */
public class DragControl extends AbstractControl {

    DragControlManager dragControlManager;
    private boolean draggable = true;
    
 
    public DragControl(DragControlManager dc){
        dragControlManager = dc;
    }
   
    /* controlUpdate() is 
        - called by the main game update loop on every control,
        - will update position of spatial currently being dragged by cursor,
        - is activated and deactivated by the DragControlManager (using setEnabled()).
    */
    @Override
    protected void controlUpdate(float tpf) {
        InputManager inputManager = dragControlManager.getInputManager();
        Camera cam = dragControlManager.getCamera();
        
        Vector3f item_location = spatial.getLocalTranslation().subtract(cam.getLocation());
        Vector3f projection = item_location.project(cam.getDirection());
        float z_view = cam.getViewToProjectionZ(projection.length());

        Vector2f click2d = inputManager.getCursorPosition().clone();  
        Vector3f click3d = cam.getWorldCoordinates(click2d, z_view);

        spatial.setLocalTranslation(click3d);
    }

    // ---------- SETTERS / GETTERS ------------
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
     
        if (spatial != null) { 
            dragControlManager.register(this);
        } else {
            dragControlManager.remove(this);
        }
    }
    
    public Spatial getSpatial() {
        return spatial;
    }
    
    public void setDraggable(boolean b) {
        draggable = b;
    }
    
    public boolean isDraggable() {
        return draggable;
    }
    
    
    // --------- IGNORE. Method for advanced users. ---------------
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
}
