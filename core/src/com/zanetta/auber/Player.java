package com.zanetta.auber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Player extends Actor implements Sprite, InputProcessor{
	private TextureRegion textureRegion;
	private float xVelocity, yVelocity;
	private float movementDistance;
	private float movementTime;
	
	public  Player(TextureRegion textureRegion){
        super();
        this.textureRegion = textureRegion;
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
        xVelocity = 0;
        yVelocity = 0;
        movementDistance = 2;
        movementTime = 0.1f;
        Gdx.input.setInputProcessor(this);
        
        
    }

    @Override
    public void act(float delta) {
    	if(xVelocity != 0 || yVelocity != 0) {
    		MoveByAction moveAction = new MoveByAction();
            moveAction.setAmount(xVelocity, yVelocity);;
            moveAction.setDuration(movementTime);
            this.addAction(moveAction);
    	}
    	super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (textureRegion == null | !isVisible()){
            return;
        }

        batch.draw(textureRegion,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());
    }
    public Infiltrator enemyCarrying(){
        return null;
    }


    public Enum state(){

        return null;
    }

    public void pickupDropEnemy(){

    }


	@Override
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}


	@Override
	public void setTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.RIGHT | keycode == Keys.D) {
			xVelocity += movementDistance;
		}if(keycode == Keys.LEFT | keycode == Keys.A) {
			xVelocity -= movementDistance;
		}if(keycode == Keys.UP | keycode == Keys.W) {
			yVelocity += movementDistance;
		}if(keycode == Keys.DOWN | keycode == Keys.S) {
			yVelocity -= movementDistance;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.RIGHT | keycode == Keys.D) {
			xVelocity += -movementDistance;
		}if(keycode == Keys.LEFT | keycode == Keys.A) {
			xVelocity -= -movementDistance;
		}if(keycode == Keys.UP | keycode == Keys.W) {
			yVelocity += -movementDistance;
		}if(keycode == Keys.DOWN | keycode == Keys.S) {
			yVelocity -= -movementDistance;
		}
			
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
