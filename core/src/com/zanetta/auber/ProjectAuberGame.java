package com.zanetta.auber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ProjectAuberGame extends ApplicationAdapter {
	OrthographicCamera camera;
	Stage stage;
	ShapeRenderer sr;
	private Texture mineTexture;
	private Tinkerer tinkerer;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(1280, 720);
		Textures.importTextures("auberTextures.atlas");
		
		sr = new ShapeRenderer();
		stage = new Stage();
		Player player = new Player(Textures.getTexture("player"), sr);
		mineTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
		tinkerer = new Tinkerer("tinkerer");
		stage.addActor(player);
		stage.addActor(tinkerer);
		
		
		
		for (int i = 0; i < 15; i++) {
			Ally ally = new Ally(Textures.getTexture("infiltrator"));
			ally.setX((float)(Math.random() * stage.getWidth()));
			ally.setY((float)(Math.random() * stage.getHeight()));
			stage.addActor(ally);
		}
		
		for (int i = 0; i < 5; i++) {
			Infiltrator infiltrator = new Infiltrator("infiltrator");
			infiltrator.setX((float)(Math.random() * stage.getWidth()));
			infiltrator.setY((float)(Math.random() * stage.getHeight()));
			stage.addActor(infiltrator);
		}
		
		for (int i = 0; i < 10; i++) {
			System s1 = new System("System", 3);
			s1.setX((float)(Math.random() * stage.getWidth()));
			s1.setY((float)(Math.random() * stage.getHeight()));
			stage.addActor(s1);
		}
		
		Controller controller = new Controller();
		stage.addActor(controller);
		MineDropAI tinkererAI = new MineDropAI(tinkerer);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sr.setAutoShapeType(true);
		sr.begin();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		sr.end();

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
