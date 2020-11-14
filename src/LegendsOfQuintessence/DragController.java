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
    
    public void update() {        
        if (dragged_item != null) {
            Vector3f item_location = dragged_item.getLocalTranslation().subtract(cam.getLocation());
            Vector3f projection = item_location.project(cam.getDirection());
            float z_view = cam.getViewToProjectionZ(projection.length());

            Vector2f click2d = inputManager.getCursorPosition().clone();  
            Vector3f click3d = cam.getWorldCoordinates(click2d, z_view);

            dragged_item.setLocalTranslation(click3d);
        }
    }
    
    public void addDraggable(Spatial item) {
        item.removeFromParent();
        draggables.attachChild(item);
    }
    
    public Spatial getDragged() {
        return this.dragged_item;
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
    
    // ----------- HELPER METHODS ------------- //
    
    protected void snapToCursor(Spatial item) {
        dragged_item = item;        
        dragged_item.move(0,0,1);
    }
    
    protected void unsnapFromCursor() {
        if (dragged_item != null) {
            dragged_item.move(0,0,-1);
        }
        dragged_item = null;
    }
    
}
