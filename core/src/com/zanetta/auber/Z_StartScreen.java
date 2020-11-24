package com.zanetta.auber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Z_StartScreen implements Screen{
    private Z_Launcher launcher;
    private Stage stage;
    private Actor logoActor;
    private float deltaSum;


    public Z_StartScreen(Z_Launcher launcher){
        this.launcher = launcher;
        stage = new Stage();
//        logoActor = new Player(Textures.getTexture("zanetta"));
        logoActor.setWidth(logoActor.getWidth()/10);
        logoActor.setHeight(logoActor.getHeight()/10);
        logoActor.setPosition(stage.getWidth()/2 - logoActor.getWidth()/2, stage.getHeight()/2 - logoActor.getHeight()/2);
        stage.addActor(logoActor);
    }

    @Override
    public void show() {
        deltaSum = 0;

    }

    @Override
    public void render(float delta) {
        deltaSum += delta;

        if (deltaSum >= 1.0F){
            if (launcher != null){
                launcher.showGameScreen();
                return;
            }
        }

        Gdx.gl.glClearColor(0.75F, 1, 0.98F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (stage != null){
            stage.dispose();
        }
    }
}
