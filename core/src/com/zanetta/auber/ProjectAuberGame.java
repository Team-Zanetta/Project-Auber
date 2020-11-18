package com.zanetta.auber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ProjectAuberGame extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	TextureRegion img;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(1280, 720);
		batch = new SpriteBatch();
		Textures.importTextures("badlogicpack.atlas");
		img = Textures.getTexture("badlogic");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		Textures.dispose();
	}
}
