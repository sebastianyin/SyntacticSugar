package simple.universe.map;

public interface ISimpleGameMap {

	
	/**
	 * Returns a 2D boolean array representing the walkable vs. non-walkable
	 * tiles in this Universe.
	 * @return
	 */
	public boolean[][] retrievePath();
	
}
