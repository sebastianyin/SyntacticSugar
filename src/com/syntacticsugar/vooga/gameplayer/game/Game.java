package com.syntacticsugar.vooga.gameplayer.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.syntacticsugar.vooga.gameplayer.universe.GameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.filechooser.FileChooserUtil;
import com.syntacticsugar.vooga.xml.XMLHandler;
import com.syntacticsugar.vooga.xml.data.GameData;
import com.syntacticsugar.vooga.xml.data.UniverseData;
import com.syntacticsugar.vooga.xml.data.GlobalSettings;

public class Game implements IGame {
	
	private List<IGameUniverse> myUniverses;
	
	private List<UniverseData> myUniverseData; // For saving
	private GlobalSettings mySettings;
	private GameData gameData;
	
	private int myLevel; // STARTS AT 1 BY CONVENTION
	
	public Game(GameData data) {
		gameData = data;
		Collection<UniverseData> udata =  data.getUniverses();
		myUniverses = new ArrayList<>();
		myUniverseData = new ArrayList<>();
		for (UniverseData d: udata) {
			myUniverses.add(new GameUniverse(d, data.getSettings()));
			myUniverseData.add(d);
		}
		mySettings = data.getSettings();
		myLevel = mySettings.getLevel();
	}

	@Override
	public IGameUniverse nextLevel() {
		if (myLevel >= myUniverses.size() - 1) {
			System.out.println("Out of levels. You win");
		}
		return myUniverses.get(myLevel++);
	}
	
	public void reset(){
		myUniverses.clear();
		for(UniverseData datum : myUniverseData){
			myUniverses.add(new GameUniverse(datum, gameData.getSettings()));
		}
		mySettings = gameData.getSettings();
		myLevel = mySettings.getLevel();
	}
	
	@Override
	public IGameUniverse getLevel(int i) {
		return myUniverses.get(i - 1);
	}
	
	@Override
	public void saveGame(UniverseData d) {
		myUniverseData.remove(myLevel - 1);
		myUniverseData.add(myLevel - 1, d);
		Collection<UniverseData> gameData = (Collection<UniverseData>) myUniverseData;
		mySettings.setLevel(myLevel);
		GameData gameSave = new GameData(gameData, mySettings);
		XMLHandler<GameData> xmlMake = new XMLHandler<>();

		FileChooserUtil.saveFile(ResourceManager.getString("save_game"), "", null, selectedFile -> {
			xmlMake.write(gameSave, selectedFile);
		});
	
		//TODO: Write it to a file (look at the authoring env object save
//		xmlMake.writeXMLToFile(xmldata, f);
	}

}
