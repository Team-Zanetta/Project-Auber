package com.zanetta.auber;
import com.badlogic.gdx.Game;

public class MainGame extends Game{

    public static final float WORLD_WIDTH = 1960;
    public static final float WORLD_HEIGHT = 1080;

    private StartScreen startScreen;

    private GameScreen gameScreen;

    @Override
    public void create() {
        startScreen = new StartScreen(this);

        gameScreen = new GameScreen();

        //setScreen(gameScreen);
        setScreen(startScreen);
    }

    public void showGameScreen(){
        setScreen(gameScreen);
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
