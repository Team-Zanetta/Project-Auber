package com.zanetta.auber;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
		
		stage = new Stage();
		Player player = new Player(Textures.getTexture("player"), (TiledMapTileLayer) map.getLayers().get(0));
		
//		Set Position of Player
		player.setPosition(85,85);


		
		System s1 = new System("System", 3);
		s1.setX(100);
		s1.setY(100);
		stage.addActor(s1);
		
		System s2 = new System("System", 3);
		s2.setX(200);
		s2.setY(200);
		stage.addActor(s2);
		
		sysList.add(s1);
		sysList.add(s2);

		Infiltrator infiltrator = new Infiltrator("infiltrator", sysList);
		mineTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
		tinkerer = new Tinkerer("tinkerer", sysList);
		stage.addActor(player);
		stage.addActor(infiltrator);
		stage.addActor(tinkerer);
		
		Controller controller = new Controller();
		stage.addActor(controller);
		MineDropAI tinkererAI = new MineDropAI(tinkerer);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
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
