package com.syntacticsugar.vooga.gameplayer.view.implementation;

import com.syntacticsugar.vooga.util.properties.PropertiesManager;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MoneyInfo extends HBox{
	
	private Label myMoney;
	private final String labelText;
	private PropertiesManager myPropertiesManager;
	
	public MoneyInfo(int initialCash, String style){
		this(style);
		this.setAlignment(Pos.CENTER_LEFT);
		myMoney = new Label(labelText + initialCash);
		ImageView coinImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("movingCoin.gif")));
		coinImage.setFitHeight(myPropertiesManager.getDoubleProperty("CoinSize"));
		coinImage.setFitWidth(myPropertiesManager.getDoubleProperty("CoinSize"));
		this.getChildren().addAll(myMoney, coinImage);
	}
	
	private MoneyInfo(String style){
		super(5);
		myPropertiesManager = new PropertiesManager("com/syntacticsugar/vooga/resources/View");
		labelText = myPropertiesManager.getProperty(style);
	}
	
	public void changeMoney(int money){
		myMoney.setText(labelText+ " " + money);
	}

}
