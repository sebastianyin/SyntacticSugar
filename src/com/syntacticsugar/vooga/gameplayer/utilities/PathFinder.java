package com.syntacticsugar.vooga.gameplayer.utilities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.syntacticsugar.vooga.gameplayer.universe.map.PathFindingMap;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;

public class PathFinder {
	private Map<Point, Boolean> grid;
	private List<Point> start;
	private List<Point> end;
	private List<Stack<Point>> paths;
	private Stack<Point> path;
	private Map<Point, List<Point>> memo;

	private Map<Point, Integer> distanceMap;
	private Queue<Point> distanceQueue;

	private int rows;
	private int cols;
	
	public PathFinder(PathFindingMap map, int size, Point start, Point end) {
		this(fillMap(map.getPathFindingMap()), size, size, start, end);
	}
	
	public PathFinder(PathFindingMap map, int size, List<Point> start, List<Point> end) {
		this(fillMap(map.getPathFindingMap()), size, size, start, end);
	}
	
	public PathFinder(Map<Point, IGameTile> tileMap, int size, List<Point> start, List<Point> end) {
		this(fillMap(tileMap), size, size, start, end);
	}

	public PathFinder(Map<Point, Boolean> pathGrid, int rows, int cols, List<Point> start, List<Point> end) {
		grid = pathGrid;
		this.start = start;
		this.end = end;
		this.rows = rows;
		this.cols = cols;
		paths = new ArrayList<Stack<Point>>();
		path = new Stack<Point>();
		memo = new HashMap<Point, List<Point>>();

		distanceMap = new HashMap<Point, Integer>();
		distanceQueue = new LinkedList<Point>();
		findPaths();
	}
	
	public PathFinder(Map<Point, IGameTile> tileMap, int size, Point start, Point end) {
		this(fillMap(tileMap), size, size, start, end);
	}
	
	public PathFinder(Map<Point, Boolean>  pathGrid, int rows, int cols, Point start, Point end) {
		grid = pathGrid;
		this.rows = rows;
		this.cols = cols;
		paths = new ArrayList<Stack<Point>>();
		path = new Stack<Point>();
		memo = new HashMap<Point, List<Point>>();

		distanceMap = new HashMap<Point, Integer>();
		distanceQueue = new LinkedList<Point>();
		fillDistances(start);
		this.end = new ArrayList<Point>();
		this.end.add(end);
		shortestPath();
		for (int i=0; i<paths.size(); i++) {
			path = paths.get(i);
		}

	}
	
	private static Map<Point, Boolean> fillMap(Map<Point, IGameTile> tileMap) {
		Map<Point, Boolean> bMap = new HashMap<Point, Boolean>();
		for (Point p : tileMap.keySet()) {
			bMap.put(p, tileMap.get(p).isWalkable());
		}
		return bMap;
	}
	
	public Path getPath() {
		return new Path(path);
	}

	private void findPaths() {
		for (Point ss : start) {
			fillDistances(ss);
			shortestPath();
			distanceMap.clear();
			distanceQueue.clear();
		}
	}

	private void shortestPath() {
		for (Point endPoint : end) {
			if (distanceMap.keySet().contains(endPoint)) {
				Stack<Point> sta = new Stack<Point>();
				Point currentPoint = new Point(endPoint);
				sta.push(new Point(currentPoint));
				int distance = distanceMap.get(currentPoint);
				while (distance > 0) {
					List<Point> neighbors = getNeighbors(currentPoint);
					for (Point n : neighbors) {
						if (distanceMap.containsKey(n)) {
							if (distanceMap.get(n) == distance - 1) {
								distance = distance - 1;
								currentPoint = n;
								sta.push(new Point(currentPoint));
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

	private void fillDistances(Point start) {
		distanceMap.put(start, 0);
		distanceQueue.addAll(getNeighbors(start));
		while (distanceQueue.size() > 0) {
			Point front = distanceQueue.poll();
			List<Point> frontNeighbors = getNeighbors(front);
			// find the smallest distance of its neighbors and add one,
			int minDistance = Integer.MAX_VALUE;
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
		}
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
			if (grid.get(n)) {
				validPoints.add(n);
			}
		}
		if (x + 1 < cols) {
			Point n = new Point(x + 1, y);
			if (grid.get(n)) {
				validPoints.add(n);
			}
		}

		if (y - 1 >= 0) {
			Point n = new Point(x, y - 1);
			if (grid.get(n)) {
				validPoints.add(n);
			}

		}
		if (y + 1 < rows) {
			Point n = new Point(x, y + 1);
			if (grid.get(n)) {
				validPoints.add(n);
			}

		}
		memo.put(p, validPoints);
		Collections.shuffle(validPoints);
		return validPoints;
	}

}