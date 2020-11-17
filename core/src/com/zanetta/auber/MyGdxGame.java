package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;

import javax.swing.text.html.HTML;


public class MyGdxGame extends ApplicationAdapter {
	private static final String TAG = MyGdxGame.class.getSimpleName();
	//SpriteBatch batch;
	Texture img;
	Infiltrator infiltrator;
	Player player;
	Group group;
	Stage stage;


	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		stage = new Stage();
		group = new Group();

		group.setPosition(50, 100);
		stage.addActor(group);

		player = new Player(new TextureRegion(img));
		player.setScale(0.5F);
		player.setPosition(0,0);

		Gdx.input.setInputProcessor(stage);
		stage.addListener(new MyInputListener());
		infiltrator = new Infiltrator(new TextureRegion(img));
		infiltrator.setPosition(50,100);
		infiltrator.addListener(new MyClickListener());
		infiltrator.setOrigin(0,0);
		infiltrator.setScale(0.5F,1.0F);
		infiltrator.setRotation(45);
		group.addActor(player);
		group.addActor(infiltrator);
	}

	//@Override
	//public void render () {
	//	Gdx.gl.glClearColor(1, 0, 0, 1);
	//	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	//	infiltrator.act(Gdx.graphics.getDeltaTime());
	//	batch.begin();
	//	batch.draw(img, 0, 0);
	//	infiltrator.draw(batch, 1.0F);
	//	batch.end();
	//}


	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	//@Override
	//public void dispose () {
	//	batch.dispose();
	//	img.dispose();
	//}


	@Override
	public void dispose() {
		if (img != null){
			img.dispose();
		}
		if (stage != null){
			stage.dispose();
		}
	}

	private class MyInputListener extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			switch (keycode) {
				case Input.Keys.UP: {
					Gdx.app.log(TAG, "UP has been pressed");
					break;
				}
				case Input.Keys.DOWN: {
					Gdx.app.log(TAG, "DOWN has been pressed");
					break;
				}
				case Input.Keys.A: {
					Gdx.app.log(TAG, "A has been pressed");
					break;
				}
				case Input.Keys.ENTER: {
					Gdx.app.log(TAG, "ENTER has been pressed");
					break;
				}
				case Input.Keys.LEFT: {
					Gdx.app.log(TAG, "LEFT has been pressed");
					break;
				}
				case Input.Keys.RIGHT: {
					Gdx.app.log(TAG, "RIGHT has been pressed");
					break;
				}
				case Input.Keys.E: {
					Gdx.app.log(TAG, "E has been pressed");
					break;
				}
				case Input.Keys.W: {
					Gdx.app.log(TAG, "W has been pressed");
					break;
				}
				case Input.Keys.S: {
					Gdx.app.log(TAG, "S has been pressed");
					break;
				}
				case Input.Keys.D: {
					Gdx.app.log(TAG, "D has been pressed");
					break;
				}
				default: {
					Gdx.app.log(TAG, "Other key pressed, KeyCode: " + keycode);
					break;

				}
			}
		return false;
		}

		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			Gdx.app.log(TAG, "touchDown:" + x + "," + "; pointer: " + pointer);
			return true;
		}

		@Override
		public void touchDragged(InputEvent event, float x, float y, int pointer){
			Gdx.app.log(TAG,"touchDragged:" + x + "," + y + "; pointer: " + pointer);
		}
	}


	private class MyClickListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			Actor actor = event.getListenerActor();
			Gdx.app.log(TAG, "clicked: " + x + "," + y + "; Actor: " + actor.getClass().getSimpleName());
		}
	}

}


