package simple.attribute.movement.algs;

import simple.attribute.movement.ISimpleMover;

public class MoveRight implements IMovementSetter {

	@Override
	public void setMovement(ISimpleMover mover) {
		double speed = mover.getSpeed();
		mover.setXVelocity(speed);
	}

}
