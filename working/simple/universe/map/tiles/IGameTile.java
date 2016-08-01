package simple.universe.map.tiles;

import javafx.geometry.Point2D;

public interface IGameTile {

	public boolean isWalkable();
	
	public boolean isPlaceable();
	
	public Point2D getPoint();
	
}
