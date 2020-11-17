package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Player extends Actor{
    private TextureRegion region;


    public Player(TextureRegion region){
        super();
        this.region = region;
        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
    }


    public TextureRegion getRegion(){
        return region;
    }


    public void setRegion(TextureRegion region){
        this.region = region;
        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (region == null || !isVisible()){
            return;
        }

    /* batch.draw(
				region,
				x, y,
				originX, originY,
				width, height,
				scaleX, scaleY,
				rotation
		);*/
        batch.draw(region,
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
