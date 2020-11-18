package com.zanetta.auber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Player extends Actor implements Sprite, InputProcessor{
	private TextureRegion textureRegion;
	private float xVelocity, yVelocity;
	private float movementDistance = 2;
	private float movementTime = 0.1f;
	private float scannerSlowdown = 0.5f;
	public boolean scanning;

	
	public  Player(TextureRegion textureRegion){
        super();
//        Sets up textures
        this.textureRegion = textureRegion;
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
//        Sets up default velocities
        xVelocity = 0;
        yVelocity = 0;
//        Sets up input
        Gdx.input.setInputProcessor(this);
        
        
    }

    @Override
    public void act(float delta) {
    	
//    	Creates movement actions if the keys are pressed
    	if(xVelocity != 0 || yVelocity != 0) {
    		float factor = movementDistance;
        	if(scanning) {
        		factor = factor * scannerSlowdown;
        	}
        	
    		MoveByAction moveAction = new MoveByAction();
            moveAction.setAmount(xVelocity * factor, yVelocity * factor);;
            moveAction.setDuration(movementTime);
            this.addAction(moveAction);
    	}
    	
//    	Executes actions
    	super.act(delta);
    }


//    Draws the player
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
//		Starts the scanner actions on next act if space is pressed
		if(keycode == Keys.SPACE) {
			scanning = true;
		}
		
//		Sets the relative velocities on button presses 
		if(keycode == Keys.RIGHT | keycode == Keys.D) {
			xVelocity += 1;
		}if(keycode == Keys.LEFT | keycode == Keys.A) {
			xVelocity -= 1;
		}if(keycode == Keys.UP | keycode == Keys.W) {
			yVelocity += 1;
		}if(keycode == Keys.DOWN | keycode == Keys.S) {
			yVelocity -= 1;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
//		Ends scanning on space up
		if(keycode == Keys.SPACE) {
			scanning = false;
		}
		
//		Reduces the relative velocities on button up
		if(keycode == Keys.RIGHT | keycode == Keys.D) {
			xVelocity += -1;
		}if(keycode == Keys.LEFT | keycode == Keys.A) {
			xVelocity -= -1;
		}if(keycode == Keys.UP | keycode == Keys.W) {
			yVelocity += -1;
		}if(keycode == Keys.DOWN | keycode == Keys.S) {
			yVelocity -= -1;
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
