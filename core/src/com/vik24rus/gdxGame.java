package com.vik24rus;

import com.badlogic.gdx.Game;

public class gdxGame extends Game {
	@Override
//	public void create () {
//		this.setScreen(new Menu(this));
//	}
	public void create () {
		this.setScreen(new GameScreen(this));
	}
	//public void create () {	this.setScreen(new TestForTable(this)); }
	@Override
	public void render () {
		super.render();
	}

	public void dispose() {	}
}
