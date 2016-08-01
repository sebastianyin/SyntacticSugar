package frontend;

public interface IGameSplashView{
	
	public void loadSavedGame(String gameXML);
	
	public void loadNewGame();
	
	public void launchGame();
	
	//returns help file
	public void getHelp();

	public void editSettings(OverallSettings settings);

	public void viewScoreboard();
	
}
