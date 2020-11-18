package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ally extends Actor implements Sprite{
	private TextureRegion textureRegion;

	public Ally(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
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
