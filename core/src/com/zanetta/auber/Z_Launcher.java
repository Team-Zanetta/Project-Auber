package com.zanetta.auber;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Z_Launcher extends Game{

    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    private Z_StartScreen startScreen;

    private ProjectAuberGame gameScreen;

    @Override
    public void create() {
    	Textures.importTextures("Auber pack.atlas");
        startScreen = new Z_StartScreen(this);
        gameScreen = new ProjectAuberGame();

        setScreen(startScreen);
    }

    public void showGameScreen(){
        setScreen((Screen) gameScreen);
        if (startScreen != null){
            startScreen.dispose();
            startScreen = null;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (startScreen != null){
            startScreen.dispose();
            startScreen = null;
        }
        if (gameScreen != null){
            gameScreen.dispose();
            gameScreen = null;
        }
    }
}
