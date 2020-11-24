package com.zanetta.auber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ProjectAuberGame extends ApplicationAdapter {
	OrthographicCamera camera;
	Stage stage;
	private Texture mineTexture;
	private Tinkerer tinkerer;
	@Override
	public void create () {
		camera = new OrthographicCamera(1280, 720);
		Textures.importTextures("auberTextures.atlas");
		
		stage = new Stage();
		Player player = new Player(Textures.getTexture("player"));
		Infiltrator infiltrator = new Infiltrator("infiltrator");
		mineTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
		tinkerer = new Tinkerer("tinkerer");
		stage.addActor(player);
		stage.addActor(infiltrator);
		stage.addActor(tinkerer);
		
		System s1 = new System("System", 3);
		s1.setX(100);
		s1.setY(100);
		stage.addActor(s1);
		
		System s2 = new System("System", 3);
		s2.setX(200);
		s2.setY(200);
		stage.addActor(s2);
		
		Controller controller = new Controller();
		stage.addActor(controller);
		MineDropAI tinkererAI = new MineDropAI(tinkerer);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		if(tinkerer.getWanderinglooptimecounter() == 0){
			Mine Mine1 = new Mine((new TextureRegion(mineTexture)));
			Mine1.setBounds(tinkerer.getX(), tinkerer.getY(), 10, 10);
			Mine1.setRandonMineMode();
			stage.addActor(Mine1);
		}
	}
	
	@Override
	public void dispose () {
		Textures.dispose();
		stage.dispose();
	}
}
