package simple.universe.map.tiles.implementations;

import javafx.geometry.Point2D;
import simple.universe.map.tiles.IGameTile;

public abstract class AbstractTileImplementation implements IGameTile {

	private Point2D myPoint;
	
	public AbstractTileImplementation(Point2D point) {
		this.myPoint = point;
	}
	
	@Override
	public abstract boolean isWalkable();

	@Override
	public abstract boolean isPlaceable();

	@Override
	public Point2D getPoint() {
		return this.myPoint;
	}

}
