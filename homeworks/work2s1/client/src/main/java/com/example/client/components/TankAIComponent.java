package com.example.client.components;

import java.util.ArrayList;
import java.util.List;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.state.StateComponent;
import com.almasb.fxgl.pathfinding.astar.AStarCell;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.example.client.entities.Entities.*;

public final class TankAIComponent extends Component {

    private static final List<AStarCell> MOVE_TO_CELLS = new ArrayList<>();

    private StateComponent state;
    private LazyValue<AStarMoveComponent> astar = new LazyValue<>(() ->
        entity.getComponent(AStarMoveComponent.class));

    private TankViewComponent tank;
	private Entity player;

    private AStarCell moveToCell = null;

    @Override
    public void onUpdate(double tpf) {
        if (player == null) {
            player = getGameWorld().getSingleton(PLAYER);
        }

        if (astar.get().isAtDestination()) {
            clearMoveCell();
        }
    }

    private void clearMoveCell() {
        if (moveToCell != null) {
            MOVE_TO_CELLS.remove(moveToCell);
            moveToCell = null;
        }
    }

    private void moveTo(AStarCell cell) {
        moveToCell = cell;
        MOVE_TO_CELLS.add(cell);

        astar.get().moveToCell(cell);
    }

    private AStarCell getCell(Entity entity) {

        int x = (int) ((entity.getX() + 30 / 2) / 30);
        int y = (int) ((entity.getY() + 30 / 2) / 30);

        return astar.get().getGrid().get(x, y);
    }
}


