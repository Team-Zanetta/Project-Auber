package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor{
	private TextureRegion textureRegion;
	
	public  Player(TextureRegion textureRegion){
        super();
        this.textureRegion = textureRegion;
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    }


    public TextureRegion getRegion(){
        return textureRegion;
    }


    public void setRegion(TextureRegion textureRegion){
        this.textureRegion = textureRegion;
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    }

    @Override
    public void act(float delta) {
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
    public Infiltrator enemyCarrying(){
        return null;
    }


    public Enum state(){

        return null;
    }

    public void pickupDropEnemy(){

    }
}
