package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Queue;

public class Infiltrator extends Actor implements Sprite{
	private TextureRegion textureRegion;
	private boolean hasBeenScanned;


    public  Infiltrator(TextureRegion textureRegion){
        super();
        this.textureRegion = textureRegion;
//        TODO: Remove these divide by 4s, only here because my temporary sprite is huge - Harry
        setSize(this.textureRegion.getRegionWidth()/4, this.textureRegion.getRegionHeight()/4);
        hasBeenScanned = false;
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
    
    public float[] getCentrePoint() {
    	float x = getX() + getWidth()/2;
    	float y = getY() + getHeight()/2;
    	return new float[] {x,y};
    }
    
    public void scan() {
    	//TODO: add actual been scanned behaviour here - need to change sprite? - Harry
    	setX(getCentrePoint()[0]);
    	setY(getCentrePoint()[1]);
    	hasBeenScanned = true;
    }
    
    public boolean getHasBeenScanned() {
    	return hasBeenScanned;
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
