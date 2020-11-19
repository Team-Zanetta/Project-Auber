package com.zanetta.auber;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class ProjectAuberGame extends ApplicationAdapter {
	
	OrthographicCamera camera;
	Stage stage;
	Player player;
	
	//Map
	private TiledMap map;
	private AssetManager manager;
	private int tileWidth, tileHeight,
    mapWidthInTiles, mapHeightInTiles,
    mapWidthInPixels, mapHeightInPixels;
	
	// Render
	private OrthogonalTiledMapRenderer renderer;
	
	@Override
	public void create () {
		manager = new AssetManager();
		manager.setLoader(TiledMap.class, new TmxMapLoader());
		manager.load("maps/Spaceship.tmx", TiledMap.class);
        manager.finishLoading();
 
        map = manager.get("maps/Spaceship.tmx", TiledMap.class);
        
        // Read properties
        MapProperties properties = map.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInPixels  = mapWidthInTiles  * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;
		
		camera = new OrthographicCamera(1280, 720);
        camera.position.x = mapWidthInPixels * .5f;
        camera.position.y = mapHeightInPixels * .35f;
        
		Textures.importTextures("Auber pack.atlas");
		
		stage = new Stage();
		player = new Player(Textures.getTexture("Player"));
		Infiltrator infiltrator = new Infiltrator(Textures.getTexture("Enemy"));
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
		manager.dispose();
		Textures.dispose();
		stage.dispose();
	}
}
