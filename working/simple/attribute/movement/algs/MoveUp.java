package simple.attribute.movement.algs;

import simple.attribute.movement.ISimpleMover;

public class MoveUp implements IMovementSetter {

	@Override
	public void setMovement(ISimpleMover mover) {
		double speed = mover.getSpeed();
		mover.setYVelocity(-1.0 * speed);
	}

}
