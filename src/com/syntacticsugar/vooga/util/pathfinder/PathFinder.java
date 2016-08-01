package com.syntacticsugar.vooga.util.pathfinder;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PathFinder {
	private boolean[][] map;
	private int height;
	private int width;
	
	private Point startPoint;
	private List<Point> destinationPoints;
	
	private Map<Point, List<Point>> memo;
	private Map<Point, Integer> distanceMap;
	private Queue<Point> distanceQueue;
	
	private List<List<Point>> paths;
	
	public PathFinder(boolean[][] map, Point startPoint, List<Point> destinationPoints) {
		this.map = map;
		this.startPoint = startPoint;
		this.destinationPoints = destinationPoints;
		width = map.length;
		height = map[0].length;
		
		memo = new HashMap<Point, List<Point>>();
		distanceMap = new HashMap<Point, Integer>();
		distanceQueue = new LinkedList<Point>();
		
		paths = new ArrayList<List<Point>>();
		fillDistances();
		shortestPaths();
	}
	
	public Point getNext() {
		List<Point> shortestPath = shortestPath();
		if (shortestPath.size()<=1) {
			return startPoint;
		} 
		return shortestPath.get(1);
	}
	
	public List<Point> getPath() {
		return shortestPath();
	}
	
	private void fillDistances() {
		distanceMap.put(startPoint, 0);
		distanceQueue.addAll(getNeighbors(startPoint));
		while (distanceQueue.size() > 0) {
			Point front = distanceQueue.poll();
			List<Point> frontNeighbors = getNeighbors(front);
			// find the smallest distance of its neighbors and add one,
			int minDistance = Integer.MAX_VALUE-1;
			for (Point nPoint : frontNeighbors) {
				if (distanceMap.containsKey(nPoint)) {
					if (distanceMap.get(nPoint) < minDistance) {
						minDistance = distanceMap.get(nPoint);
					}
				} else {
					// add all neighbors to the queue
					if (!distanceQueue.contains(nPoint)) {
						distanceQueue.add(nPoint);
					}
				}
			}
			distanceMap.put(front, minDistance + 1);
			//System.out.println("Added point: "+front+" with distance "+(minDistance+1));
		}
	}

	private void shortestPaths() {
		for (Point endPoint : destinationPoints) {
			if (distanceMap.keySet().contains(endPoint)) {
				List<Point> sta = new ArrayList<Point>();
				Point currentPoint = new Point(endPoint);
				sta.add(new Point(currentPoint));
				int distance = distanceMap.get(currentPoint);
				while (distance > 0) {
					List<Point> neighbors = getNeighbors(currentPoint);
					for (Point n : neighbors) {
						if (distanceMap.containsKey(n)) {
							if (distanceMap.get(n) == distance - 1) {
								distance = distance - 1;
								currentPoint = n;
								sta.add(new Point(currentPoint));
								break;
							}
						}
					}
				}
				Collections.reverse(sta);
				paths.add(sta);
			}
		}
	}
	
	private List<Point> shortestPath() {
		List<Point> shortest = new ArrayList<Point>();
		Collections.shuffle(paths);
		int shortSize = Integer.MAX_VALUE;
		for (int i=0; i<paths.size(); i++) {
			if (paths.get(i).size() < shortSize) {
				shortest = paths.get(i);
				shortSize = paths.get(i).size();
			}
		}
		return shortest;
	}


	private List<Point> getNeighbors(Point p) {
		if (memo.containsKey(p)) {
			List<Point> ret = memo.get(p);
			Collections.shuffle(ret);
			return ret;
		}
		List<Point> validPoints = new ArrayList<Point>();
		int x = p.x;
		int y = p.y;
		if (x - 1 >= 0) {
			Point n = new Point(x - 1, y);
			if (map[x][y]) {
				validPoints.add(n);
			}
		}
		if (x + 1 < width) {
			Point n = new Point(x + 1, y);
			if (map[x][y]) {
				validPoints.add(n);
			}
		}

		if (y - 1 >= 0) {
			Point n = new Point(x, y - 1);
			if (map[x][y]) {
				validPoints.add(n);
			}

		}
		if (y + 1 < height) {
			Point n = new Point(x, y + 1);
			if (map[x][y]) {
				validPoints.add(n);
			}

		}
		memo.put(p, validPoints);
		Collections.shuffle(validPoints);
		return validPoints;
	}

}
