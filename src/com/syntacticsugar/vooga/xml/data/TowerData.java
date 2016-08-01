package com.syntacticsugar.vooga.xml.data;

import com.syntacticsugar.vooga.gameplayer.objects.towers.ITower;

public class TowerData extends ObjectData {
	
	private int cost;
	private String name;
	
	private static final long serialVersionUID = 2L;
	
	public TowerData(ITower o) {
		super(o);
		cost = o.getCost();
		name = o.getName();
	}
	
	public TowerData() {
		
	}

	public int getCost() {
		return cost;
	}
	
	public void setCost(int c) {
		cost = c;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String newName){
		name = newName;
	}

}
