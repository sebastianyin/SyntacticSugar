package com.syntacticsugar.vooga.gameplayer.objects.items.bullets;

import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;

import javafx.geometry.Point2D;

public class BulletParams {

	Direction move;
	Point2D startPoint;
	String imagePath;
	double damage;
	double speed;
	
	double width;
	double height;
	
	GameObjectType type;
	
	public GameObjectType getType() {
		return type;
	}
	public void setType(GameObjectType type) {
		this.type = type;
	}
	
	public Direction getMove() {
		return move;
	}
	public void setMove(Direction move) {
		this.move = move;
	}
	public Point2D getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point2D startPoint) {
		this.startPoint = startPoint;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
}
