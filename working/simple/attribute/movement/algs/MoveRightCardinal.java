package simple.attribute.movement.algs;

import simple.attribute.movement.ISimpleMover;

public class MoveRightCardinal implements IMovementSetter {

	@Override
	public void setMovement(ISimpleMover mover) {
		double speed = mover.getSpeed();
		mover.setVelocity(speed, 0);
	}

}
