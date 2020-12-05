/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LegendsOfQuintessence.gameComponents;

import de.lessvoid.nifty.builder.PanelBuilder;

/**
 *
 * @author JMT
 */
public class PlayerMiddlePanel extends AbstractGameComponent implements CompositeComponentBuilder {
    private AbstractGameComponent field;
    private AbstractGameComponent base;
    
    public PlayerMiddlePanel(AbstractGameComponent f, AbstractGameComponent b) {
        field = f;
        base = b;
    }
    
    public PanelBuilder build() {
        
        return new PanelBuilder() {{
          width("100%");
          height("50%");
          childLayoutHorizontal();
          
          panel(new PanelBuilder() {{
              childLayoutVertical();
              alignCenter();
              width("25%");
              panel(base.gameComponentBuilder());
          }});
          
          panel(field.gameComponentBuilder());
            
        }};
        
    }
    
    @Override
    public PanelBuilder gameComponentBuilder() {
        return this.build();
    }
}
