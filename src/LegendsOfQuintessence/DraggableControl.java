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

/**
 *
 * @author Rashed
 */
public class DraggableControl extends AbstractControl implements Draggable {

    DragController draggableController;
    InputManager inputManager;
    Camera cam;
    
    private boolean draggable = true;
    private boolean useSnapBack = true;
    private boolean lockToDrop = true;
    
    
    public DraggableControl(DragController dc){
        draggableController = dc;
        inputManager = dc.getInputManager();
        cam = dc.getCamera();
    }
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
     
        setEnabled(false);
        setDraggable(true);
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        Vector3f item_location = spatial.getLocalTranslation().subtract(cam.getLocation());
        Vector3f projection = item_location.project(cam.getDirection());
        float z_view = cam.getViewToProjectionZ(projection.length());

        Vector2f click2d = inputManager.getCursorPosition().clone();  
        Vector3f click3d = cam.getWorldCoordinates(click2d, z_view);

        spatial.setLocalTranslation(click3d);
    }

    // set whether spatial is draggable (register it with draggableController)
    @Override
    public void setDraggable(boolean b) {
        if (b) { 
            draggableController.registerDraggable(spatial);
        }
        else {
            draggableController.removeDraggable(spatial);
        }
    }

    //
    @Override
    public void setUseSnapBack(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUseLockToDrop(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDropSpatial(Spatial s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) { }
    
}
