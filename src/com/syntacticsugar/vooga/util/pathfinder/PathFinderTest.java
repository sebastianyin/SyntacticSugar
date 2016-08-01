package com.syntacticsugar.vooga.util.pathfinder;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PathFinderTest {
	public static void main(String args[]) {
		boolean[][] map = new boolean[10][10];
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				map[row][col] = true;
				if (row==1 && col!=8) {
					map[row][col] = false;
				}
			}
		}
		for (int i=0; i<10; i++) {
			//map[3][i] = false;
		}
		
		List<Point> destination = new ArrayList<Point>();
		destination.add(new Point(5,5));
		PathFinder pf = new PathFinder(map, new Point(0,0), destination);
		Point next = pf.getNext();
		System.out.println(next);
		List<Point> path = pf.getPath();
		System.out.println(path);
		System.out.println();
		
		/*List<Point> destinations = new ArrayList<Point>();
		destinations.add(new Point(6,6));
		destinations.add(new Point(4,4));
		PathFinder pf2 = new PathFinder(map, new Point(5,5), destinations);
		Point next2 = pf2.getNext();
		System.out.println(next2);
		List<Point> path2 = pf2.getPath();
		System.out.println(path2);
		System.out.println();*/
	}
}
