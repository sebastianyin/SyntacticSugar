package simple.attribute.movement.algs;

import simple.attribute.movement.ISimpleMover;

public class MoveDown implements IMovementSetter {

	@Override
	public void setMovement(ISimpleMover mover) {
		double speed = mover.getSpeed();
		mover.setYVelocity(speed);
	}

}
