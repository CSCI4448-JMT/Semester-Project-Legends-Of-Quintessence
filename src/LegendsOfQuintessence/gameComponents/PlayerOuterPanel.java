/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author timothylenahan
 */
public class PlayerOuterPanel extends AbstractGameComponent implements CompositeComponentBuilder {
    private AbstractGameComponent board;
    
    public PlayerOuterPanel(AbstractGameComponent b) {
        board = b;
    }
    
    @Override
    public PanelBuilder build() {
        return new PanelBuilder() {{
          width("100%");
          height("50%");
          childLayoutHorizontal();
          
          panel(board.gameComponentBuilder());
            
        }};
    }
    
    @Override
    public PanelBuilder gameComponentBuilder() {
        return this.build();
    }
    
    @Override
    public void attachFilters(Screen s, String filterId) {
//        deck.attachFilters(s, filterId);
        board.attachFilters(s, filterId);
    }
}
