package com.vik24rus;

import com.badlogic.gdx.Game;

//https://libgdx.info/buttons-scene2d/
//https://habr.com/post/224175/
//https://habr.com/sandbox/101382/
//https://habr.com/post/314312/

public class gdxGame extends Game {
	//public BitmapFont font;
	
	@Override
	public void create () {
	//	font = new BitmapFont();
		this.setScreen(new Menu(this));  //благодаря такой инициализации можно к примеру тут инициализировать
		                                         // шрифт и передать его в скрин Menu
	}
	@Override
	public void render () {
		super.render();
	}

}
