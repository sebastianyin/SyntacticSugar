package frontend;

public interface IGameEngineView {

	public void displayUpdate();

	public void pause();

	public void play();

	public void goHome();

	public void startLevel();

	public void placeTower();

	public void upgradeTower(int path);

	public void sellTower();

	public void saveCurrentGame();

	public void updateScore();

	public void updateResources();

	public void endGame(boolean winLose);
}
