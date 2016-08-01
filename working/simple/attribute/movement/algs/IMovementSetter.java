package simple.attribute.movement.algs;

import simple.attribute.movement.ISimpleMover;

public interface IMovementSetter {

	/**
	 * Change the current motion (x and y velocity) of a
	 * basic ISimpleMover object (ie. anything that can move). 
	 */
	public void setMovement(ISimpleMover mover);
	
}
