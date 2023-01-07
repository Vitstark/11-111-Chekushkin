package com.example.client.components;

import com.almasb.fxgl.dsl.components.view.ChildViewComponent;

import static com.almasb.fxgl.dsl.FXGL.texture;

/**
 * @author Vitaly Chekushkin
 */
public class PlayerArrowViewComponent extends ChildViewComponent {

    public PlayerArrowViewComponent() {
        super(24, -48, false);

        var arrow = texture("me_arrow.png", 16, 16);

        getViewRoot().getChildren().addAll(arrow);
    }
}
