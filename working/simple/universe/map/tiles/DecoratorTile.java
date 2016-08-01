package simple.universe.map.tiles;

import javafx.geometry.Point2D;
import simple.universe.map.tiles.implementations.PathTile;

public class DecoratorTile implements IGameTile {

	private IGameTile myImplementation;
	
	public DecoratorTile(Point2D point, double width, double height) {
		this.myImplementation = new PathTile(point);
	}
	
	public void setImplementation(IGameTile implementation) {
		this.myImplementation = implementation;
	}
	
	@Override
	public boolean isWalkable() {
		return this.myImplementation.isWalkable();
	}

	@Override
	public boolean isPlaceable() {
		return this.myImplementation.isPlaceable();
	}

	@Override
	public Point2D getPoint() {
		return this.myImplementation.getPoint();
	}

}
