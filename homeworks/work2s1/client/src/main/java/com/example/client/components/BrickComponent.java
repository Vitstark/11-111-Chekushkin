package com.example.client.components;

import com.almasb.fxgl.entity.component.Component;

/**
 * @author Vitaly Chekushkin
 */
public class BrickComponent extends Component {

    private static final int MAX_HP = 3;
    private int hp = MAX_HP;

    public void onHit() {
        if (hp <= 0)
            return;

        hp--;

        if (hp >= 1) {
            entity.getViewComponent().setOpacity(1.0 * hp / MAX_HP);
        } else {
            entity.removeFromWorld();
        }
    }
}
