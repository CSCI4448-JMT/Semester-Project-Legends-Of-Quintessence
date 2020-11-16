/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JMT
 * @param <T>
 */
public class DropContainer extends Node {
    private Spatial item;             // item currently in the container
    private Spatial collisionSpatial; // detect drops using a collision with this
    private Boolean vacant;           // is container vacant
    
    DropContainer(Spatial s) {
        super();
        item = null;
        collisionSpatial = s;
        vacant = true;
        this.attachChild(s);
    }
    
    
    public void addItem(Spatial item) {
        this.item = item;
        vacant = false;
    }
    
    public void removeItem() {
        this.item = null;
        vacant = true;
    }
    
    public Spatial getItem() {
        return item;
    }
    
    public Spatial getCollisionSpatial() {
        return collisionSpatial;
    }    
    
    public Vector3f getPosition() {
        return collisionSpatial.getLocalTranslation();
    }
    
    public boolean isVacant() {
        return vacant;
    }
}
