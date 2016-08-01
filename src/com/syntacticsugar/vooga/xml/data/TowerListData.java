package com.syntacticsugar.vooga.xml.data;

import java.util.Collection;

public class TowerListData {
	
	private Collection<TowerData> towers;
	
	public TowerListData(Collection<TowerData> data) {
		towers = data;
	}
	
	public Collection<TowerData> getTowers() {
		return towers;
	}

}
