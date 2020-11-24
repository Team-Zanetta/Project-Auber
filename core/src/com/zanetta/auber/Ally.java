package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Ally extends Actor implements Sprite{
	private TextureRegion textureRegion;
	private float movementSpeed = 30;

	public Ally(TextureRegion textureRegion) {
		setTextureRegion(textureRegion);
	}
	
	@Override
    public void act(float delta) {
        
    	if(Math.random()<0.01) {
    		moveTo((float)Math.random() * getStage().getWidth(), (float)Math.random() * getStage().getHeight());
    	}
        	
        super.act(delta);
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
	
	public void moveTo(float x, float y) {
    	
    	MoveToAction moveAction = new MoveToAction();
    	moveAction.setPosition(x, y);
    	
    	float dx = Math.abs(x - getX());
		float dy = Math.abs(y - getY());
    	float distance = (float) Math.sqrt(dx * dx + dy * dy);
    	
    	moveAction.setDuration(distance / movementSpeed);
    	addAction(moveAction);
    	
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
