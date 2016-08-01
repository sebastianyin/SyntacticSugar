package simple.universe.map.tiles.implementations;

import javafx.geometry.Point2D;

public class SceneryTile extends AbstractTileImplementation {

	public SceneryTile(Point2D point) {
		super(point);
	}
	
	@Override
	public boolean isWalkable() {
		return false;
	}

	@Override
	public boolean isPlaceable() {
		return true;
	}

}
