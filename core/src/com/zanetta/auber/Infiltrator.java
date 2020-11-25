package com.zanetta.auber;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;

public class Infiltrator extends Actor implements Sprite{
	private TextureRegion textureRegion;
	private String textureName;
	private boolean hasBeenScanned;
	public Health health;
	private int maxHP = 3;
	private float movementSpeed = 30;
	private ArrayList<System> systems = new ArrayList<System>();
	private System sabotaging;
	
	enum State{
		IDLE,
		INCAPACITATED,
		TRAVELING,
		SABOTAGING
	}
	public State state;

    public  Infiltrator(String textureName){
        super();
        this.textureName = textureName;
        setTextureRegion(Textures.getTexture(textureName));
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
        	
    	} else if (state == State.TRAVELING) {
    		if(getX() == sabotaging.getX() & getY() == sabotaging.getY()) {
    			state = State.SABOTAGING;
    			this.clearActions();
    		}
    	} else if (state == State.SABOTAGING) {
    		sabotaging.setHealth(sabotaging.getHealth() - delta);
    		if(sabotaging.getDestroyed()) {
    			sabotaging = null;
    			state = State.IDLE;
    		}
    	} else if (state == State.IDLE){
        	if(Math.random()<0.01) {
        		moveTo((float)Math.random() * getStage().getWidth(), (float)Math.random() * getStage().getHeight());
        	}
        }
        super.act(delta);
    }
    
    public void moveTo(float x, float y) {
    	if(state != State.INCAPACITATED) {
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
    	String s = textureName+"-alerted";
//    	java.lang.System.out.println(s);
    	setTextureRegion(Textures.getTexture(s));
    	hasBeenScanned = true;
    }
    
    public void unScan() {
    	setTextureRegion(Textures.getTexture(textureName));
    	hasBeenScanned = false;
    }
    
    public boolean getHasBeenScanned() {
    	return hasBeenScanned;
    }
    
    public ArrayList<System> getAllSystems(){
    	ArrayList<System> systems = new ArrayList<System>();
    	Array <Actor> actors = this.getStage().getActors();
		for(Actor actor : actors){
			if (actor instanceof System) {
				systems.add((System)actor);
			}
		}
		return systems;
    }

    public Queue<System> sabotageQueue(){
    	if(systems.size() == 0) {
    		systems = getAllSystems();
    	}
    	
    	Queue<System> system_queue = new Queue<>();
    	for (int i = 0; i < systems.size(); i++) {
    		if (systems.get(i).getDestroyed() == false && systems.get(i).claimed == false) {
				 system_queue.addLast(systems.get(i));
			 }
    	}
        return system_queue;
    }


    public void PerformSabotage(){	
    	System sabotage = sabotageQueue().get(0);
        sabotage.claimed = true;
        moveTo(sabotage.getX(), sabotage.getY());
        this.sabotaging = sabotage;
        state = State.TRAVELING;
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
