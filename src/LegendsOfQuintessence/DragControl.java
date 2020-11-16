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
    
    List<Spatial> droppables;
    
    private boolean draggable = true;   // spatial can be dragged by cursor
    private boolean drag = false;       // spatial is being dragged
    private boolean drop = false;       // spatial has dropped
    
    private Vector3f final_location; // spatial's final location
    
    public DragControl(DragControlManager dc){
        dragControlManager = dc;
        droppables = new ArrayList();
    }
   
    /* controlUpdate() is 
        - called by the main game update loop on every control,
        - will update position of spatial currently being dragged by cursor,
        - is activated and deactivated by the DragControlManager (using setEnabled()).
    */
    @Override
    protected void controlUpdate(float tpf) {
        if (drag) {
            InputManager inputManager = dragControlManager.getInputManager();
            Camera cam = dragControlManager.getCamera();

            Vector3f item_location = spatial.getLocalTranslation().subtract(cam.getLocation());
            Vector3f projection = item_location.project(cam.getDirection());
            float z_view = cam.getViewToProjectionZ(projection.length());

            Vector2f click2d = inputManager.getCursorPosition().clone();  
            Vector3f click3d = cam.getWorldCoordinates(click2d, z_view);

            spatial.setLocalTranslation(click3d);
        }
        
        if (drop) {
            animateMoveTo(final_location, tpf);
        }
    }

    
    public void animateMoveTo(Vector3f new_location, float tpf) {
        Vector3f current_location = spatial.getLocalTranslation();  
        Vector3f dir = new_location.subtract(current_location);
        
        if (dir.length() < 0.001f) {
            spatial.setLocalTranslation(new_location);
            drop = false;
        } else {
            spatial.setLocalTranslation(current_location.add(dir.normalizeLocal().mult(10*tpf)));
        }
    }
    
    // set Spatial to snap to one of the desired Spatials upon collision
    public void snapToDroppable() {
        List<Spatial> droppables = spatial.getControl(DragControl.class).getDroppables();

        for (Spatial item : droppables) {
            CollisionResults results = new CollisionResults();
            spatial.collideWith((BoundingBox) item.getWorldBound(), results);

            if (results.size() > 0) {
                Vector3f location = item.getLocalTranslation();
                final_location = location;
                drop = true;
                
                // ?? notify droppable
                
                break;
            }
        }
    }

    // ------------- SETTERS / GETTERS -------------- //
    
    
    // turn on (or off) draggability of the spatial
    public void setDraggable(boolean b) {
        draggable = b;
    }
    
    public boolean isDraggable() {
        return draggable;
    }
    
    // add Spatial that the dragged item can snap to
    public void addDroppable(Spatial item) {
        droppables.add(item);
    }
    
    public List<Spatial> getDroppables() {
        return droppables;
    }
    
    public Spatial getSpatial() {
        return spatial;
    }
    
    public void setDrag(boolean b) {
        drag = b;
    }

    
    // do NOT call setSpatial (game engine will do this)
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
     
        if (spatial != null) { 
            dragControlManager.register(this);
        } else {
            dragControlManager.remove(this);
        }
    }
    
    // --------- IGNORE. Method for advanced users. ---------------
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
}
