package com.syntacticsugar.vooga.gameplayer.attribute.movement;

import java.awt.Point;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.control.IUserControlAttribute;
import com.syntacticsugar.vooga.gameplayer.event.implementations.PlayerChangeTileEvent;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.userinput.IKeyInputStorage;

import javafx.scene.input.KeyCode;

@EditableClass (
		className = "Player Movement Control"
		)
public class MovementControlAttribute extends AbstractMovementAttribute implements IUserControlAttribute {

	private KeyCode upCode;
	private KeyCode downCode;
	private KeyCode leftCode;
	private KeyCode rightCode;

	private Direction myCurrentMovement;

	public MovementControlAttribute() {
		super();
		setDefaults();
	}

	private MovementControlAttribute(MovementControlAttribute toCopy) {
		super(toCopy);
		this.upCode = toCopy.upCode;
		this.downCode = toCopy.downCode;
		this.leftCode = toCopy.leftCode;
		this.rightCode = toCopy.rightCode;
		this.myCurrentMovement = toCopy.myCurrentMovement;
	}
	
	@Override
	protected void setDefaults() {
		super.setDefaults();
		this.upCode = KeyCode.UP;
		this.downCode = KeyCode.DOWN;
		this.leftCode = KeyCode.LEFT;
		this.rightCode = KeyCode.RIGHT;
		this.myCurrentMovement = Direction.STOP;
	}

	@Override
	public void updateKeyInput(IKeyInputStorage universeKeyInput) {
		for (KeyCode code : universeKeyInput.getCurrentKeyInput()) {
			this.myCurrentMovement = getDirectionFromInput(code);
		}
	}

	private void processKeyInput() {
		if (!myCurrentMovement.equals(Direction.STOP)) {
			setDirection(myCurrentMovement);
		}
		myCurrentMovement = Direction.STOP;
	}

	@Override
	public void updateSelf(IGameUniverse universe) {
		Point oldPoint = new Point(universe.getMap().getMapIndexFromCoordinate(getParent().getPoint()));
		updateKeyInput(universe);
		setVelocity(myCurrentMovement);
		processKeyInput();
		move(universe);
		resetVelocity();
		Point newPoint = new Point(universe.getMap().getMapIndexFromCoordinate(getParent().getPoint()));
		if (oldPoint.x != newPoint.x || oldPoint.y != newPoint.y) {
			universe.postEvent(new PlayerChangeTileEvent(oldPoint, newPoint));
		}
	}

	private Direction getDirectionFromInput(KeyCode code) {
		if (code.equals(upCode))
			return Direction.UP;
		else if (code.equals(downCode))
			return Direction.DOWN;
		else if (code.equals(leftCode))
			return Direction.LEFT;
		else if (code.equals(rightCode))
			return Direction.RIGHT;
		return myCurrentMovement;
	}

	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/

	@EditableField(
			inputLabel = "KeyCode: Move Up",
			defaultVal = "UP"
			)
	private void editUpCode(String arg) {
		try {
			this.upCode = InputParser.parseAsKeyCode(arg.trim());
		} catch (InputTypeException e) { }
	}

	@EditableField(
			inputLabel = "KeyCode: Move Down",
			defaultVal = "DOWN"
			)
	private void editDownCode(String arg) {
		try {
			this.downCode = InputParser.parseAsKeyCode(arg.trim());
		} catch (InputTypeException e) { }
	}

	@EditableField(
			inputLabel = "KeyCode: Move Left",
			defaultVal = "LEFT"
			)
	private void editLeftCode(String arg) {
		try {
			this.leftCode = InputParser.parseAsKeyCode(arg.trim());
		} catch (InputTypeException e) { }
	}

	@EditableField(
			inputLabel = "KeyCode: Move Right",
			defaultVal = "RIGHT"
			)
	private void editRightCode(String arg) {
		try {
			this.rightCode = InputParser.parseAsKeyCode(arg.trim());
		} catch (InputTypeException e) { }
	}

	@Override
	public IAttribute copyAttribute() {
		return new MovementControlAttribute(this);
	}

}
