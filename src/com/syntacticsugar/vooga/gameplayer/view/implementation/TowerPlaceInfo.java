package com.syntacticsugar.vooga.gameplayer.view.implementation;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;

import javafx.geometry.Point2D;

public class TowerPlaceInfo {
	
	private Point2D myCoordinates;
	private Direction myDirection;
	private double myWidth;
	private double myHeight;
	
	public TowerPlaceInfo(double x, double y, Direction direction, double width, double height){
		myCoordinates = new Point2D(x, y);
		myDirection = direction;
		myWidth = width;
		myHeight = height;
	}
	
	public Point2D getCoordinates(){
		return myCoordinates;
	}
	
	public Direction getDirection(){
		return myDirection; 
	}
	
	public double getWidth(){
		return myWidth;
	}
	
	public double getHeight(){
		return myHeight;
	}

}
