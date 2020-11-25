package com.zanetta.auber;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ProjectAuberGame extends ApplicationAdapter {
	OrthographicCamera camera;
	Stage stage;
	ShapeRenderer sr;
	private Texture mineTexture;
	private Tinkerer tinkerer;
	private OrthogonalTiledMapRenderer renderer;
	private TiledMap map;
	private ArrayList<System> sysList = new ArrayList<System>();
	
	@Override
	public void create () {
		
		TmxMapLoader tmxMapLoader = new TmxMapLoader();
		map = tmxMapLoader.load("maps/AuberSpaceStation.tmx");
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		renderer = new OrthogonalTiledMapRenderer(map);
	
        camera = new OrthographicCamera();
        float widthHeightRatio= 1f; 
        camera.setToOrtho(false,(layer.getWidth()*layer.getTileWidth())/widthHeightRatio,layer.getHeight()*layer.getTileHeight());
        camera.update();

		Textures.importTextures("auberTextures.atlas");
		
		sr = new ShapeRenderer();
		stage = new Stage();
		Player player = new Player(Textures.getTexture("auber"), sr, layer);
		mineTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
		stage.addActor(player);
		
//		Set Position of Player
		player.setPosition(85,85);


		
		
		String[] sprites = {"squidmonk", "flameghost", "babymech"};
		
		for (int i = 0; i<1; i++) {
			int rand = (int)(Math.random() * 3);
			tinkerer = new Tinkerer(sprites[rand]);
			stage.addActor(tinkerer);
		}
		
		for (int i = 0; i < 15; i++) {
			int rand = (int)(Math.random() * 3);
			Ally ally = new Ally(Textures.getTexture(sprites[rand]));
			ally.setX((float)(Math.random() * stage.getWidth()));
			ally.setY((float)(Math.random() * stage.getHeight()));
			stage.addActor(ally);
		}
		
		for (int i = 0; i < 5; i++) {
			int rand = (int)(Math.random() * 3);
			Infiltrator infiltrator = new Infiltrator(sprites[rand]);
			infiltrator.setX((float)(Math.random() * stage.getWidth()));
			infiltrator.setY((float)(Math.random() * stage.getHeight()));
			stage.addActor(infiltrator);
		}
		
		for (int i = 0; i < 10; i++) {
			System s1 = new System("testSystem", 3);
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
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
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
