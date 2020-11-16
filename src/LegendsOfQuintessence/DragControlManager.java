/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.bounding.BoundingBox;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Manages drag controls (and associated spatials), and global drag and drop functionality.
 *  Usage:
 *      - Add new DragControlManager to Game app (after that, NO need to call any methods on manager).
 *      - Construct new DragDropControl (with reference to the manager) and set it to any Spatial.
 *      - Spatial will be draggable, and DragDropControl methods can be used for customization/restriction.
 */
public class DragControlManager {
    private InputManager inputManager;  // cursor info 
    private Camera cam;                 // camera info
    private Node rootNode;              // parent node (usually root node of game)
    
    private Map<DragDropControl,Spatial> controls;  // map of registered DragDropControls to Spatials
    private Node draggables;                        // parent node to hold all draggable Spatials as children
    
    private Spatial dragged_spatial;    // spatial currently being dragged by cursor
    
    DragControlManager(InputManager im, Camera cam, Node rn) {
        this.inputManager = im;
        this.cam = cam;
        this.rootNode = rn;
        
        this.controls = new HashMap();
        this.draggables = new Node("Draggables");
        this.rootNode.attachChild(draggables);
        
        // setup for click detection
        ActionListener clickListener = new ActionListener() {
            @Override
            public void onAction(String name, boolean keyPressed, float tpf) {
                if (keyPressed) {
                    drag();
                } else if (!keyPressed) {
                    drop();
                }
            }
        };
        inputManager.addMapping("LeftClick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(clickListener, "LeftClick");
    }
    
    public void register(DragDropControl control) {
        controls.put(control, control.getSpatial());
        addDraggable(control.getSpatial());
    }
    
    public void remove(DragDropControl control) {
        removeDraggable(controls.get(control));
        controls.remove(control);
    }
    
    // --------------------- HELPER METHODS ---------------------- //
    
    private void drag() {
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
            
            if (closest.getGeometry().getControl(DragControl.class).isDraggable()) {
                dragged_spatial = closest.getGeometry();
                dragged_spatial.getControl(DragControl.class).snapToCursor();
            }
        }
    }
    
    private void drop() {
        if (dragged_spatial != null) {
            dragged_spatial.getControl(DragControl.class).unsnapFromCursor();
            dragged_spatial.getControl(DropControl.class).unsnapFromCursor();
        }
        dragged_spatial = null;
    }
    
    // set Spatial as child of draggable node
    private void addDraggable(Spatial item) {
        item.removeFromParent();
        draggables.attachChild(item);
    }
    
    // set Spatial as child of root node
    private void removeDraggable(Spatial item) {
        item.removeFromParent();
        rootNode.attachChild(item);
    }
    
    
    // --------- GETTERS & SETTERS --------- //
    
    public InputManager getInputManager() {
        return inputManager;
    }
    
    public Camera getCamera() {
        return cam;
    }
    
    public Node getRootNode() {
        return rootNode;
    }
}
