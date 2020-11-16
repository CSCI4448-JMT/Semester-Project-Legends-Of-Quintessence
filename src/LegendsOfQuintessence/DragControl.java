/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import java.util.ArrayList;
import java.util.List;

/** A custom control that can be used for draggability.
 *  Usage:
 *      - construct new control with reference to a DragControlManager
 *      - add control to any Spatial, and call the following methods;
 *          (1) setDraggable(boolean) to turn on or turn off draggability for the spatial
 *          (2) addDroppable(Spatial) to add Spatials that the spatial can snap to upon collision
 *          (3) ...
 */
public class DragControl extends AbstractControl {

    DragControlManager dragControlManager;
    
    private boolean draggable = true;   // whether spatial can be dragged by cursor
    private boolean drag = false;       // whether spatial is being dragged
    
    public DragControl(DragControlManager dc){
        dragControlManager = dc;
    }
   
    // the update loop - when enabled, update the position of spatial to track mouse cursor
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
    
    // the command to make the spatial track the mouse cursor
    public void snapToCursor() {
        // make spatial pop out
        Vector3f location = spatial.getLocalTranslation();        
        spatial.setLocalTranslation(location.getX(), location.getY(), 2); 
        
        // enable update loop
        setEnabled(true);
    }
    
    // the command to make the spatial stop following the cursor
    public void unsnapFromCursor() {
        setEnabled(false);
    }
    
    
    // ------------- SETTERS / GETTERS -------------- //
    
    
    // turn on (or off) draggability of the spatial
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














































