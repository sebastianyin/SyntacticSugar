package simple.attribute.movement.algs;

import simple.attribute.movement.ISimpleMover;

public class MoveLeft implements IMovementSetter {

	@Override
	public void setMovement(ISimpleMover mover) {
		double speed = mover.getSpeed();
		mover.setXVelocity(-1.0 * speed);
	}

}
