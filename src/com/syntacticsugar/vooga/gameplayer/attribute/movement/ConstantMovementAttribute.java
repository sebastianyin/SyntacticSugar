package com.syntacticsugar.vooga.gameplayer.attribute.movement;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.objects.IBoundingBox;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;

import javafx.geometry.Point2D;

@EditableClass (
		className = "Constant Movement Attribute"
		)
public class ConstantMovementAttribute extends AbstractMovementAttribute {

	private Direction myDir;
	
	public ConstantMovementAttribute() {
		super();
		setDefaults();
	}
	
	private ConstantMovementAttribute(ConstantMovementAttribute toCopy) {
		super(toCopy);
		this.myDir = toCopy.myDir;
	}
	
	public ConstantMovementAttribute(Direction dir, double speed) {
		super();
		setSpeed(speed);
		myDir = dir;
	}
	
	@Override
	protected void setDefaults() {
		super.setDefaults();
		this.myDir = Direction.DOWN;
	}

	@Override
	public void updateSelf(IGameUniverse universe) {
		setDirection(myDir);
		setVelocity(myDir);
		move(universe);
	}
	
	@Override
	public void move(IGameUniverse universe) {
		IBoundingBox box = getParent().getBoundingBox();
		Point2D oldPoint = box.getPoint();
		box.setPoint(new Point2D(oldPoint.getX() + getXVelocity(), oldPoint.getY() + getYVelocity()));
	}
	

	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/

	@EditableField(
			inputLabel = "Direction",
			defaultVal = "DOWN"
			)
	private void editDirection(String arg) {
		try {
			this.myDir = InputParser.parseAsDirection(arg);
		} catch (InputTypeException e) { }
	}

	@Override
	public IAttribute copyAttribute() {
		return new ConstantMovementAttribute(this);
	}
	
	
}
