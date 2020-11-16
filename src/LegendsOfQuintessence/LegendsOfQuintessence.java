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
            
    protected Spatial card;

    @Override
    public void simpleInitApp() {
        initScene();
       
        DragControlManager dragController = new DragControlManager(this.inputManager, this.cam, this.rootNode);
       
        Spatial slot1 = makeSlot(6f,0,0);
        Spatial slot2 = makeSlot(0,0,0);
        Spatial slot3 = makeSlot(-6f,0,0);
        
        card = makeCard(0,-6,0);
        Node board = new Node();
                
        rootNode.attachChild(card);
        rootNode.attachChild(board);
        
        board.attachChild(slot1);
        board.attachChild(slot2);
        board.attachChild(slot3);
        
        DragDropControl c = new DragDropControl(dragController);
        c.addDroppable(board);

        c.setSpatial(card);
    }

    private void initScene() {
        viewPort.setBackgroundColor(ColorRGBA.White);
        flyCam.setEnabled(false);            // turn off fly cam (issue with drag and drop)
        inputManager.setCursorVisible(true); // show cursor
        
        cam.setLocation(new Vector3f(0f, 0f, 40f));
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1,0,-2).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.5f));
        rootNode.addLight(al);
    }
 
    // USE FOR TESTING ONLY
    private Spatial makeCard(float x, float y, float z) {
        Box b = new Box(2, 3, 0.1f);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        ColorRGBA color = ColorRGBA.randomColor();
        
        mat.setColor("Ambient", color);
        mat.setColor("Diffuse", color);
        geom.setMaterial(mat);
        
        geom.setLocalTranslation(x,y,z);
        
        return geom;
    }
    
    // USE FOR TESTING ONLY
    private Spatial makeSlot(float x, float y, float z) {
        Box b = new Box(2, 3, 0.01f);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        ColorRGBA color = ColorRGBA.Gray;
        
        mat.setColor("Ambient", color);
        mat.setColor("Diffuse", color);
        geom.setMaterial(mat);
        
        geom.setLocalTranslation(x,y,z);
        
        return geom;
    }
    
    
    
    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
