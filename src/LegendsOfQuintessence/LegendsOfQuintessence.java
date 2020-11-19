package LegendsOfQuintessence;


import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
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
import java.util.ArrayList;
import java.util.List;

import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class LegendsOfQuintessence extends SimpleApplication {
    
    private Nifty nifty;
    private StartScreen startScreen = new StartScreen();
    
    public static void main(String[] args) {
        //Needed to change the Window Title to LoQ & get rid of the JMonkey pop-up
        AppSettings settings = new AppSettings(true);
        settings.setTitle("Legends Of Quintessence");
        
        LegendsOfQuintessence app = new LegendsOfQuintessence();
        app.setShowSettings(false); //Needed to change the Window Title to LoQ & get rid of the JMonkey pop-up
        app.setSettings(settings); //Needed to change the Window Title to LoQ
        app.start();
    }
            
    protected Spatial card;
    protected Spatial card2;
    protected Spatial card3;
    
    //@Override
    public void simpleInitApp() {
        initScene();
        
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(this.assetManager, this.inputManager, this.audioRenderer, this.guiViewPort);
        this.nifty = niftyDisplay.getNifty();
        this.nifty.fromXml("Interface/StartScreen.xml", "start", new ScreenController[]{this.startScreen});
        this.startScreen.setGameState(new GameState());
        this.guiViewPort.addProcessor(niftyDisplay);
        this.inputManager.setCursorVisible(true);
        this.flyCam.setEnabled(false); //enables mouse movement on screen
        this.setDisplayStatView(false); //gets rid of StatView box
        this.setDisplayFps(false); //gets rid of FPS onscreen
       
        DragControlManager dragController = new DragControlManager(this.inputManager, this.cam, this.rootNode);
       
        List<DropContainer> board_row = makeRow(0);
        List<DropContainer> field_row = makeRow(7);
                
        card = makeCard(0,-7,0);
        card2 = makeCard(5,-7,0);
        card3 = makeCard(-5,-7,0);
        
        DragDropControl c = new DragDropControl(dragController);
        c.addDropContainers(board_row);
        c.addDropContainers(field_row);
        //c.removeDropContainers();
        //c.addDropContainers(board_row);
        
        DragDropControl c2 = c.clone();
        DragDropControl c3 = c.clone();
        
        card.addControl(c);
        card2.addControl(c2);
        card3.addControl(c3);
    }

    private void initScene() {
        viewPort.setBackgroundColor(ColorRGBA.White);
        //flyCam.setEnabled(false);            // turn off fly cam (issue with drag and drop)
        //inputManager.setCursorVisible(true); // show cursor
        
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
        rootNode.attachChild(geom);
        
        return geom;
    }
    
    // USE FOR TESTING ONLY
    private List<DropContainer> makeRow (float y) {
        float width = 5;
        
        ArrayList slots = new ArrayList(); 
        
        for(int i = -2; i <=2; i++) {
            DropContainer slot = makeSlot(i * width, y, 0);
            slots.add(slot);
        }
        
        return slots;
    }
    
    // USE FOR TESTING ONLY
    private DropContainer makeSlot(float x, float y, float z) {
        Box b = new Box(2, 3, 0.01f);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        ColorRGBA color = ColorRGBA.Gray;
        
        mat.setColor("Ambient", color);
        mat.setColor("Diffuse", color);
        geom.setMaterial(mat);
        
        geom.setLocalTranslation(x,y,z);
        
        DropContainer dc = new DropContainer(geom);
        rootNode.attachChild(geom);
        
        return dc;
    }
    
    
    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
