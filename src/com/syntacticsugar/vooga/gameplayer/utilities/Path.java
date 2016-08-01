package com.syntacticsugar.vooga.gameplayer.utilities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Path {
	List<Point> list;
	Stack<Point> stack;
	
	public Path() {
		list = new ArrayList<Point>();
		stack = new Stack<Point>();
	}
	
	public Path(Stack<Point> path) {
		list = new ArrayList<Point>();
		stack = path;
		list.addAll(stack);
	}
	
	public List<Point> getList() {
		return list;
	}
	
	public Stack<Point> getStack() {
		return stack;
	}
	
	public Point getNext(){
		if (list.size() < 2) {
			return null; // TODO
		} 
		return list.get(1); // return 2nd to last which is the next element
	}
	
	public Point getDestination() {
		return stack.peek();
	}
}