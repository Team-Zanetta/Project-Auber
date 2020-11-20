package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Queue;

public class Infiltrator extends Actor implements Sprite{
	private TextureRegion textureRegion;
	private String textureName;
	private boolean hasBeenScanned;
	public Health health;
	private int maxHP = 3;
	private float movementSpeed = 30;

	
	enum State{
		IDLE,
		INCAPACITATED
	}
	private State state;

    public  Infiltrator(String textureName){
        super();
        this.textureName = textureName;
        setTextureRegion(Textures.getTexture(textureName));
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
        hasBeenScanned = false;
        state = State.IDLE;
        health = new Health();
        health.setMaxHealth(maxHP);
        health.setHealth(maxHP);
    }

    @Override
    public void act(float delta) {
        if(health.getHealth() <= 0) {
        	health.setHealth(0);
        	state = State.INCAPACITATED;
        	this.clearActions();
        } else {
        	if(Math.random()<0.01) {
        		moveTo((float)Math.random() * getStage().getWidth(), (float)Math.random() * getStage().getHeight());
        	}
        }
        super.act(delta);
    }
    
    public void moveTo(float x, float y) {
    	if(state != state.INCAPACITATED) {
	    	MoveToAction moveAction = new MoveToAction();
	    	moveAction.setPosition(x, y);
	    	
	    	float dx = Math.abs(x - getX());
			float dy = Math.abs(y - getY());
	    	float distance = (float) Math.sqrt(dx * dx + dy * dy);
	    	
	    	moveAction.setDuration(distance / movementSpeed);
	    	addAction(moveAction);
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
    	setTextureRegion(Textures.getTexture(textureName+"Scanned"));
    	hasBeenScanned = true;
    }
    
    public void unScan() {
    	setTextureRegion(Textures.getTexture(textureName));
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
