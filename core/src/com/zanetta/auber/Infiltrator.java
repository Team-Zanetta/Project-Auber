package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Queue;

public class Infiltrator extends Actor implements Sprite{
	private TextureRegion textureRegion;
	private boolean hasBeenScanned;
	public Health health;
	private int maxHP = 3;
	
	enum State{
		IDLE,
		INCAPACITATED
	}
	private State state;

    public  Infiltrator(TextureRegion textureRegion){
        super();
        this.textureRegion = textureRegion;
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
        hasBeenScanned = false;
        state = State.IDLE;
        health = new Health();
        health.setMaxHealth(maxHP);
        health.setHealth(maxHP);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        
        if(health.getHealth() <= 0) {
        	health.setHealth(0);
        	state = State.INCAPACITATED;
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (textureRegion == null || !isVisible()){
            return;
        }

        batch.draw(textureRegion,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());
    }
    
    public float[] getCentrePoint() {
    	float x = getX() + getWidth()/2;
    	float y = getY() + getHeight()/2;
    	return new float[] {x,y};
    }
    
    public void scan() {
    	setTextureRegion(Textures.getTexture("EnemyScanned"));
    	hasBeenScanned = true;
    }
    
    public void unScan() {
    	setTextureRegion(Textures.getTexture("Enemy"));
    	hasBeenScanned = false;
    }
    
    public boolean getHasBeenScanned() {
    	return hasBeenScanned;
    }


    public Enum state(){

        return state;
    }


    public Queue sabotageQueue(){

        return null;
    }


    public void PerformSabotage(){
        
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
}
