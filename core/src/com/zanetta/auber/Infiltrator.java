package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Queue;

public class Infiltrator extends Actor implements Sprite{
	private TextureRegion textureRegion;


    public  Infiltrator(TextureRegion textureRegion){
        super();
        this.textureRegion = textureRegion;
        setSize(this.textureRegion.getRegionWidth()/3, this.textureRegion.getRegionHeight()/3);
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


    public Enum state(){

        return null;
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
