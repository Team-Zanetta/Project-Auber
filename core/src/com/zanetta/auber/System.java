package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

class System extends Actor implements Sprite{
    private float health;  //In seconds
    private boolean destroyed;
	private TextureRegion textureRegion;
	private String textureName;
	public boolean claimed;

    System(String textureName, int health) {
    	super();
    	this.textureName = textureName;
    	setTextureRegion(Textures.getTexture(textureName));
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    	
        this.health = health;
        this.destroyed = false;
        this.claimed = false;
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
    
    public float getHealth() {
        return health;
    }
    
    public void setHealth(float health) {
        this.health = health;
        if(this.health <= 0) {
        	this.health = 0;
        	setDestroyed(true);
        }
    }

    public boolean getDestroyed() {
        return destroyed;
    }
    
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
        
        if(destroyed) {
        	setTextureRegion(Textures.getTexture(textureName + "Destroyed"));
        }
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
