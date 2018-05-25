package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
	
	@Override
	public void create () {
		setScreen(new GameScreen());  // creating Game Screen and setting focus of application on it
	}

}
