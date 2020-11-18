package com.zanetta.auber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen extends ScreenAdapter{
    private Texture mainTexture;
    private Texture tinkererTexture;

    private Stage stage;
    private Player mainPlayer;
    private Tinkerer tinkerer;


    public GameScreen(){
        mainTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        tinkererTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        stage = new Stage(new StretchViewport(MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT));
        mainPlayer = new Player((new TextureRegion(mainTexture)));
        tinkerer = new Tinkerer((new TextureRegion(tinkererTexture)));
        stage.addActor(mainPlayer);
        stage.addActor(tinkerer);
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
