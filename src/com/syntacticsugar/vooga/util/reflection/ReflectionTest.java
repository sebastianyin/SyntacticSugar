package com.syntacticsugar.vooga.util.reflection;

import com.syntacticsugar.vooga.gameplayer.objects.GameObject;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;

import javafx.geometry.Point2D;

public class ReflectionTest {
	
	public static void main(String args[]){
		
		GameObjectType type = GameObjectType.PLAYER;
		double width = 100;
		double length = 60;
		Point2D point = new Point2D(10, 10);
		String path = "enemy_dragon.png";
		String classname = "com.syntacticsugar.vooga.gameplayer.objects.GameObject";

		System.out.println("HERER");
		
		GameObject myObject = (GameObject) Reflection.createInstance(classname, type, point, width, length, path);
		System.out.println(myObject.getBoundingBox().getHeight());
		
		
		System.out.println("HERE");
		
		
		
	}

}
