package com.zanetta.auber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ProjectAuberGame extends ApplicationAdapter {
	OrthographicCamera camera;
	Stage stage;
	Player player;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(1280, 720);
		Textures.importTextures("Auber pack.atlas");
		
		stage = new Stage();
		player = new Player(Textures.getTexture("badlogic"));
		Infiltrator infiltrator = new Infiltrator(Textures.getTexture("astronaut"));
		stage.addActor(player);
		stage.addActor(infiltrator);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
		Textures.dispose();
		stage.dispose();
	}
}
