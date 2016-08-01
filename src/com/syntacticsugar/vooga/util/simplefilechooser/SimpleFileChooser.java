package com.syntacticsugar.vooga.util.simplefilechooser;

import java.io.File;

import com.syntacticsugar.vooga.xml.XMLHandler;
import com.syntacticsugar.vooga.xml.data.GameData;
import com.syntacticsugar.vooga.xml.data.MapData;
import com.syntacticsugar.vooga.xml.data.ObjectData;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SimpleFileChooser {
	public static File saveGame(GameData game, Stage myStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Game File");
		File f = fileChooser.showSaveDialog(myStage);
		game.setName(f.getName());
		XMLHandler<GameData> xml = new XMLHandler<>();
		xml.write(game, f);
		System.out.println(f.getName());
		return f;
	}

	public static void saveGameSimple(GameData game, File f) {

	}

	public static File saveObject(ObjectData obj, Stage myStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Game File");
		File f = fileChooser.showSaveDialog(myStage);
		XMLHandler<ObjectData> xml = new XMLHandler<>();
		xml.write(obj, f);
		System.out.println(f.getName());
		return f;
	}

	public static File saveMap(MapData map, Stage myStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Map File");
		File f = fileChooser.showSaveDialog(myStage);
		XMLHandler<MapData> xml = new XMLHandler<>();
		xml.write(map, f);
		System.out.println(f.getName());
		return f;
	}

	public static MapData loadMap(Stage myStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Map File");
		File f = fileChooser.showOpenDialog(myStage);
		XMLHandler<MapData> xml = new XMLHandler<>();
		MapData m = xml.read(f);
		return m;
	}

	public static GameData loadGame(Stage myStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Game File");
		File f = fileChooser.showOpenDialog(myStage);
		XMLHandler<GameData> xml = new XMLHandler<>();
		GameData m = xml.read(f);
		return m;
	}
}
