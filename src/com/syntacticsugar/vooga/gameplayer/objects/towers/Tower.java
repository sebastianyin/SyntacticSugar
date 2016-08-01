package com.syntacticsugar.vooga.gameplayer.objects.towers;

import com.syntacticsugar.vooga.gameplayer.objects.GameObject;
import com.syntacticsugar.vooga.xml.data.TowerData;

public class Tower extends GameObject implements ITower {
	
	private int myCost;
	private String myName;
	
	public Tower(TowerData data) {
		super(data);
		myCost = data.getCost();
		myName = data.getName();
	}
	
	public int getCost() {
		return myCost;
	}
	
	public String getName(){
		return myName;
	}

}
