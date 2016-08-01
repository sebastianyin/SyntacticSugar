package com.syntacticsugar.vooga.gameplayer.objects;

import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;

import javafx.geometry.Point2D;

public abstract class AbstractViewableObject implements IViewableObject{
	
	private BoundingBox myBoundingBox;
	private String myPath;
	//private int myID;
	
	public AbstractViewableObject(Point2D point, double width, double height, String path){
		myBoundingBox = new BoundingBox(point, width, height);
		myPath = path;
		myBoundingBox.setDirection(Direction.DOWN);
	}
	
	@Override
	public BoundingBox getBoundingBox(){
		return myBoundingBox;
	}
	
	@Override
	public String getPath(){
		return myPath;
	}
	
	@Override
	public void setPoint(Point2D point) {
		myBoundingBox.setPoint(point);
	}
	
	@Override
	public Point2D getPoint() {
		return myBoundingBox.getPoint();
	}

	
}
