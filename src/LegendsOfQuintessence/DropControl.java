/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResult;
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

    private List<Spatial> droppables;
    private Vector3f start_pos;
    private Vector3f final_pos;
    
    private boolean animated = true;
    private boolean snapback = true;    


    DropControl(DragControlManager dc) {
        dragControlManager = dc;
        droppables = new ArrayList();
    }
    
    // the update loop - when enabled, move the spatial to the final position
    @Override
    protected void controlUpdate(float tpf) {
        Vector3f current_pos = spatial.getLocalTranslation();  
        Vector3f dir = final_pos.subtract(current_pos);
        
        if (dir.length() < 0.01f || !animated) {
            spatial.setLocalTranslation(final_pos);
            setEnabled(false);
        } else {
            spatial.setLocalTranslation(current_pos.add(dir.normalizeLocal().mult(20*tpf)));
        }
    }
    
    public void snapToCursor() {
        start_pos = spatial.getLocalTranslation().clone();
    }
    
    public void unsnapFromCursor() {
        Vector3f location = spatial.getLocalTranslation();
        spatial.setLocalTranslation(location.getX(), location.getY(), 0);
        CollisionResults results = new CollisionResults();
        
        for (Spatial container : droppables) {    
            CollisionResults local_results = new CollisionResults();
            
            container.collideWith((BoundingBox) spatial.getWorldBound(), local_results);
            if (local_results.size() > 0) {
                CollisionResult result = local_results.getClosestCollision();
                results.addCollision(result);
            }
        } 
        
        if (results.size() > 0) {
            final_pos = results.getClosestCollision().getGeometry().getLocalTranslation();
        } else if (snapback) {
            final_pos = start_pos;
        } else {
            final_pos = spatial.getLocalTranslation();
        }
        setEnabled(true);
    }
    
    // add Spatial that the dragged item can snap to
    public void addDroppable(Spatial item) {
        droppables.add(item);
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
