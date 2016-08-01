package simple.attribute;

import java.awt.Point;

import javafx.geometry.Point2D;
import simple.attribute.movement.ISimpleMover;
import simple.attribute.movement.algs.IMovementSetter;
import simple.attribute.movement.algs.MoveRightCardinal;
import simple.event.ISimpleEvent;
import simple.obj.ISimpleBoundingBox;
import simple.obj.ISimpleObject;
import simple.obj.SimpleBoundingBox;
import simple.universe.ISimpleUniverse;
import simple.universe.userinput.IKeyInputStorage;
import simple.utilities.Path;
import simple.utilities.PathFinder;

public class SimpleAIMotionAttribute extends SimpleAbstractAttribute implements ISimpleMover {

	private double xVelocity;
	private double yVelocity;
	private double mySpeed;
	private Point currentLocation;
	private Point destinationLocation;
	
	private Path myPath;
	private Point currentTile;
	private Point nextTile;
	private Point destinationTile;
	
	private IMovementSetter myMover;
	
	public SimpleAIMotionAttribute(ISimpleObject parent) {
		super(parent);
		myMover = new MoveRightCardinal(); // DEFAULT RIGHT MOVEMENT TODO
		// currentLocation = parent.getBoundingBox().getPoint(); TODO
		
		// update currentTile
		myPath = new Path();
		setVelocity(0, 0);
		setSpeed(3);
	}
	
	@Override
	public void receiveEvent(ISimpleEvent event) {
		// TODO could be speed changes, make immobile for n seconds, etc.
	}
	
	private void generatePath(ISimpleUniverse universe) {
		// TODO
		/*
		 * Find destination point
		 */
		//destinationTile = universe.getDestinationPoint();
		
		/*
		 * Get boolean tileMap for path
		 */
		//Map<Point, Boolean> tileMap = universe.getTileMap();
		
		/*
		 * Find path
		 */
		//myPath = new PathFinder(tileMap, universe.getMapSize(), currentTile, destinationTile);
	}
	
	private Point nextPoint() {
		int currentIndex = myPath.getList().indexOf(currentTile);
		if (currentIndex == myPath.getList().size()-1 ) {
			return myPath.getDestination();
		} else {
			return myPath.getList().get(currentIndex+1);
		}
	}

	@Override
	public void updateSelf(ISimpleUniverse universe) {
		// every n frames, run generatePath(universe);
		
		// set new xVelocity, yVelocity
		// convert currentTile to double and subtract currentLocation
		// this difference is the velocity in the direction you want to go
		
		// move to nextPoint
		move();
	}
	
	@Override
	public void move() {
		Point nextPoint = nextPoint();
		// ISimpleBoundingBox box = getParent().getBoundingBox();
		// Point2D oldPoint = box.getPoint();
		// convert nextPoint to Point2D
		// if (distance from nextPoint is greater than xVelocity + yVelocity) {
		// 		box.setPoint(new Point2D(oldPoint.getX() + xVelocity, oldPoint.getY() + yVelocity));
		// } else {
		//		box.setPoint(nextPointX, nextPointY);
		// }
		// box.setPoint(new Point2D(oldPoint.getX() + xVelocity, oldPoint.getY() + yVelocity));
		System.out.println(String.format("X Velocity: %d   Y Velocity: %d", xVelocity, yVelocity));
	}
	
	@Override
	public double getSpeed() {
		return this.mySpeed;
	}

	@Override
	public void setSpeed(double speed) {
		this.mySpeed = speed;
	}
	
	@Override
	public void setVelocity(double xVel, double yVel) {
		setXVelocity(xVel);
		setYVelocity(yVel);
	}

	@Override
	public void setXVelocity(double xvel) {
		this.xVelocity = xvel;
	}
	
	@Override
	public void setYVelocity(double yvel) {
		this.yVelocity = yvel;
	}

}
