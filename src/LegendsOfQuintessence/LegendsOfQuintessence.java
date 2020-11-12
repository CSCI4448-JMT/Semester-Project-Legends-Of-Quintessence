package LegendsOfQuintessence;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class LegendsOfQuintessence extends SimpleApplication {

    public static void main(String[] args) {
        LegendsOfQuintessence app = new LegendsOfQuintessence();
        app.start();
    }

    private Node draggables;
    
    @Override
    public void simpleInitApp() {
        initKeys();
        
        cam.setLocation(new Vector3f(0f, 0f, 30f));
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1,0,-2).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.5f));
        rootNode.addLight(al);
        
        Box b = new Box(2, 3, 0.5f);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        ColorRGBA color = new ColorRGBA(0.0f, 0.6f, 0.8f,1);
        
        mat.setColor("Ambient", color);
        mat.setColor("Diffuse", color);
        geom.setMaterial(mat);

        draggables = new Node("Draggables");
        rootNode.attachChild(draggables);
        draggables.attachChild(geom);
    }


    private void initKeys() {
        flyCam.setEnabled(false);            // turn off fly cam (issue with drag and drop)
        inputManager.setCursorVisible(true); // show cursor
        
        inputManager.addMapping("LeftClick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "LeftClick");
    }
 

    private ActionListener actionListener = new ActionListener() {
        
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("LeftClick") && keyPressed) {
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
                
            } else if (name.equals("LeftClick") && !keyPressed) {
                unsnapFromCursor();
            }
          
        }
    };
    
    
    protected Spatial snapped_item = null;
    protected void snapToCursor(Spatial item) {
        snapped_item = item;        
        snapped_item.move(0,0,1);
    }
    protected void unsnapFromCursor() {
        if (snapped_item != null) {
            snapped_item.move(0,0,-1);
        }
        snapped_item = null;
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        
        if (snapped_item != null) {
            Vector3f item_location = snapped_item.getLocalTranslation().subtract(cam.getLocation());
            Vector3f projection = item_location.project(cam.getDirection());//.normalizeLocal());
            float z_view = cam.getViewToProjectionZ(projection.length());

            Vector2f click2d = inputManager.getCursorPosition().clone();  
            Vector3f click3d = cam.getWorldCoordinates(click2d, z_view);

            snapped_item.setLocalTranslation(click3d);
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
