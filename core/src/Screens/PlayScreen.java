package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zanetta.auber.ProjectAuberGame;


public class PlayScreen implements Screen {
private ProjectAuberGame game;
Texture texture;
private OrthographicCamera gamecam;
private Viewport gamePort;


public PlayScreen(ProjectAuberGame game) {
	this.game=game;
	texture = new Texture("badlogicpack.png");
	gamecam = new OrthographicCamera();
	gamePort = new FitViewport( ProjectAuberGame.V_WIDTH,ProjectAuberGame.V_HEIGHT,gamecam);
}
	@Override
	public void show() {
		
		
	}

	@Override
	public void render(float delta) {
		 Gdx.gl.glClearColor(1, 0, 0, 1);
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 game.batch.setProjectionMatrix(gamecam.combined);
		 game.batch.begin();
		 game.batch.draw(texture, 0, 0);
		 game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width,height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

