package com.syntacticsugar.vooga.gameplayer.objects;

import java.util.Observable;

import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;

import javafx.geometry.Point2D;

public class BoundingBox extends Observable implements IBoundingBox {

	private Point2D myPoint;
	private double myWidth;
	private double myHeight;
	private Direction myDirection;
	private double myRotation;
	
	public BoundingBox(Point2D point, double width, double height) {
		myPoint = point;
		myWidth = width;
		myHeight = height;
		myDirection = Direction.RIGHT;
		myRotation = 0;
	}
	
	@Override
	public Point2D getPoint() {
		return new Point2D(myPoint.getX(), myPoint.getY());
	}
	
	@Override
	public Direction getDirection() {
		return this.myDirection;
	}
	
	@Override
	public double getRotate() {
		return this.myRotation;
	}

	@Override
	public double getWidth() {
		return myWidth;
	}

	@Override
	public double getHeight() {
		return myHeight;
	}

	@Override
	public void setPoint(Point2D point) {
		myPoint = point;
		triggerViewUpdate();
	}

	@Override
	public void setDirection(Direction dir) {
		this.myDirection = dir;
		if (dir == null) {
			return;
		}
		switch (dir) {
		case DOWN:
			setRotate(90);
			break;
		case UP:
			setRotate(270);
			break;
		case RIGHT:
			setRotate(0);
			break;
		case LEFT:
			setRotate(180);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void setRotate(double rotation) {
		this.myRotation = rotation;
		triggerViewUpdate();
	}
	
	@Override
	public void setWidth(double width) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\nZERO SSETs");
		myWidth = width;
		triggerViewUpdate();
	}

	@Override
	public void setHeight(double height) {
		myHeight = height;
		triggerViewUpdate();
	}
	
	private void triggerViewUpdate() {
		setChanged();
		notifyObservers();
	}

}
