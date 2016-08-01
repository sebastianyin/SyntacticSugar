package Objects.Characters;

import Objects.GameObject;
import Objects.Interfaces.IMovable;

public abstract class GameCharacter extends GameObject implements IMovable {

	@Override
	public abstract void move();

}
