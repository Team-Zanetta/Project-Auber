package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen extends ScreenAdapter{
    private Texture mainTexture;
    private Stage stage;
    private Player mainPlayer;


    public GameScreen(){
        mainTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        stage = new Stage(new StretchViewport(MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT));
        mainPlayer = new Player((new TextureRegion(mainTexture)));
        stage.addActor(mainPlayer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (stage != null){
            stage.dispose();
        }
        if (mainTexture != null){
            mainTexture.dispose();
        }


    }
}
