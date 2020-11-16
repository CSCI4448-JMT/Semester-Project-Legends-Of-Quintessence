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
import com.jme3.scene.control.AbstractControl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rashed
 */
public class DropControl extends AbstractControl {

    DragControlManager dragControlManager;
    List<Spatial> droppables;
    private Vector3f final_location;

    DropControl(DragControlManager dc) {
        dragControlManager = dc;
        droppables = new ArrayList();
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        animateMoveTo(final_location, tpf);
    }

    
    private void animateMoveTo(Vector3f new_location, float tpf) {
        Vector3f current_location = spatial.getLocalTranslation();  
        Vector3f dir = new_location.subtract(current_location);
        
        if (dir.length() < 0.001f) {
            spatial.setLocalTranslation(new_location);
            setEnabled(false);
        } else {
            spatial.setLocalTranslation(current_location.add(dir.normalizeLocal().mult(10*tpf)));
        }
    }
    
    // set Spatial to snap to one of the desired Spatials upon collision
    public void snapToDroppable() {
        for (Spatial item : droppables) {
            CollisionResults results = new CollisionResults();
            spatial.collideWith((BoundingBox) item.getWorldBound(), results);

            if (results.size() > 0) {
                Vector3f location = item.getLocalTranslation();
                final_location = location;
                setEnabled(true);
                
                // ?? notify droppable
                
                break;
            }
        }
    }
    
    // add Spatial that the dragged item can snap to
    public void addDroppable(Spatial item) {
        droppables.add(item);
    }
    
    public List<Spatial> getDroppables() {
        return droppables;
    }
   
    
    // IGNORE. Method for advanced users.
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
    
}
