package com.zanetta.auber;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.utils.Array;
import com.zanetta.auber.Infiltrator.State;

public class Player extends Actor implements Sprite, InputProcessor {
	private TextureRegion textureRegion;
	private TiledMapTileLayer collisionLayer;
	private float xVelocity, yVelocity;
	public Health health;
	private float movementSpeed = 30;
	private float movementSpeedAdjust = 1, timeLeftOnSpeedAdjust = 0;
	private float scannerSlowdown = 0.5f;
	public boolean scanning;
	private float scannerRadius = 100;
	private float pickupRadius = 20;
	private Infiltrator infiltratorCarrying;
	private float attackRange = 50;
	private int attackDamage = 1;

	public Player(TextureRegion textureRegion, TiledMapTileLayer collisionLayer) {
		super();
//        Sets up textures
		this.textureRegion = textureRegion;
		setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
//        Sets up default velocities
		xVelocity = 0;
		yVelocity = 0;
//        Sets up input
		Gdx.input.setInputProcessor(this);
		
//		Sets up health
		this.health = new Health();
		this.health.setMaxHealth(5);
		this.health.setHealth(5);

//		Sets up Collision layer
		this.collisionLayer = collisionLayer;
	}

	@Override
	public void act(float delta) {

//    	Creates movement actions if the keys are pressed
		if (xVelocity != 0 || yVelocity != 0) {
			float duration = (float)0.1;
			float distance = movementSpeed*duration;
			if (scanning) {
				distance = distance * scannerSlowdown;
			}
			if (timeLeftOnSpeedAdjust < 0) {
				distance = distance * movementSpeedAdjust;
				timeLeftOnSpeedAdjust -= delta;
			}
			
			float velocity = (float) Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
			
			MoveByAction moveAction = new MoveByAction();
			moveAction.setAmount(xVelocity/velocity * distance, yVelocity/velocity * distance);
			moveAction.setDuration(duration);
			
			this.addAction(moveAction);
			
			/**
			 *	Below is the code for collisions regarding player and walls. 
			 */
			
			//variables for collision
			float oldX = getX(), oldY = getY(), tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
			boolean collisionX = false, collisionY = false;
			
			//collision on x-axis
			if(xVelocity < 0) {
				//top left tile
				collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight())/ tileHeight))
						.getTile().getProperties().containsKey("blocked");
				
				//middle left tile
				if(!collisionX)
				collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight() / 2)/ tileHeight))
						.getTile().getProperties().containsKey("blocked");

				
				//bottom left tile
				if(!collisionX)
				collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight))
						.getTile().getProperties().containsKey("blocked");

			} 
				if (xVelocity > 0) {
				//top right tile
				collisionX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
						.getTile().getProperties().containsKey("blocked");
				
				//middle right tile
				if(!collisionX)
				collisionX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight() / 2) / tileHeight))
						.getTile().getProperties().containsKey("blocked");
				
				//bottom right tile
				if(!collisionX)
				collisionX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeight))
					.getTile().getProperties().containsKey("blocked");
			}
			
				//collision on y axis
				
				if(yVelocity < 0) {
				//bottom left tile
				collisionY = collisionLayer.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight))
						.getTile().getProperties().containsKey("blocked");
				
				//bottom middle tile
				if(!collisionY)
					collisionY = collisionLayer.getCell((int) ((getX() + getWidth() / 2) / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
					.getTile().getProperties().containsKey("blocked");
				
				//bottom right tile
				if(!collisionY)
					collisionY = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeight))
					.getTile().getProperties().containsKey("blocked");
				
			} 
				if (yVelocity > 0) {
				//top left tile
				collisionY = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
						.getTile().getProperties().containsKey("blocked");
				
				//top middle tile
				if(!collisionY)
					collisionY = collisionLayer.getCell((int) ((getX() + getWidth() / 2) / tileWidth), (int) ((getY() + getHeight()/ 2) / tileHeight))
					.getTile().getProperties().containsKey("blocked");
				
				//top right tile
				if(!collisionY)
					collisionY = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
					.getTile().getProperties().containsKey("blocked");
			}
			
			//react to collision on x & y axis
			if(collisionX) {
				setX(oldX);
				xVelocity = 0;
				collisionX = false;
			}
			if(collisionY) {
				setY(oldY);
				yVelocity = 0;
				collisionY = false;
			}
			
			this.addAction(moveAction);
			
		}
		

		if (scanning) {
			scan();
		}

//    	Executes actions
		super.act(delta);
	}

	private void scan() {
		float[] playerLocation = getCentrePoint();
		Array<Actor> actors = this.getStage().getActors();
		for (Actor actor : actors) {
			if (actor instanceof Infiltrator) {
				Infiltrator infiltrator = (Infiltrator) actor;
				float[] infiltratorLocation = infiltrator.getCentrePoint();

				float dx = Math.abs(playerLocation[0] - infiltratorLocation[0]);
				float dy = Math.abs(playerLocation[1] - infiltratorLocation[1]);

//    			Quick square check
				if (dx < scannerRadius & dy < scannerRadius) {
//    				Slower circle check
					if (Math.sqrt(dx * dx + dy * dy) < scannerRadius) {
						infiltrator.scan();
					}
				}else {
//			    	If the infiltrator is not within the radius, they are unscanned (texture returns to normal)
					infiltrator.unScan();
				}
			}
		}
	}

	private void unScanAll() {
		Array<Actor> actors = this.getStage().getActors();
		for (Actor actor : actors) {
			if (actor instanceof Infiltrator) {
				Infiltrator infiltrator = (Infiltrator) actor;
				infiltrator.unScan();
			}
		}
	}

	public float[] getCentrePoint() {
		float x = getX() + getWidth() / 2;
		float y = getY() + getHeight() / 2;
		return new float[] { x, y };
	}

//    Draws the player
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (textureRegion == null | !isVisible()) {
			return;
		}

		batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
				getScaleY(), getRotation());
	}
	
	public Infiltrator infiltratorCarrying() {
		return infiltratorCarrying;
	}

	public Enum state() {

		return null;
	}

	private Infiltrator getNearestinfiltrator() {
		float[] playerLocation = getCentrePoint();
		Array<Actor> actors = this.getStage().getActors();
		float closestDistance = Float.MAX_VALUE;
		Infiltrator closestInfiltrator = null;
		for (Actor actor : actors) {
			if (actor instanceof Infiltrator) {
				Infiltrator infiltrator = (Infiltrator) actor;
				float[] infiltratorLocation = infiltrator.getCentrePoint();
				float dx = Math.abs(playerLocation[0] - infiltratorLocation[0]);
				float dy = Math.abs(playerLocation[1] - infiltratorLocation[1]);
				float distance = (float) Math.sqrt(dx * dx + dy * dy);
				if (distance < closestDistance) {
					closestDistance = distance;
					closestInfiltrator = infiltrator;
				}
			}
		}
		return closestInfiltrator;
	}

	private void attackInfiltrator() {
		Infiltrator infiltrator = getNearestinfiltrator();
		float[] playerLocation = getCentrePoint();
		float[] infiltratorLocation = infiltrator.getCentrePoint();
		float dx = Math.abs(playerLocation[0] - infiltratorLocation[0]);
		float dy = Math.abs(playerLocation[1] - infiltratorLocation[1]);
		float distance = (float) Math.sqrt(dx * dx + dy * dy);
		
		if(distance < attackRange) {
			infiltrator.health.decreaseHealth(attackDamage);
		}
	}

	private void pickupDropInfiltrator() {
		if (infiltratorCarrying == null) {
			float[] playerLocation = getCentrePoint();
			Infiltrator infiltrator = getNearestinfiltrator();
			if (infiltrator.state() == State.INCAPACITATED) {
				
				float[] infiltratorLocation = infiltrator.getCentrePoint();
				float dx = Math.abs(playerLocation[0] - infiltratorLocation[0]);
				float dy = Math.abs(playerLocation[1] - infiltratorLocation[1]);
				
				// Quick square check
				if (dx < pickupRadius & dy < pickupRadius) {
					// Slower circle check
					if (Math.sqrt(dx * dx + dy * dy) < pickupRadius) {
						infiltrator.setVisible(false);
						infiltratorCarrying = infiltrator;
					}
				}
			}
		}else{
			//If carrying an infiltrator, drop them
			infiltratorCarrying.setX(getX());
			infiltratorCarrying.setY(getY());
			infiltratorCarrying.setVisible(true);
			infiltratorCarrying=null;
		}
	}
	
	public boolean teleportTo(float x, float y) {
		if(0 < x & 0 < y & x < getStage().getWidth() & y < getStage().getHeight()) {
			setX(x);
			setY(y);
			return true;
		}else {
			return false;
		}
	}
	
	public void changeSpeed(float speedFactor, float time) {
		movementSpeedAdjust = speedFactor;
		timeLeftOnSpeedAdjust = time;
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
		}if(keycode == Keys.CONTROL_RIGHT | keycode == Keys.E) {
			pickupDropInfiltrator();
		}if(keycode == Keys.ALT_RIGHT | keycode == Keys.R) {
			attackInfiltrator();
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
			unScanAll();
		}
		
//		Reduces the relative velocities on button up
		if(keycode == Keys.RIGHT | keycode == Keys.D) {
			xVelocity = 0;
		}if(keycode == Keys.LEFT | keycode == Keys.A) {
			xVelocity = 0;
		}if(keycode == Keys.UP | keycode == Keys.W) {
			yVelocity  = 0;
		}if(keycode == Keys.DOWN | keycode == Keys.S) {
			yVelocity  = 0;
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
