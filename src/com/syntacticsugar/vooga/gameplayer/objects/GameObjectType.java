package com.syntacticsugar.vooga.gameplayer.objects;

public enum GameObjectType {
	PLAYER, ENEMY, TOWER, ITEM;

	@Override
	public String toString() {
		String name = this.name();
		StringBuilder builder = new StringBuilder();
		builder.append(name.charAt(0));
		builder.append(name.toLowerCase().substring(1));
		System.out.println(builder.toString());
		return builder.toString();
	}
}