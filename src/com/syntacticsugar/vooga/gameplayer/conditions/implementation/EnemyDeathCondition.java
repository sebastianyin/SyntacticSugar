package com.syntacticsugar.vooga.gameplayer.conditions.implementation;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.conditions.AbstractCondition;
import com.syntacticsugar.vooga.gameplayer.conditions.ConditionType;
import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.LevelChangeEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectDespawnEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;

@EditableClass (
		className = "Enemies Dead Necessary"
		)
public class EnemyDeathCondition extends AbstractCondition {

	private int enemiesDead;
	private int enemiesToDie;

	public EnemyDeathCondition() {
		super(ConditionType.WINNING);
	}
	
	public EnemyDeathCondition(int numbertodie) {
		super(ConditionType.WINNING);
		enemiesToDie = numbertodie;
		enemiesDead = 0;
	}
	
	@Override
	protected void setDefaults() {
		enemiesToDie = 10;
		enemiesDead = 0;
	}

	@Override
	public void onEvent(IGameEvent e) {
		try {
			ObjectDespawnEvent event = (ObjectDespawnEvent) e;
			if (event.getObj().getType().equals(GameObjectType.ENEMY)) {
				enemiesDead++;
				if (enemiesDead >= enemiesToDie) {
					postEvent(new LevelChangeEvent(this.returnType()));
				}
			}
			

		} catch (ClassCastException ce) {

		}

	}
	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/
	
	@EditableField (	
		inputLabel = "Enemy Threshold",
		defaultVal = "10"
		)
	private void editEnemiesThresh(String arg) {
		try {
			this.enemiesToDie = InputParser.parseAsInt(arg);
		} catch (InputTypeException e) { 	}
	}

}
