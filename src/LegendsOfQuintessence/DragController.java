/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Rashed
 */
public class DragController {
    private InputManager inputManager;
    private Camera cam;
    private Node rootNode;
    
    private Node draggables; 
    private Spatial dragged_item;
    
    
    DragController(InputManager im, Camera cam, Node rn) {
        this.inputManager = im;
        this.cam = cam;
        this.rootNode = rn;
        this.draggables = new Node("Draggables");
        
        rootNode.attachChild(draggables);
    }
    
    public void registerDraggable(Spatial item) {
        // make item child of draggable node
        item.removeFromParent();
        draggables.attachChild(item);
    }
    
    public void removeDraggable(Spatial item) {
        // make item child of draggable node
        item.removeFromParent();
        rootNode.attachChild(item);
    }
    
    public void drag() {
        CollisionResults results = new CollisionResults();

        Vector2f click2d = inputManager.getCursorPosition().clone();
        Vector3f click3d = cam.getWorldCoordinates(click2d, 0f).clone();
        Vector3f dir = cam.getWorldCoordinates(
            click2d, 1f).subtractLocal(click3d).normalizeLocal();

        Ray ray = new Ray(click3d, dir);
        draggables.collideWith(ray, results);

        // get closest spatial and snap to cursor
        if (results.size() > 0) {
            CollisionResult closest = results.getClosestCollision();
            snapToCursor(closest.getGeometry());
        }
    }
    
    public void drop() {
        unsnapFromCursor();
    }
    
    // --------- GETTERS & SETTERS ------------ //
    
    public InputManager getInputManager() {
        return inputManager;
    }
    
    public Camera getCamera() {
        return cam;
    }
    
    
    // ----------- HELPER METHODS ------------- //
    
    protected void snapToCursor(Spatial item) {
        dragged_item = item;        
        dragged_item.move(0,0,1);
        
        dragged_item.getControl(DraggableControl.class).setEnabled(true);
    }
    
    protected void unsnapFromCursor() {
        if (dragged_item != null) {
            dragged_item.move(0,0,-1);
            dragged_item.getControl(DraggableControl.class).setEnabled(false);
        }
        dragged_item = null;
    }
    
}
