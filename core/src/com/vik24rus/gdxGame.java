package com.vik24rus;

import com.badlogic.gdx.Game;

//https://libgdx.info/buttons-scene2d/
//https://habr.com/post/224175/
//https://habr.com/sandbox/101382/
//https://habr.com/post/314312/

public class gdxGame extends Game {
	@Override
//	public void create () {
//		this.setScreen(new Menu(this));
//	}
	public void create () {	this.setScreen(new TestForTable(this)); }
	@Override
	public void render () {
		super.render();
	}

	public void dispose() {	}
}
