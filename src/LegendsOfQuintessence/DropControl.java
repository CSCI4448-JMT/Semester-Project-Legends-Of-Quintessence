/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rashed
 */
public class DropControl extends AbstractControl {

    private DragControlManager dragControlManager;
    private Node droppables;
    private Vector3f start_pos;
    private Vector3f final_pos;

    DropControl(DragControlManager dc) {
        dragControlManager = dc;
        droppables = new Node();
        dragControlManager.getRootNode().attachChild(droppables);
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        animateMoveTo(final_pos, tpf);
    }
    
    public void unsnapFromCursor() {
        Vector3f location = spatial.getLocalTranslation();
        spatial.setLocalTranslation(location.getX(), location.getY(), 0);
            
        snapToDroppable();
    }
    
    // add Spatial that the dragged item can snap to
    public void addDroppable(Spatial item) {
        item.removeFromParent();
        droppables.attachChild(item);
    }

    public void setStartPos(Vector3f pos) {
        start_pos = pos;
    }
    
    // ----------------------- HELPER FUNCTIONS ------------------------------
    
    // set Spatial to snap to one of the desired Spatials upon collision
    private void snapToDroppable() {
        CollisionResults results = new CollisionResults();
        droppables.collideWith((BoundingBox) spatial.getWorldBound(), results);

        if (results.size() > 0) {
            final_pos = results.getClosestCollision().getGeometry().getLocalTranslation();
            start_pos = final_pos;
        } else {
            System.out.println("No collision.");
            final_pos = start_pos;
        }
        setEnabled(true);
    }
    
    private void animateMoveTo(Vector3f new_location, float tpf) {
        Vector3f current_location = spatial.getLocalTranslation();  
        Vector3f dir = new_location.subtract(current_location);
        
        if (dir.length() < 0.01f) {
            spatial.setLocalTranslation(new_location);
            setEnabled(false);
        } else {
            spatial.setLocalTranslation(current_location.add(dir.normalizeLocal().mult(20*tpf)));
        }
    }

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        
        if (spatial != null) { 
            start_pos = spatial.getLocalTranslation().clone();
        }
    }
    
    
    // IGNORE. Method for advanced users.
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
    
}
