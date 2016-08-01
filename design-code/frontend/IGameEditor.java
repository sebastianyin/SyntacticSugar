package frontend;

public interface IGameEditor {
	public void createTower();

	public void createEnemy();

	public void createPlayer();

	public void createTile();

	public void addTileToMap(ITile tile);

	public void addPlayerToMap(IPlayer player);

	public void addEnemyToQueue(IMonster monster);

	public void makeTowerAvailable(ITower tower);

	public void changeSetting(LevelSettings setting);

	public void addLevel();

	public void switchLevel(int levelNumber);

	public void deleteLevel(int levelNumber);

	public void save();

}
