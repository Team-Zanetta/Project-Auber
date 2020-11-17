package com.zanetta.auber;
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
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import javax.swing.text.html.HTML;


public class MyGdxGame extends ApplicationAdapter {
	private static final String TAG = MyGdxGame.class.getSimpleName();
	//SpriteBatch batch;
	public static final float WORLD_WIDTH = 480;
	public static final float WORLD_HEIGHT = 800;
	Texture img;
	Infiltrator infiltrator;
	Player player;
	//Group group;
	Stage stage;


	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));
		//group = new Group();


		//group.setPosition(50, 100);
		//stage.addActor(group);

		player = new Player(new TextureRegion(img));
		stage.addActor(player);
		//player.setScale(0.5F);
		//player.setPosition(0,0);

		Gdx.input.setInputProcessor(stage);
		stage.addListener(new MyInputListener());
		//infiltrator = new Infiltrator(new TextureRegion(img));
		//infiltrator.setPosition(50,100);
		//infiltrator.addListener(new MyClickListener());
		//infiltrator.setOrigin(0,0);
		//infiltrator.setScale(0.5F,1.0F);
		//infiltrator.setRotation(45);
		//group.addActor(player);
		//group.addActor(infiltrator);
	}


	private void testMoveToAction(){
		player.setPosition(0,0);
		MoveToAction action = Actions.moveTo(150,300,3.0F);

		player.addAction(action);
	}

	private void testMoveByAction(){
		player.setPosition(player.getStage().getWidth()/2 - player.getWidth()/2, player.getStage().getHeight()/2 - player.getHeight()/2);
		MoveByAction action = Actions.moveBy(100, -200, 2.0F);
		player.addAction(action);
	}

	private void testRotateToAction(){
		player.setPosition(player.getStage().getWidth()/2 - player.getWidth()/2, player.getStage().getHeight()/2 - player.getHeight()/2);
		player.setOrigin(player.getWidth()/2, player.getHeight()/2);
		player.setRotation(-90);
		RotateToAction action = Actions.rotateTo(-270, 2.0F);
		player.addAction(action);
	}

	private void testRotateByAction(){
		player.setPosition(player.getStage().getWidth()/2 - player.getWidth()/2, player.getStage().getHeight()/2 - player.getHeight()/2);
		player.setOrigin(player.getWidth()/2, player.getHeight()/2);
		player.setScale(0.5F, 2.0F);
		ScaleToAction action = Actions.scaleTo(1.0F, 1.0F, 2.0F);
		player.addAction(action);
	}

	private void testScaleByAction(){
		player.setPosition(player.getStage().getWidth()/2 - player.getWidth()/2, player.getStage().getHeight()/2 - player.getHeight()/2);
		player.setOrigin(player.getWidth()/2, player.getHeight()/2);
		player.setScale(0.5F, 0.5F);
		ScaleByAction action = Actions.scaleBy(0.5F, 0.5F, 2.0F);
		player.addAction(action);
	}
	private void testSizeToAction() {
		player.setPosition(0, 0);
		SizeToAction action = Actions.sizeTo(150, 300, 2.0F);
		player.addAction(action);
	}

	private void testSizeByAction() {
		player.setPosition(0, 0);
		SizeByAction action = Actions.sizeBy(150, 300, 2.0F);
		player.addAction(action);
	}

	private void testAlphaAction() {
		player.setPosition(0, 0);
		player.getColor().a = 1.0F;
		AlphaAction action = Actions.alpha(0.0F, 5.0F);
		player.addAction(action);
	}

	private void testParallelAction() {
		player.setPosition(0, 0);
		player.setScale(0.5F, 0.5F);
		player.setRotation(0);

		player.setOrigin(player.getWidth() / 2, player.getHeight() / 2);

		MoveToAction moveTo = Actions.moveTo(150, 500, 3.0F);

		ScaleToAction scaleTo = Actions.scaleTo(1.0F, 1.0F, 3.0F);

		RotateByAction rotateBy = Actions.rotateBy(360.0F, 3.0F);

		ParallelAction parallelAction = Actions.parallel(moveTo, scaleTo, rotateBy);

		player.addAction(parallelAction);
	}

	private void testSequenceAction() {
		player.setPosition(0, 0);
		player.setScale(1.0F, 1.0F);
		player.setRotation(0);

		player.setOrigin(player.getWidth() / 2, player.getHeight() / 2);

		DelayAction delay = Actions.delay(3.0F);

		MoveToAction moveTo = Actions.moveTo(150, 500, 3.0F);

		ParallelAction parallel = Actions.parallel(
				Actions.scaleTo(0.5F, 0.5F, 3.0F),
				Actions.rotateBy(360.0F, 3.0F)
		);

		SequenceAction sequenceAction = Actions.sequence(delay, moveTo, parallel);

		player.addAction(sequenceAction);
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


